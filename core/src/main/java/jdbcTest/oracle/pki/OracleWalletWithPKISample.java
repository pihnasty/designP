package jdbcTest.oracle.pki;

import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class OracleWalletWithPKISample {

//    private static final String walletStoreFile =   "C:\\Users\\bondar.v\\Downloads\\Microsoft.SkypeApp_kzf8qxf38zg5c!App\\All\\truststoreP2\\ewallet.p12";
    //  "E:\\app\\Pihnastyi.O\\product\\12.1.0\\dbhome_1\\BIN\\truststoreP3\\ewallet.p12";
    private static final String keyStoreFile = "C:\\Users\\bondar.v\\Downloads\\Microsoft.SkypeApp_kzf8qxf38zg5c!App\\All\\ewalletK.jks";
    private static final String storeType = "JKS";
    private static final String keyPassword = "System99";
    private static final String oracleAuthServices = "(TCPS)";

//    private static final String host = "192.168.13.155";
//    private static final String port = "2484";
//    private static final String serviceName = "orcl12.168.13.155";
//    private static final String userName = "min_privs";
//    private static final String password = "min_privs";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=192.168.13.155)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=orcl12.168.13.155)))";

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Security.insertProviderAt(new oracle.security.pki.OraclePKIProvider(), 3);

        Properties prop = new Properties();
        prop.setProperty("oracle.net.authentication_services","(TCPS)");
        prop.setProperty("user", "min_privs");
        prop.setProperty("password", "min_privs");
        prop.setProperty("javax.net.ssl.trustStore",keyStoreFile);
        prop.setProperty("javax.net.ssl.trustStoreType","PKCS12");
        prop.setProperty("javax.net.ssl.trustStorePassword","min_privs");




        try (Connection connection = DriverManager.getConnection(url, prop)) {
            Statement stat = connection.createStatement();
            ResultSet res = stat.executeQuery("select 1 from dual");
            res.next();
            System.out.println("Hello World " + res.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
