package jdbcTest.sybasessl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SybaseSSL2 {

    static final String path = "jar:file:C:///JDBCDrivers/SAP ASE SSL/no connect 2018_12_13/jconn4.jar!/";

    static String className = "com.sybase.jdbc4.jdbc.SybDriver";
    static String url = "jdbc:sybase:Tds:";
    static String host = "192.168.14.36:5443";
    static String database = "test_sapase";  //
    static String user = "min_privs";
    static String password = "min_privs";
    static String parameters = "?ENABLE_SSL=true";
    static String connectionStr = concat_all(url, host, "/", database, parameters);




    public static void main(String[] args) throws MalformedURLException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {


//-----------------------------------------------------------------------
        URLClassLoader cl = null;


        cl = new URLClassLoader(new URL[]{new URL(path)});
        Class driverClass = cl.loadClass(className);
        System.out.println("Connecting to database...");
        Driver driver = (Driver)driverClass.newInstance();
        Connection con  = driver.connect( connectionStr, getProperties() );








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

    private static String concat_all(String... args){
        StringBuilder resultStr = new StringBuilder();

        for (String arg : args) {
            resultStr.append(arg);
        }

        return resultStr.toString();
    }


}

