package jdbcTest.sybasessl;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

public class SybaseDenic2 {

//    static final String path = "jar:file:C:///JDBCDrivers/SAP ASE SSL/16034/jconn4.jar!/";
    static final String path = "jar:file:C:///JDBCDrivers/SAP ASE SSL/no connect 2018_12_13/jconn4.jar!/";

    static String className = "com.sybase.jdbc4.jdbc.SybDriver";
    static String url = "jdbc:sybase:Tds:";
    static String host = "192.168.14.36:5443";
    static String database = "test_sapase";  //
    static String user = "min_privs";
    static String password = "min_privs";
    static String parameters = "?ENABLE_SSL=true";
    static String connectionStr = concat_all(url, host, "/", database, parameters);

    public static void main(String args[])
    {


        try {

            URLClassLoader cl = null;

            cl = new URLClassLoader(new URL[]{new URL(path)});
            Class driverClass = cl.loadClass(className);
            System.out.println("Connecting to database...");
            Driver driver = (Driver)driverClass.newInstance();
            Connection con  = driver.connect( connectionStr, getProperties() );

//
//
//            System.setProperty("javax.net.ssl.trustStore", "C:\\JDBCDrivers\\SAP ASE SSL\\16034\\truststore.jks");
//  //         Class.forName( className ).newInstance();
//
//       //     Connection con = DriverManager.getConnection(connectionStr,user,password);
//
//
//
//            System.out.println(connectionStr);
//
//            Statement stmt = con.createStatement();
//            String SQL = "insert into tbl_ssl_cipher values(@@ssl_ciphersuite)";
//            stmt.execute(SQL);
//            stmt.close();
//            con.commit();
//
//            con.commit();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
        }


    }

    private static String concat_all(String... args){
        StringBuilder resultStr = new StringBuilder();

        for (String arg : args) {
            resultStr.append(arg);
        }

        return resultStr.toString();
    }

    private static Properties getProperties() {

        Properties properties = new Properties();

        System.setProperty("javax.net.ssl.trustStore", "C:\\JDBCDrivers\\SAP ASE SSL\\16034\\truststore.jks");
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        properties.setProperty("ENABLE_SSL", "true");

//        System.setProperty("javax.net.ssl.trustStore", trustStore);
//        System.setProperty("user", USER);
//        System.setProperty("password", PASS);
        System.setProperty("ENABLE_SSL", "true");

        return properties;
    }

}
