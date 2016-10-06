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
public class JavaToRedshift {

    // JDBC URL, username and password of MySQL server

    private static final String url = "jdbc:redshift://rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com:5439/dev";
    private static final String user = "rsdbbmaster";
    private static final String password = "T8ickAvKbet3";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String args[]) throws SQLException {

        String query = "select count(*) from ap.accounts";


        try {
             Class.forName("com.amazon.redshift.jdbc41.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of  the table : " + count);
            }


            socketssl("gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com",5439);



            DatabaseMetaData dbmd = con.getMetaData();
            System.out.println("dbmd.getDriverVersion()="+dbmd.getDriverVersion());

            ResultSet rs2 = dbmd.getCatalogs ();
            rs2 = dbmd. getSchemas ();
            while (rs2.next()) {
                System.out.println("Name of the table : " + rs2.getString(1));
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
            fs.close();
            tmf.init(ks);
            tms = tmf.getTrustManagers();

            context.init(null, tms, null);
            SSLContext.setDefault(context);
            System.out.println("context.getDefaultSSLParameters()==================="+context.getSupportedSSLParameters().getCipherSuites()+"=========================");
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
