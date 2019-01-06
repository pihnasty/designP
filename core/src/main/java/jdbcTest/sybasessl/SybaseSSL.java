package jdbcTest.sybasessl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SybaseSSL {

//    static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443/test_sapase?ENABLE_SSL=true&SSL_TRUST_ALL_CERTS=true";
//     static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443/test_sapase";
 //   static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5000/test_sapase?ENABLE_SSL=true&trustStore=C:\\JDBCDrivers\\SAP ASE SSL\\no connect 2018_12_13\\truststore.jks";
//      static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443?ENABLE_SSL=true";
 //   static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443";
//static final String path = "jar:file:C:\\JDBCDrivers\\SAP ASE SSL\\no connect 2018_12_13\\jconn4.jar!/";



    static final String JDBC_DRIVER = "com.sybase.jdbc4.jdbc.SybDriver";
    //////////////////////////   static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443?ENABLE_SSL=true?EncryptionMethod=SSL?ValidateServerCertificate=true";    // ?ValidateServerCertificate=true
    static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443";
    //static final String path = "jar:file:C:///JDBCDrivers/jconn4.jar!/";
   //static final String path = "jar:file:C:///JDBCDrivers/SAP ASE SSL/no connect 2018_12_13/jconn4.jar!/";
    static final String path = "jar:file:C:///JDBCDrivers/SAP ASE SSL/16034/jconn4.jar!/";
//    static final String path = "jar:file:C:///JDBCDrivers/SAP ASE SSL/jconn4.jar!/";

    static final String USER = "min_privs";
    static final String PASS = "min_privs";

    static final String trustStore = "C:\\JDBCDrivers\\SAP ASE SSL\\no connect 2018_12_13\\truststore.jks";




    public static void main(String[] args) throws MalformedURLException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String sql = "";
        String colunmName = "";
        PreparedStatement stmt = null;
        Connection connection = null;
        URLClassLoader cl = null;

        cl = new URLClassLoader(new URL[]{new URL(path)});
        Class driverClass = cl.loadClass(JDBC_DRIVER);
        System.out.println("Connecting to database...");
        Driver driver = (Driver)driverClass.newInstance();

        sql = getQueryText(1, sql);
        colunmName = getColumn(1, colunmName);
        connection = driver.connect( DB_URL, getProperties() );

        stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        getResult(1,rs,colunmName);

        rs.close();
    }

    private static String getQueryText(int number, String queryText) {
        switch (number) {
            case 1:
                queryText = "                        SELECT  u.name AS owner,\n" +
                    "                                o.name AS table_name\n" +
                    "                          FROM\n" +
                    "                                    test_sapase.dbo.sysobjects o\n" +
                    "                               JOIN test_sapase.dbo.sysusers u ON o.uid = u.uid\n" +
                    "                         WHERE o.type = 'U'\n" +
                    "                           AND u.name = 'dbo'--schema_name";
                break;
                default:
        }
        return queryText;
    }

    private static String getColumn(int number, String colunmName) {
        switch (number) {
            case 1:
                colunmName  = "table_name";
                break;
            default:
        }
        return colunmName;
    }

    private static void getResult(int number,  ResultSet rs, String colunmName) {
        String text="";
        try {
            while (rs.next()) {
                switch (number) {
                    case 1:
                        text = rs.getString(colunmName);
                        break;
                }
                System.out.println("text: " + text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static java.util.Properties getProperties() {

        Properties properties = new Properties();
//------------------ No worked ----------------------------------------
        //properties.setProperty("javax.net.ssl.trustStore", "C:\\JDBCDrivers\\SAP ASE SSL\\16034\\truststore.jks");
        //properties.setProperty("javax.net.ssl.trustStore", trustStore);
        //System.setProperty("ENABLE_SSL", "true");
//-end-------------- No worked ----------------------------------------

        System.setProperty("javax.net.ssl.trustStore", "C:\\JDBCDrivers\\SAP ASE SSL\\16034\\truststore.jks");

        properties.setProperty("user", USER);
        properties.setProperty("password", PASS);
        properties.setProperty("ENABLE_SSL", "true");

//        System.setProperty("user", USER);
//        System.setProperty("password", PASS);

        return properties;
    }


}

