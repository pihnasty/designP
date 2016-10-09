package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160831;

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

enum CharsetName {
    UTF8("UTF-8"), UTF16("UTF-16");

    private final String charsetName;

    CharsetName(String charsetName) {
        this.charsetName = charsetName;

    }
    public String getCharsetName() {
        return charsetName;
    }

}

class TableExtractorTask implements Runnable, Callable<Long> {

    private final String query;
    private final List<Long> params;
    private final Path filepath;
    private final DataSource datasource;
    private CompressMethod compressMethod;
    private final int fetchSize;
    private final boolean verifyCompressedStream;
    private CharsetName charsetName = CharsetName.UTF16;


    TableExtractorTask(String query, List<Long> params, Path filepath, DataSource datasource, CompressMethod compressMethod, int fetchSize, boolean verifyCompressedStream ) {
        this.query = query;
        this.params = params;
        this.filepath = filepath;
        this.datasource = datasource;
        this.compressMethod = compressMethod;
        this.fetchSize = fetchSize;
        this.verifyCompressedStream = verifyCompressedStream;
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
                ", compression='" + compressMethod.toString() + '\'' +
                ", compressionEstimate=" + Double.toString(compressMethod.getCompressionEstimate()) +
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
                Path filepathZip = Paths.get(filepath.toAbsolutePath().toString()+ compressMethod.getFileExtension());

                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream (
                                        createCompressionOutputStream(new FileOutputStream(filepathZip.toFile()))
                                ),
                                charsetName.getCharsetName()
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
                    StringBuilder csb = new StringBuilder();
                    for (int columnNum = 1; columnNum<=columnCount; columnNum++) {
                        csb.append("\"").append(rs.getMetaData().getColumnName(columnNum).replace("\"","\"\"")).append("\"");
                        if (columnNum!=columnCount)
                            csb.append("|");
                    }
                    bw.write(csb.toString());
                    bw.newLine();
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
                        bw.close();
                    if ((compressMethod!=CompressMethod.NO_COMPRESSION) && (verifyCompressedStream)) {
                        // TODO Unzip file to the out folder
                        unzipFileForVerification(filepathZip);
                    }
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

    private void unzipFileForVerification(Path filepath) throws FileNotFoundException {
        BufferedReader rw = null;
        BufferedWriter bw = null;
        CompressMethod tempCompressMethod = compressMethod;
        try {
            rw = new BufferedReader(
                    new InputStreamReader(
                            createCompressionInputStream(new FileInputStream(filepath.toFile())),
                            charsetName.getCharsetName()
                    )
            );
            Path filepathZip = Paths.get(filepath.toAbsolutePath().toString().substring(0,filepath.toAbsolutePath().toString().lastIndexOf(compressMethod.getFileExtension()))+"~");
            compressMethod = CompressMethod.NO_COMPRESSION;
            bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream (
                                    createCompressionOutputStream(new FileOutputStream(filepathZip.toFile()))
                            ),
                            charsetName.getCharsetName()
                    )
            );
            String s;
            while ( ( s = rw.readLine()) !=null  ) {
                System.out.println(s);
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {   e.printStackTrace();    }
        finally {
            if (rw !=null) try {
                rw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bw!=null)
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            compressMethod = tempCompressMethod;
        }
    }

    private OutputStream createCompressionOutputStream(OutputStream os) {
        try {
            switch (compressMethod) {
                case NO_COMPRESSION:
                    return os;
                case GZIP:
                    return new GZIPOutputStream(os);
                case BZIP2:
                    return new BZip2CompressorOutputStream(os);
                case LZOP: {
                    LzopCodec codec = new LzopCodec();
                    Configuration conf = new Configuration();
                    codec.setConf(conf);
                    return codec.createOutputStream(os);
                }
            }
        } catch (IOException e) {
            Log.write(e);
        }
        throw new RuntimeException("Compression method " + compressMethod.toString() + " is not recognized.");
    }

    InputStream createCompressionInputStream(InputStream is) {
        try {
            switch (compressMethod) {
                case NO_COMPRESSION:
                    return is;
                case GZIP:
                    return new GZIPInputStream(is);
                case BZIP2:
                    return new BZip2CompressorInputStream(is);
                case LZOP: {
                    LzopCodec codec = new LzopCodec();
                    Configuration conf = new Configuration();
                    codec.setConf(conf);
                    CompressionInputStream cis = codec.createInputStream(is);
                    return cis;
                }
            }
        } catch (IOException e) {
            Log.write(e);
        }
        throw new RuntimeException("Compression method " + compressMethod.toString() + " is not recognized.");
    }

}


