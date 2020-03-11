package jdbcTest.oracle.pki.test1.in_work;


import oracle.security.pki.OraclePKIProvider;

import java.net.URL;
import java.net.URLClassLoader;
import java.security.Security;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JksOracleRootUserDifferent {

    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_URL =  "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=192.168.13.155)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=orcl12.168.13.155)))";
    private static final String pathJar = "jar:file:C:///JDBCDrivers/oracle/ojdbc8.jar!/";


    private static final String user ="min_privs";
    private static final String pass ="min_privs";

    private static final String trustStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT.jks";
    private static final String trustStorePass = "System99";
    private static final String keyStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.jks";
    private static final String keyStorePass = "System99";

    private static final String trustStoreWalletPath = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_trust\\cwallet.sso";
    private static final String keyStoreWalletPath   = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_pkey\\cwallet.sso";
    private static final String walletPass = "System99";  // walletpass123

    private static final boolean useSslAuthentication = true;
    private static final boolean trustStoreAuthentication = false;

    public static void main(String[] args) {
        Security.addProvider(new OraclePKIProvider());
        Security.insertProviderAt(new OraclePKIProvider(),3);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            URLClassLoader cl = new URLClassLoader(new URL[]{new URL(pathJar)});
            cl.loadClass(JDBC_DRIVER).newInstance();
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(DB_URL, getProperties());
            System.out.println("Creating statement...");
            int i = 5;
            String colunmName = "vals";
            String sql = getSql(i);
            stmt = conn.prepareStatement(sql);
            List<String> columnCells = getColumn(stmt,colunmName);
            System.out.println(columnCells );
            System.out.println("finish");
        } catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        if (useSslAuthentication) {
            props.setProperty("oracle.net.authentication_services","(TCPS)");
        } else {
            props.setProperty("user",user);
            props.setProperty("password", pass);
        }

        if (trustStoreAuthentication) {
            props.setProperty("javax.net.ssl.trustStoreType","JKS");
            props.setProperty("javax.net.ssl.trustStore",trustStorePath);
            props.setProperty("javax.net.ssl.trustStorePassword",trustStorePass);

            props.setProperty("javax.net.ssl.keyStoreType", "JKS");
            props.setProperty("javax.net.ssl.keyStore", keyStorePath);
            props.setProperty("javax.net.ssl.keyStorePassword", keyStorePass);
        } else {
            props.setProperty("javax.net.ssl.trustStoreType","SSO");   //--
            props.setProperty("oracle.net.authentication_services","(TCPS)");     //-
            props.setProperty("oracle.net.ssl_authentication","true");
    //     props.setProperty("oracle.net.ssl_server_dn_match", "true");

            props.setProperty("javax.net.ssl.trustStore",trustStoreWalletPath);

    //       props.setProperty("oracle.net.wallet_password",walletPass);

/*
            props.setProperty("javax.net.ssl.keyStoreType", "JKS");
            props.setProperty("javax.net.ssl.keyStore", keyStorePath);
            props.setProperty("javax.net.ssl.keyStorePassword", keyStorePass);
*/

            props.setProperty("javax.net.ssl.keyStore", keyStoreWalletPath);
            props.setProperty("javax.net.ssl.keyStoreType", "SSO");

            if (!useSslAuthentication) {
                props.setProperty("javax.net.ssl.trustStorePassword", walletPass);
                props.setProperty("javax.net.ssl.keyStorePassword", walletPass);
            }
        }


        return props;
    }

    private static  List<String>  getColumn(PreparedStatement stmt, String colunmName) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        String text = "";
        List<String> columnCells = new ArrayList<>();
        while (rs.next()) {
            columnCells.add( rs.getString(colunmName) );
            System.out.println("text: " + text);
        }
        rs.close();
        return columnCells;
    }

    private static String getSql(int i) {
        String sql = "";
        switch (i) {
            case 5:
                sql = "select sys_context('userenv','service_name') as vals from dual\n" +
                        "union all\n" +
                        "select sys_context('userenv', 'network_protocol') from dual\n" +
                        "union all\n" +
                        "select sys_context('userenv', 'session_user') from dual";
                break;

            default:
                sql = "";
        }
        return sql;
    }

}
