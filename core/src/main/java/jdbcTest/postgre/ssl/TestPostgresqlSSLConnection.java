package jdbcTest.postgre.ssl;
import java.sql.*;
import java.util.Properties;

public class TestPostgresqlSSLConnection {
    private static final String DB_SERVER_NAME = "<public DNS name provided by Amazon RDS>";
    private static final String SSL_PORT = "5432";
    private static final String DB_SID = "<your database name>";
    private static final String DB_USER = "<username>";
    private static final String DB_PASSWORD = "<password>";
    private static final String CA_ROOT_CERT_FILE = "<full path and file name to the downloaded root certificate>";
    private static final String KEY_STORE_FILE_PATH = "";
    private static final String KEY_STORE_PASS = "";

    public static void main(String[] args) {
        final Properties properties = new Properties();
        final String connectionString = String.format(
                "jdbc:postgresql://%s:%s/%s",
                DB_SERVER_NAME, SSL_PORT, DB_SID);
        properties.put("user", DB_USER);
        properties.put("password", DB_PASSWORD);
        properties.put("ssl", "true");
        properties.put("sslmode", "verify-full");
        properties.put("sslrootcert", CA_ROOT_CERT_FILE);
        System.setProperty("javax.net.ssl.trustStore", KEY_STORE_FILE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASS);
        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish the connection.
            con = DriverManager.getConnection(connectionString, properties);

            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT 'SSL connection is established succesfully'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString(1) );
            }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
}
