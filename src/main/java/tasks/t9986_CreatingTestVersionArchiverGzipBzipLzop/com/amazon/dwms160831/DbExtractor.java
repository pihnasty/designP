package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160831;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by yaroshenko.y on 7/31/2016.
 */

enum DriverType {
    ORACLE("oracle.jdbc.driver.OracleDriver"), TERADATA("com.teradata.jdbc.TeraDriver");
    private String driverClass;

    DriverType(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public static DriverType valueOfIgnoreCase(String s) {
        return valueOf(s.toUpperCase());
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
enum CompressMethod {
    NO_COMPRESSION("",1.00d), GZIP("gz", 0.38d), LZOP("lzo",0.64d), BZIP2("bz2", 0.32d);

    private String fileExtension;
    private double compressionEstimate;

    CompressMethod(String fileExtension, double compressionEstimate) {
        this.fileExtension = fileExtension;
        this.compressionEstimate = compressionEstimate;
    }

    public double getCompressionEstimate() {
        return compressionEstimate;
    }

    public String getFileExtension() {
        return (this==NO_COMPRESSION)? fileExtension : "."+fileExtension;
    }

    public static CompressMethod valueOfByString(String compressMethod) {
        return compressMethod.equalsIgnoreCase("nocompression") ? NO_COMPRESSION : valueOf(compressMethod.toUpperCase());
    }

}

class DbExtractor {


    private final static Map<DriverType,String> statisticsQueries;
    static {
        Map<DriverType,String> aMap = new HashMap<DriverType,String>(){
            {
                put(DriverType.ORACLE,   "WITH tmp AS (SELECT COUNT (1) cnt_rec FROM %1$s.%2$s)\n" +
                        "SELECT cnt_rec,\n" +
                        "       size_bytes_arc,\n" +
                        "       cnt_rec4batch,\n" +
                        "       cnt_batch,\n" +
                        "       cnt_rec - (cnt_rec4batch * cnt_batch) cnt_rec_round_err\n" +
                        "  FROM (SELECT t.cnt_rec,\n" +
                        "               FLOOR (t.cnt_rec / cnt_batch) cnt_rec4batch,\n" +
                        "               cnt_batch,\n" +
                        "               size_bytes_arc\n" +
                        "          FROM (SELECT     CEIL (size_bytes_arc / (1024 * 1024 * 1024) / p_cnt_slices)\n" +
                        "                         * p_cnt_slices as cnt_batch,\n" +
                        "                       size_bytes_arc\n" +
                        "                  FROM (SELECT SUM (bytes) size_bytes,\n" +
                        "                               SUM (bytes) * p_compress_estimate size_bytes_arc,\n" +
                        "                               %3$d as p_cnt_slices, \n" +
                        "                               %4$f as p_compress_estimate \n" +
                        "                         FROM sys.dba_segments ds\n" +
                        "                         WHERE     ds.segment_type IN\n" +
                        "                                      ('TABLE',\n" +
                        "                                       'TABLE PARTITION',\n" +
                        "                                       'TABLE SUBPARTITION')\n" +
                        "                               AND ds.segment_name = '%2$s'\n" +
                        "                               AND ds.owner = '%1$s') ds),\n" +
                        "               tmp t)");
                put(DriverType.TERADATA,  "SELECT cnt_rec,\n" +
                        "             SUM(currentperm)/2.5 size_bytes_arc,\n" +
                        "             FLOOR(cnt_rec / cnt_batch) cnt_rec4batch,\n" +
                        "             CASE\n" +
                        "                WHEN SUM(currentperm) / p_cnt_slices BETWEEN 2.5 * 1024 * 1024 AND 2.5 * 1024 * 1024 * 1024 THEN p_cnt_slices\n" +
                        "                WHEN SUM(currentperm) / p_cnt_slices < 2.5 * 1024 * 1024 THEN 1\n" +
                        "             ELSE (TRUNC(SUM(currentperm) / 2.5 * 1024 * 1024 * 1024 / p_cnt_slices) + 1) * p_cnt_slices\n" +
                        "             END cnt_batch,\n" +
                        "            cnt_rec - (cnt_rec4batch * cnt_batch) cnt_rec_round_err             \n" +
                        "      FROM dbc.tablesizev t\n" +
                        "           INNER JOIN (\n" +
                        "                         SELECT DISTINCT databasename,\n" +
                        "                                         TABLENAME,\n" +
                        "                                         rowcount AS cnt_rec,\n" +
                        "                                         %3$d as p_cnt_slices, \n" +
                        "                                         %4$f as p_compress_estimate \n" +
                        "                         FROM dbc.statsv\n" +
                        "                      ) s\n" +
                        "              ON s.databasename = t.databasename\n" +
                        "             AND s.TABLENAME = t.TABLENAME\n" +
                        "      WHERE t.databasename = '%1$s'\n" +
                        "        AND t.TABLENAME = '%2$s'\n" +
                        "      GROUP BY 1");
            }
        };
        statisticsQueries = Collections.unmodifiableMap(aMap);
    }


    private final DriverType driverType;
    private final String connectionUrl;
    private final String user;
    private final String password;
    private final DataSource datasource;


    DbExtractor(DriverType driver, String connectionUrl, String user, String password, int connectionCount) {
        this.driverType = driver;
        this.connectionUrl = connectionUrl;
        this.user = user;
        this.password = password;

        //Properties dbProperties = new Properties();//loaderProperties.getConnectionProperties().getProperties();
        //dbProperties.setProperty("user", user);
        //dbProperties.setProperty("password", password);

        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(connectionUrl);
        //poolProperties.setDbProperties(dbProperties);
        poolProperties.setUsername(user);
        poolProperties.setPassword(password);

        poolProperties.setDriverClassName(driver.getDriverClass());
        poolProperties.setTestOnBorrow(true);
        poolProperties.setValidationQuery("select 1 from dual");
        poolProperties.setTimeBetweenEvictionRunsMillis(30000);
        poolProperties.setMaxActive(connectionCount);
        poolProperties.setInitialSize(1);
        poolProperties.setMaxWait(60000);
        poolProperties.setRemoveAbandonedTimeout(3600);
        poolProperties.setMinEvictableIdleTimeMillis(30000);
        poolProperties.setMinIdle(1);
        poolProperties.setMaxIdle(2);
        poolProperties.setLogAbandoned(true);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        this.datasource = new DataSource();
        this.datasource.setPoolProperties(poolProperties);

        Log.write(String.format("ConnectionPool was initialized: %s", datasource.toString()));

    }


    private String getExecutorState(ThreadPoolExecutor executor) {
        StringBuilder s = new StringBuilder("@");
        s.append(Integer.toString(executor.hashCode()))
                .append( executor.isTerminated()? " [Terminated ==> " :
                        (executor.isShutdown() ?  " [Shutting down ==> " :
                                " [Running ==> "))
                .append(Long.toString(executor.getTaskCount()))
                .append(": ")
                .append(Long.toString(executor.getTaskCount()-executor.getCompletedTaskCount()-executor.getActiveCount()))
                .append(" -> ")
                .append(Long.toString(executor.getActiveCount()))
                .append(" -> ")
                .append(Long.toString(executor.getCompletedTaskCount()))
                .append("]");
        return s.toString();
    }


    void extractData(String query, String schema, String table, String outFolder, CompressMethod compressMethod, int fetchSize
                     , int threadPoolSize, int sliceCount, boolean verifyCompressedStream) {
        Log.write("Extracting data: ["+ schema+":"+table+"] started...");
        final long extractStartTime = System.currentTimeMillis();
        TableStatistics tableStatistics = getTableStatistics(schema, table, sliceCount, compressMethod.getCompressionEstimate());
        if (tableStatistics==null) {
            throw new IllegalStateException(String.format("There is no statistics for table [%s:%s].",schema,table));
        }
        Log.write("Table statistics is loaded in %.3f sec. : %s", (System.currentTimeMillis() - extractStartTime) / 1000.0, tableStatistics.toString());
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        List<Future<Long>> resultList = new ArrayList<>();


        long startPartRow = 1;
        long endPartRow=0;
        for(int i = 0; i<tableStatistics.getBatchCount(); i++) {
            Path filepath = Paths.get(outFolder).resolve(schema+"."+table+"_"+i+".csv");
            if (i<tableStatistics.getBatchCount()-1)
                endPartRow+=tableStatistics.getCountInBatch();
            else
                endPartRow = tableStatistics.getRowCount();
            List<Long> params = Arrays.asList(startPartRow, endPartRow);
            TableExtractorTask tableExtractorTask = new TableExtractorTask(query, params, filepath, datasource, compressMethod, fetchSize, verifyCompressedStream );
            //executor.execute(tableExtractorTask);
            Future<Long> result = executor.submit((Callable<Long>) tableExtractorTask);
            resultList.add(result);
            startPartRow=endPartRow+1;
        }

        executor.shutdown();

        long totalFetched = 0;
        try {
            final ThreadPoolExecutor tp = (ThreadPoolExecutor) executor;
            int waitIteration = 0;
            Instant startTime = Instant.now();
            boolean timeoutNotReached = true;
            if (!executor.isTerminated()) {
                while ((!executor.awaitTermination(30, TimeUnit.SECONDS))
                        && (timeoutNotReached = Instant.now().minusSeconds(4800).compareTo(startTime) < 0)) {

                    Runtime runtime = Runtime.getRuntime();
                    long maxMemory = runtime.maxMemory()/(1024*1024);
                    long totalMemory = runtime.totalMemory()/(1024*1024);
                    long freeMemory = runtime.freeMemory()/(1024*1024);

                    Log.write(String.format("Monitor: Iteration " + (waitIteration++)
                            + ", Memory: max="+maxMemory+"; total="+totalMemory+"; free="+freeMemory+"; used="+ (totalMemory-freeMemory)+
                            "; Executor: " + getExecutorState(tp)));

                }
            }

            if (!timeoutNotReached) {
                Log.write("Reached multithreading timeout for thread '" + Thread.currentThread().getName() + "'");
            } else {
                Log.write("Extracting data completed in %.3f sec. Executor: %s", (System.currentTimeMillis() - extractStartTime) / 1000.0, getExecutorState(tp));
            }

            for(Future<Long> future : resultList)
            {
                try
                {
                    if (future.isDone())
                    totalFetched+=future.get();
                }
                catch (InterruptedException | ExecutionException e)
                {
                    Log.write(e);
                }
            }

        } catch (InterruptedException e) {
            Log.write("The thread '%s' was interrupted.", Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }

        Log.write("Extracting data [%s:%s] finished. Fetched %d row(s).", schema, table, totalFetched );

    }

    TableStatistics getTableStatistics(String schema, String table, int sliceCount, double compressionEstimate) {
        //Log.write("getTableStatisctics: ["+ schema+":"+table+"] started...");

        String query = String.format(statisticsQueries.get(driverType), schema, table, sliceCount, compressionEstimate);

        try (Connection con = datasource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                return new TableStatistics(schema, table,rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5));
            }
        } catch (Exception e) {
            Log.write("ERROR: executing statement: %s\n %s",e.getMessage(),query);
            Log.write(e);
        }
        return null;
    }


    void close() {
        datasource.close();
    }
}


