package jdbcTest.oracle.pki.test1.archive;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.KeyStore;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

import static java.lang.System.getenv;

public class KeyStoreTest {

    private static       String trustStoreWalletPath =    "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_trust\\cwallet.sso";
    private static       String keyStoreWalletPath   =    "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_pkey\\cwallet.sso";
    private static final String trustStoreWalletPathP12 = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\test_convert\\wallet_pkey\\ewallet.p12";
    private static final String keyStoreWalletPathP12   = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\test_convert\\wallet_trust\\ewallet.p12";
    private static final String trustStoreWalletPass = "walletpass123";
    private static final String keyStoreWalletPass   = "walletpass123";

    private static final String trustStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT.jks";
    private static final String trustStorePass = "walletpass123";
    private static final String keyStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.jks";
    private static final String keyStorePass = "walletpass123";

    static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=192.168.13.155)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=orcl12.168.13.155)))";
    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String pathJar = "jar:file:C:///JDBCDrivers/oracle/ojdbc8.jar!/";
    private static final boolean useSslAuthentication = true;
    private static final boolean trustStoreAuthentication = false;

    private static final String typeSslAuthentication ="PKCS12";// "sso";    "PKCS12";

    private static final String user ="min_privs";
    private static final String pass ="min_privs";

    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream(new File( "C:\\MyCertificates\\oracle\\2019_12_20_testND\\test_convert\\wallet_trust\\ewallet.p12"));
        KeyStore ks = java.security.KeyStore.getInstance("PKCS12");
        ks.load(in, "walletpass123".toCharArray());
        in.close();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, new TrustManager[]{defaultTrustManager}, null);
        SSLSocketFactory sslSocketFactory = context.getSocketFactory();


        URLClassLoader cl = new URLClassLoader(new URL[]{new URL(pathJar)});
        cl.loadClass(JDBC_DRIVER).newInstance();
        System.out.println("Connecting to database...");

        Driver driver = DriverManager.getDriver(DB_URL);

        URL url = new URL("https://192.168.1.1:8443/test/test");
        Connection conn = DriverManager.getConnection(DB_URL, getProperties());

    }

    private static Properties getProperties() {
        Properties props = new Properties();

        if (useSslAuthentication) {
            props.setProperty("oracle.net.authentication_services","(TCPS)");
        } else {
            props.setProperty("user",user);
            props.setProperty("password", pass);
        }

        String trustStoreLocation = getenv("javax.net.ssl.trustStore");

        if (trustStoreAuthentication) {
            props.setProperty("javax.net.ssl.trustStoreType","JKS");
            props.setProperty("javax.net.ssl.trustStore",trustStorePath);
            props.setProperty("javax.net.ssl.trustStorePassword",trustStorePass);

            props.setProperty("javax.net.ssl.keyStoreType", "JKS");
            props.setProperty("javax.net.ssl.keyStore", keyStorePath);
            props.setProperty("javax.net.ssl.keyStorePassword", keyStorePass);
        } else {

            if("sso".equalsIgnoreCase(typeSslAuthentication)) {
                props.setProperty("javax.net.ssl.trustStoreType","SSO");
                props.setProperty("javax.net.ssl.keyStoreType", "SSO");
            } else {
                trustStoreWalletPath = trustStoreWalletPathP12;
                keyStoreWalletPath = keyStoreWalletPathP12;



                System.getProperty("KeyStoreProvider", "OraclePKI");
                props.setProperty("javax.net.ssl.trustStoreType","PKCS12");
                props.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
                //           props.setProperty("oracle.net.ssl_server_dn_match","TRUE");

                props.setProperty("javax.net.ssl.trustStorePassword", trustStoreWalletPass);
                props.setProperty("javax.net.ssl.keyStorePassword", keyStoreWalletPass);

            }

            props.setProperty("oracle.net.authentication_services","(TCPS)");     //-
            props.setProperty("oracle.net.ssl_authentication","true");
            //      props.setProperty("oracle.net.ssl_server_dn_match", "true");
            //       props.setProperty("oracle.net.wallet_password",trustStoreWalletPass);
            props.setProperty("javax.net.ssl.trustStore",trustStoreWalletPath);
            props.setProperty("javax.net.ssl.keyStore", keyStoreWalletPath);

//            if (!useSslAuthentication) {
//                props.setProperty("javax.net.ssl.trustStorePassword", trustStoreWalletPass);
//                props.setProperty("javax.net.ssl.keyStorePassword", keyStoreWalletPass);
//            }

        }


        return props;
    }
}
