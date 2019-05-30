package jdbcTest;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.security.Security;
import java.util.Properties;

public class SocketProtocols {

    static {
    //    Security.setProperty("jdk.tls.disabledAlgorithms", "");
    }
    public static void main(String[] args) throws Exception {

        Security.setProperty("jdk.tls.disabledAlgorithms", "");
        Security.setProperty("jdk.tls.disabledAlgorithms", "SSLv3, RC4, MD5withRSA, DH keySize < 768");
        System.out.println("Before->Security.getProperty = "+ Security.getProperty("jdk.tls.disabledAlgorithms"));
      //  SSLContext.getInstance("TLSv2");
        getSSLv3();

        Security.setProperty("jdk.tls.disabledAlgorithms", "SSLv3, RC4, MD5withRSA, DH keySize < 768");

        getSSLv3();


        System.out.println("args = " + args);

        System.out.println("After->Security.getProperty = "+ Security.getProperty("jdk.tls.disabledAlgorithms"));

    }

    private static void getSSLv3() throws IOException {

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
        SSLSocket soc = (SSLSocket) factory.createSocket();

        // Returns the names of the protocol versions which are
        // currently enabled for use on this connection.
        String[] protocols = soc.getEnabledProtocols();
        getProps();
        System.out.println("Enabled protocols:");
        for (String s : protocols) {
            System.out.println(s);
        }
    }



    //   Connection connection = DriverManager.getConnection(url, getProps());

    private static Properties getProps()
    {
        Properties properties = new Properties();
        properties.setProperty("User", "sa");
        properties.setProperty("Password", "password");
        properties.setProperty("EncryptionMethod", "SSL");
        properties.setProperty("HostNameInCertificate", "my.sqlserver.com");
        properties.setProperty("TrustStore", "<Path to trust store");
        properties.setProperty("TrustStorePassword", "password");
        properties.setProperty("ValidateServerCertificate", "true");
        properties.setProperty("CryptoProtocolVersion", "TLSv1.2");

        return properties;
    }
}