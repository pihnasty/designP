package tests.verticaTest;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.*;
import java.util.Properties;

public class VerticaTestSSL {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchProviderException, NoSuchAlgorithmException {
        // Assume using JDBC 4.0 driver on JVM 6+. No driver loading needed.
        Properties myProp = new Properties();
        myProp.put("user", "userfoo_tls");
        myProp.put("password", "userfoo_tls99");
        // Set two backup hosts to be used if connecting to the first host
        // fails. All of these hosts will be tried in order until the connection
        // succeeds or all of the connections fail.
  //      myProp.put("BackupServerNode", "VerticaHost02,VerticaHost03");




        System.setProperty("javax.net.debug", "ssl");
        System.setProperty("ssl", "true");
        System.setProperty("javax.net.ssl.trustStore", "C:\\MyCertificates\\vertica\\v15_SSL\\trust_22_02_17.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        myProp.put("javax.net.debug", "ssl");
        myProp.put("ssl", "true");
        myProp.put("javax.net.ssl.trustStore", "C:\\MyCertificates\\vertica\\v15_SSL\\trust_22_02_17.jks");
        myProp.put("javax.net.ssl.trustStorePassword", "123456");
        myProp.put("sun.security.ssl.allowUnsafeRenegotiation", "true");


     //   URL url = new URL("jdbc:vertica://ec2-52-205-81-122.compute-1.amazonaws.com:5433/VERTICA_DWH");
     //   Connection conn = (Connection) url.openConnection();
        Class.forName("com.vertica.jdbc.Driver");

        Connection conn;




        try {
            // The connection string is set to try to connect to a known
            // bnad host (in this case, a host that never existed).
          conn = DriverManager.getConnection(  "jdbc:vertica://ec2-52-205-81-122.compute-1.amazonaws.com:5433/VERTICA_DWH", myProp);
         //     conn.setSSLSocketFactory(sslsocketfactory);
         //   conn.setSSLSocketFactory(sslsocketfactory);
         System.out.println("Connected!");
            // Query system to table to see what node we are connected to.
            // Assume a single row in response set.
            Statement stmt =conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT node_name FROM v_monitor.current_session;");
            rs.next();
            System.out.println("Connected to node " + rs.getString(1).trim());
            // Done with connection.
            ( conn).close();
        } catch (SQLException e) {
            // Catch-all for other exceptions
            e.printStackTrace();
        }
    }
}


// [Vertica][VJDBC](100024) IOException while communicating with server: java.net.SocketException: Software caused connection abort: recv failed.