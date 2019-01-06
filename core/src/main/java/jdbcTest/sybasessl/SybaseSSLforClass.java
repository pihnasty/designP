package jdbcTest.sybasessl;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SybaseSSLforClass {

//    static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443/test_sapase?ENABLE_SSL=true&SSL_TRUST_ALL_CERTS=true";
//     static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443/test_sapase";
 //   static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5000/test_sapase?ENABLE_SSL=true&trustStore=C:\\JDBCDrivers\\SAP ASE SSL\\no connect 2018_12_13\\truststore.jks";
//      static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443?ENABLE_SSL=true";
 //   static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443";
//static final String path = "jar:file:C:\\JDBCDrivers\\SAP ASE SSL\\no connect 2018_12_13\\jconn4.jar!/";



    static final String JDBC_DRIVER = "com.sybase.jdbc4.jdbc.SybDriver";
    static final String DB_URL = "jdbc:sybase:Tds:192.168.14.36:5443?ENABLE_SSL=true";    // ?ValidateServerCertificate=true
    static final String path = "jar:file:C:///JDBCDrivers/SAP ASE SSL/no connect 2018_12_13/jconn4.jar!/";


    static final String USER = "min_privs";
    static final String PASS = "min_privs";

    static final String trustStore = "C:\\JDBCDrivers\\SAP ASE SSL\\no connect 2018_12_13\\truststore.jks";



    public static void main(String[] args) throws MalformedURLException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String sql = "";
        String colunmName = "";
        PreparedStatement stmt = null;
        Connection connection = null;


        Class.forName(JDBC_DRIVER).newInstance();
        connection = DriverManager.getConnection( DB_URL, getProperties() );


        sql = getQueryText(1, sql);
        colunmName = getColumn(1, colunmName);


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

    private static Properties getProperties() {

        Properties properties = new Properties();

        properties.setProperty("javax.net.ssl.trustStore", trustStore);
        properties.setProperty("user", USER);
        properties.setProperty("password", PASS);
        properties.setProperty("ENABLE_SSL", "true");


        return properties;
    }


}

