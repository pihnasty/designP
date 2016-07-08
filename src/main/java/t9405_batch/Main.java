package t9405_batch;


import testrecorddl.Log;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


class DriverShim implements Driver {
    private Driver driver;

    DriverShim(Driver d) {
        this.driver = d;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return this.driver.acceptsURL(url);
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return this.driver.connect(url, info);
    }

    @Override
    public int getMajorVersion() {
        return this.driver.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return this.driver.getMinorVersion();
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
        return this.driver.getPropertyInfo(u, p);
    }

    @Override
    public boolean jdbcCompliant() {
        return this.driver.jdbcCompliant();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this.driver.getParentLogger();
    }

    public static void loadNew(String classname, String jarFilePath) throws Exception {
        final Class<?>[] parameters = new Class[]{URL.class};
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;
        Method method = sysclass.getDeclaredMethod("addURL", parameters);

        Path p = Paths.get(jarFilePath);
        URL u = p.toUri().toURL();
        method.setAccessible(true);
        method.invoke(sysloader, u);

        Driver d = (Driver) Class.forName(classname, true, ClassLoader.getSystemClassLoader()).newInstance();
        DriverManager.registerDriver(new DriverShim(d));
    }


}


public class Main {

    public static void main(String[] args) {

        try {
            String driverJar = "RedshiftJDBC41-1.1.10.1010.jar";
            DriverShim.loadNew("com.amazon.redshift.jdbc41.Driver", driverJar);             //   Class.forName("com.amazon.redshift.jdbc41.Driver");
            String url = "jdbc:redshift://rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com:5439/dev";


            Properties connectionProps = new Properties();
            connectionProps.put("user", "rsdbbmaster");
            connectionProps.put("password", "T8ickAvKbet3");


            List<String> sqlStatements = testrecorddl.XmlRW.readCommands("src\\data\\commandsListBatch.xml");

            int j = 0;
            Connection con = null;
            Statement st = null;
            try {
                con = DriverManager.getConnection(url, connectionProps);
                con.setAutoCommit(false);
                st = con.createStatement();


//                    for (String query : sqlStatements) st.addBatch(query);

                for (int i = 0; i < 5; i++) {
                    j++;
                    Log.logger.info("j=" + j);
                    String query = sqlStatements.get(i);
                    st.addBatch(query);
                }

                int[] batchResults = st.executeBatch();

                for (int i = 0; i < batchResults.length; i++) {
                    String description;
                    switch (batchResults[i]) {
                        case Statement.SUCCESS_NO_INFO:
                            description = "the command was processed successfully but that the number of rows affected is unknown";
                            break;
                        case Statement.EXECUTE_FAILED:
                            description = "the command failed to execute successfully and occurs only if a driver continues to process commands after a command fails";
                            break;
                        default:
                            description = String.format("the command was processed successfully (affected %d rows)", batchResults[i]);
                            break;
                    }
                    Log.logger.info(description);
                }
                System.exit(-1);

            } catch (SQLException e) {

                Log.logger.info(e.getMessage());


                SQLException exception = e;
                exception.printStackTrace();
//                while ((exception = exception.getNextException()) != null) {
//                    exception.printStackTrace();
//                }
            } finally {
              //  if (st != null) st.close();
              //  if (con != null) con.close();
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
