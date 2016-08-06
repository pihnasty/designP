package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160804;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaroshenko.y on 7/31/2016.
 */


class DbExtractor {

    private final String connectionUrl;
    private final String user;
    private final String password;
    private final DataSource datasource;

    DbExtractor(String connectionUrl, String user, String password) {
        this.connectionUrl = connectionUrl;
        this.user = user;
        this.password = password;

        Properties dbProperties = new Properties();//loaderProperties.getConnectionProperties().getProperties();
        dbProperties.setProperty("user", user);
        dbProperties.setProperty("password", password);

        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(connectionUrl);
        poolProperties.setDbProperties(dbProperties);
        poolProperties.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        poolProperties.setTestOnBorrow(true);
        poolProperties.setValidationQuery("select 1 from dual");
        poolProperties.setTimeBetweenEvictionRunsMillis(30000);
        poolProperties.setMaxActive(20);
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

        Log.print(String.format("ConnectionPool was initialized: %s", datasource.toString()));
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


    void extractData(String query, String schema, String table, String outFolder, String zipMethod, int fetchSize, int threadPoolSize) {
        Log.print("Extracting data: ["+ schema+":"+table+"] started...");
        final long extractStartTime = System.currentTimeMillis();
        TableStatistics tableStatistics = getTableStatistics(schema, table);
        Log.print(String.format("Table statistics is loaded in %.3f sec.", (System.currentTimeMillis() - extractStartTime) / 1000.0));
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        long startPartRow = 1;
        long endPartRow=0;
        for(int i = 0; i<tableStatistics.getBatchCount(); i++) {
            Path filepath = Paths.get(outFolder + "\\"+schema+"."+table+"_"+i+".csv");    //
            if (i<tableStatistics.getBatchCount()-1)
                endPartRow+=tableStatistics.getCountInBatch();
            else
                endPartRow = tableStatistics.getRowCount();
            List<Long> params = Arrays.asList(endPartRow, startPartRow);
            TableExtractorTask tableExtractorTask = new TableExtractorTask(query, params, filepath, datasource, zipMethod, fetchSize );
            executor.execute(tableExtractorTask);
            startPartRow=endPartRow+1;
        }

        executor.shutdown();


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

                    Log.print(String.format("Monitor: Iteration " + (waitIteration++)
                            + ", Memory: max="+maxMemory+"; total="+totalMemory+"; free="+freeMemory+"; used="+ (totalMemory-freeMemory)+
                            "; Executor: " + getExecutorState(tp)));

                }
            }

            if (!timeoutNotReached) {
                Log.print("Reached multithreading timeout for thread '" + Thread.currentThread().getName() + "'");
            } else {
                Log.print(String.format("Extracting data completed in %.3f sec. Executor: %s", (System.currentTimeMillis() - extractStartTime) / 1000.0, getExecutorState(tp)));
            }
        } catch (InterruptedException e) {
            Log.print(String.format("The thread '%s' was interrupted.", Thread.currentThread().getName()));
            Thread.currentThread().interrupt();
        }

        Log.print("Extracting data ["+ schema+":"+table+"] finished.");
    }

    TableStatistics getTableStatistics(String schema, String table) {
        //Log.print("getTableStatisctics: ["+ schema+":"+table+"] started...");

        String query = String.format("WITH tmp AS (SELECT COUNT (1) cnt_rec FROM %1$s.%2$s)\n" +
        "SELECT cnt_rec,\n" +
        "       size_bytes_arc,\n" +
        "       cnt_rec4batch,\n" +
        "       cnt_batch,\n" +
        "       cnt_rec - (cnt_rec4batch * cnt_batch) cnt_rec_round_err\n" +
        "  FROM (SELECT t.cnt_rec,\n" +
        "               FLOOR (t.cnt_rec / cnt_batch) cnt_rec4batch,\n" +
        "               cnt_batch,\n" +
        "               size_bytes_arc\n" +
        "          FROM (SELECT     FLOOR (size_bytes_arc / (1024 * 1024 * 1024) / 8)\n" +
        "                         * 8\n" +
        "                       + 8\n" +
        "                          cnt_batch,\n" +
        "                       size_bytes_arc\n" +
        "                  FROM (SELECT SUM (bytes) size_bytes,\n" +
        "                               SUM (bytes) * 0.38 size_bytes_arc\n" +
        "                          FROM sys.dba_segments ds\n" +
        "                         WHERE     ds.segment_type IN\n" +
        "                                      ('TABLE',\n" +
        "                                       'TABLE PARTITION',\n" +
        "                                       'TABLE SUBPARTITION')\n" +
        "                               AND ds.segment_name = '%2$s'\n" +
        "                               AND ds.owner = '%1$s') ds),\n" +
        "               tmp t)", schema.toUpperCase(), table.toUpperCase());

        try (Connection con = datasource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            String result = "";
            if (rs.next()) {
                return new TableStatistics(schema, table,rs.getLong(1),rs.getLong(2),rs.getLong(3),rs.getLong(4),rs.getLong(5));
            }

        } catch (Exception e) {
            Log.print(String.format("ERROR: executing statement: %s\n %s",e.getMessage(),query));
            e.printStackTrace();
        }
        return null;
    }


    void close() {
        datasource.close();
    }
}


