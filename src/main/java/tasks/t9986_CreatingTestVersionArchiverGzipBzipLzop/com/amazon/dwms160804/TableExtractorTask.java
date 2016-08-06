package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160804;

import com.hadoop.compression.lzo.LzopCodec;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yaroshenko.y on 7/31/2016.
 */
class TableExtractorTask implements Runnable{

    private final String query;
    private final List<Long> params;
    private final Path filepath;
    private final DataSource datasource;
    private final String zipMethod;
    private final int fetchSize;

    public TableExtractorTask(String query, List<Long> params, Path filepath, DataSource datasource, String zipMethod, int fetchSize ) {
        this.query = query;
        this.params = params;
        this.filepath = filepath;
        this.datasource = datasource;
        this.zipMethod = zipMethod;
        this.fetchSize = fetchSize;
    }




    public String getQuery() {
        return query;
    }

    public List<Long>  getParams() {
        return params;
    }

    public Path getFilepath() {
        return filepath;
    }

    @Override
    public String toString() {
        return "TableExtractorTask{" +
                "query='" + query + '\'' +
                ", params=" + params +
                ", filepath=" + filepath +
                //", datasource=" + datasource +
                ", zipMethod='" + zipMethod + '\'' +
                ", fetchSize=" + fetchSize +
                '}';
    }

    @Override
    public void run() {

        try (Connection con = datasource.getConnection()){
            PreparedStatement rez = null;
            try {
                try {
                    rez = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                } catch (SQLFeatureNotSupportedException e) {
                    // Ignore this exception and try to prepare statement without additional parameters
                    Log.print("The JDBC driver doesn't support additional parameters for PreparedStatement.");
                }
                if (rez == null)
                    rez = con.prepareStatement(query);

                rez.clearParameters();
                for (int i = 0; i < params.size(); i++) {
                    rez.setLong(i + 1, params.get(i));
                }

//                BufferedWriter bw = new BufferedWriter(
//                        new OutputStreamWriter(
//                                                new FileOutputStream(filepath.toFile()),
//                                                "UTF-8")
//                                               );


/*!!!!!!!!!!!!!!!!!!!!  Здесь добавляем расширение в зависимости от метода сжатия       */
                Path filepathZip =Paths.get(filepath.toAbsolutePath().toString()+getExpansion(zipMethod));

                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream (
                                        createCompressionOutputStream(new FileOutputStream(filepathZip.toFile()), zipMethod)
                                ),
                                "UTF-8"
                        )
                );

                final long startTime = System.currentTimeMillis();
                Log.print(String.format("Task params : %s", this.toString()));
                Log.print("Execute query...");
                rez.setFetchSize(fetchSize);
                final long fetchingStartTime = System.currentTimeMillis();
                ResultSet rs = rez.executeQuery();
                Log.print("Ready to fetching...");
                try {
                    int columnCount = rs.getMetaData().getColumnCount();
                    long rowNum = 0;
                    while (rs.next()) {
                        if ((++rowNum)%10000 == 0) Log.print(String.format("Fetched %d rows in %.3f sec.", rowNum, (System.currentTimeMillis() - fetchingStartTime) / 1000.0));
                        StringBuilder sb = new StringBuilder();
                        for (int columnNum = 1; columnNum<=columnCount; columnNum++) {
                            // TODO Need to analyze rs.getMetaData().getColumnType()
                            sb.append("\"").append(rs.getString(columnNum)).append("\"");
                            if (columnNum!=columnCount)
                                sb.append("|");
                        }
                        bw.write(sb.toString());
                        bw.newLine();
                    }
                    bw.flush();
                    Log.print(String.format("Fetching data completed, fetched %d rows in %.3f sec.", rowNum, (System.currentTimeMillis() - startTime) / 1000.0));

                } finally {
                    if (rs!=null)
                        rs.close();
                    if (bw!=null)
                        bw.close();

/*!!!!!!!!!!!!!!!!!!!!  Этот  метод  выводит на экран содержимое упакованного файла       */
                    readZip (filepathZip, zipMethod);
                }

            } finally {
                if (rez!=null)
                    rez.close();
            }

        }
        catch(Exception e)  {
            e.printStackTrace();
        }
    }


    public void readZip (Path filepath, String zipMethod) throws FileNotFoundException {
        BufferedReader rw = null;
        try {
            rw = new BufferedReader(
                    new InputStreamReader(
                            createCompressionInputStream(new FileInputStream(filepath.toFile()),zipMethod),
                            "UTF-8"
                    )
            );
            String s = "";
            while ( ( s = rw.readLine()) !=null  ) System.out.println(s);
        } catch (IOException e) {   e.printStackTrace();    }
        finally {
            if (rw !=null) try {
                rw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    OutputStream createCompressionOutputStream(OutputStream os, String zipMethod) {
        try {
            if (zipMethod.equals("gzip")) return new GZIPOutputStream(os);
            if (zipMethod.equals("bzip2")) return new BZip2CompressorOutputStream(os);
            if (zipMethod.equals("lzop")) {
                LzopCodec codec = new LzopCodec();
                Configuration conf = new Configuration();
                codec.setConf(conf);
                return codec.createOutputStream(os);
            }
            if (zipMethod.equals("default")) return os;
        } catch (IOException e) {   e.printStackTrace();    }

        throw new RuntimeException("Compression method " + zipMethod + " is not recognized.");
    }

    InputStream createCompressionInputStream(InputStream is, String zipMethod) {
        try {
            if (zipMethod.equals("gzip")) return new GZIPInputStream(is);
            if (zipMethod.equals("bzip2")) return new BZip2CompressorInputStream(is);
            if (zipMethod.equals("lzop")) {
                LzopCodec codec = new LzopCodec();
                Configuration conf = new Configuration();
                codec.setConf(conf);
                CompressionInputStream cis = codec.createInputStream(is);
                return cis;
            }
            if (zipMethod.equals("default")) return is;
        } catch (IOException e) {   e.printStackTrace();    }

        throw new RuntimeException("Compression method " + zipMethod + " is not recognized.");
    }

    String getExpansion(String zipMethod) {
            if (zipMethod.equals("gzip")) return ".gz";
            if (zipMethod.equals("bzip2")) return ".bz2";
            if (zipMethod.equals("lzop")) return ".lzo";
            if (zipMethod.equals("default")) return "";
        throw new RuntimeException("Compression method " + zipMethod + " is not recognized.");
    }


}
