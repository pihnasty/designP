package tasks.t10727_Support_SSL_for_GreenPlum;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
import java.security.Security;
import java.sql.*;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * @author Javin Paul
 */
public class JavaToMySQL {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306";
    private static final String user = "gbuser";
    private static final String password = "Dataaccesspassword!!!";


    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String args[]) throws SQLException {
        String query = "select count(*) from GOLD_TEST.BANK_ADD";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of  the table : " + count);
            }




            socketssl("gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com",3306);



            DatabaseMetaData dbmd = con.getMetaData();
            System.out.println(dbmd.getDriverVersion());

            ResultSet rs2 = dbmd.getCatalogs ();

            while (rs2.next()) {
                System.out.println("Total number of  the table : " + rs2.getString(1));
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }




    }


    private static void socketssl(String host, int port ) {
        //    int port = 3306;
        //    String host = "gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com";

        System.setProperty("ssl", "true");
        System.setProperty("javax.net.ssl.trustStore","C:\\MyCertificates\\AmazonTrustStore.jks");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStorePassword",  "1q2w3e");

        try {
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            TrustManager[] tms = null;
            KeyStore ks = KeyStore.getInstance("JKS");




            FileInputStream fs = new FileInputStream("C:\\MyCertificates\\AmazonTrustStore.jks");
            ks.load(fs, "1q2w3e".toCharArray());
            if (fs != null)
                fs.close();
            tmf.init(ks);
            tms = tmf.getTrustManagers();

            context.init(null, tms, null);
            SSLContext.setDefault(context);
            System.out.println("context.getDefaultSSLParameters()==================="+context.getSupportedSSLParameters().getCipherSuites().toString()+"=========================");
        }catch (Throwable e) {
            //throw e;
      //      Logger.LOADER.writeError(e);
        }




        try{
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

            System.out.println("----------------------------"+socket.getSession());

            Writer out = new OutputStreamWriter(socket.getOutputStream());
            // https requires the full URL in the GET line
    //        out.write("GETTP/1.0\\r\\\n");
    //        out.write("\\r\\n");		out.flush();

            // read response
            BufferedReader in = new BufferedReader(  new InputStreamReader(socket.getInputStream()));
            int c;
            while ((c = in.read()) != -1) {
                System.out.write(c);
            }

            out.close();
            in.close();
            socket.close();
        }catch (IOException e) {        System.err.println(e);       }
    }

}
