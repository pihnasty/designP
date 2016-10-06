package tasks.t10727_Support_SSL_for_GreenPlum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class TestGreenPlumSSLConnection {
    private static final String DB_SERVER_NAME = "192.168.13.59";
    private static final String SSL_PORT = "5432";
    private static final String DB_SID = "postgres";
    private static final String DB_USER = "gpmon";
    private static final String DB_PASSWORD = "qw12345";
    private static final String CA_ROOT_CERT_FILE = "C:\\MyCertificates\\AmazonTrustStore.jks";
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
        System.setProperty("javax.net.ssl.trustStore", "C:\\MyCertificates\\AmazonTrustStore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "1q2w3e");
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
