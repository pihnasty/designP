package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms;

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
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yaroshenko.y on 7/31/2016.
 */
class TableExtractorTask implements Runnable, Callable<Long> {

    private final String query;
    private final List<Long> params;
    private final Path filepath;
    private final DataSource datasource;
    private final String zipMethod;
    private final int fetchSize;

    TableExtractorTask(String query, List<Long> params, Path filepath, DataSource datasource, String zipMethod, int fetchSize ) {
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

    private long fetchData() {
        try (Connection con = datasource.getConnection()){
            PreparedStatement rez = null;
            try {
                try {
                    rez = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                } catch (SQLFeatureNotSupportedException e) {
                    // Ignore this exception and try to prepare statement without additional parameters
                    Log.write("The JDBC driver doesn't support additional parameters for PreparedStatement.");
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



                Path filepathZip = Paths.get(filepath.toAbsolutePath().toString()+ getExtension(zipMethod));

                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream (
                                        createCompressionOutputStream(new FileOutputStream(filepathZip.toFile()), zipMethod)
                                ),
                                "UTF-8"
                        )
                );

                final long startTime = System.currentTimeMillis();
                Log.write(String.format("Task params : %s", this.toString()));
                Log.write("Execute query...");
                rez.setFetchSize(fetchSize);
                final long fetchingStartTime = System.currentTimeMillis();
                ResultSet rs = rez.executeQuery();
                Log.write("Ready to fetching...");
                try {
                    int columnCount = rs.getMetaData().getColumnCount();
                    long rowNum = 0;
                    while (rs.next()) {
                        if ((++rowNum)%fetchSize == 1)
                            Log.write(String.format("Fetched %d rows in %.3f sec.", rowNum-1+fetchSize, (System.currentTimeMillis() - fetchingStartTime) / 1000.0));
                        StringBuilder sb = new StringBuilder();
                        for (int columnNum = 1; columnNum<=columnCount; columnNum++) {
                            // TODO Need to analyze rs.getMetaData().getColumnType()
                            sb.append("\"").append(rs.getString(columnNum).replace("\"","\"\"")).append("\"");
                            if (columnNum!=columnCount)
                                sb.append("|");
                        }
                        bw.write(sb.toString());
                        bw.newLine();
                    }
                    bw.flush();
                    Log.write(String.format("Fetching data completed, processed %d rows in %.3f sec.", rowNum, (System.currentTimeMillis() - startTime) / 1000.0));

                    return rowNum;
                } finally {
                    if (rs!=null)
                        rs.close();
                    if (bw!=null)
                        bw.close();
                }

            } finally {
                if (rez!=null)
                    rez.close();
            }


        }
        catch(Exception e)
        {
            Log.write(e);
            return -1;
        }

    }

    @Override
    public void run() {
        fetchData();
    }

    @Override
    public Long call() throws Exception {
        return fetchData();
    }

    OutputStream createCompressionOutputStream(OutputStream os, String zipMethod) {
        try {
            if (zipMethod.equalsIgnoreCase("gzip")) return new GZIPOutputStream(os);
            if (zipMethod.equalsIgnoreCase("bzip2")) return new BZip2CompressorOutputStream(os);
            if (zipMethod.equalsIgnoreCase("lzop")) {
                LzopCodec codec = new LzopCodec();
                Configuration conf = new Configuration();
                codec.setConf(conf);
                return codec.createOutputStream(os);
            }
            if (zipMethod.equalsIgnoreCase("nocompression")) return os;
        } catch (IOException e) {   Log.write(e);    }

        throw new RuntimeException("Compression method " + zipMethod + " is not recognized.");
    }

    InputStream createCompressionInputStream(InputStream is, String zipMethod) {
        try {
            if (zipMethod.equalsIgnoreCase("gzip")) return new GZIPInputStream(is);
            if (zipMethod.equalsIgnoreCase("bzip2")) return new BZip2CompressorInputStream(is);
            if (zipMethod.equalsIgnoreCase("lzop")) {
                LzopCodec codec = new LzopCodec();
                Configuration conf = new Configuration();
                codec.setConf(conf);
                CompressionInputStream cis = codec.createInputStream(is);
                return cis;
            }
            if (zipMethod.equalsIgnoreCase("nocompression")) return is;
        } catch (IOException e) {   Log.write(e);  }

        throw new RuntimeException("Compression method " + zipMethod + " is not recognized.");
    }

    String getExtension(String zipMethod) {
            if (zipMethod.equalsIgnoreCase("gzip")) return ".gz";
            if (zipMethod.equalsIgnoreCase("bzip2")) return ".bz2";
            if (zipMethod.equalsIgnoreCase("lzop")) return ".lzo";
            if (zipMethod.equalsIgnoreCase("nocompression")) return "";
        throw new RuntimeException("Compression method " + zipMethod + " is not recognized.");
    }

}


