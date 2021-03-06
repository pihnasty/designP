package jdbcTest;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.io.OutputStream;

/** Establish a SSL connection to a host and port, writes a byte and
 * prints the response. See
 * http://confluence.atlassian.com/display/JIRA/Connecting+to+SSL+services
 */
public class SSLRC4Poke {
    public static void main(String[] args) {
        String[] cyphers;
//        if (args.length < 2) {
//            System.out.println("Usage: "+SSLRC4Poke.class.getName()+" <host> <port> enable");
//            System.exit(1);
//        }
        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("ec2-52-205-81-122.compute-1.amazonaws.com", 5433);
//            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(args[0], Integer.parseInt(args[1]));

            cyphers = sslsocketfactory.getSupportedCipherSuites();
            if (args.length !=3){
                sslsocket.setEnabledCipherSuites(new String[]{
                        "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5",
                        "SSL_DH_anon_WITH_RC4_128_MD5",
                        "SSL_RSA_EXPORT_WITH_RC4_40_MD5",
                        "SSL_RSA_WITH_RC4_128_MD5",
                        "SSL_RSA_WITH_RC4_128_SHA",
                        "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                        "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                        "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                        "TLS_ECDH_RSA_WITH_RC4_128_SHA",
                        "TLS_ECDH_anon_WITH_RC4_128_SHA",
                        "TLS_KRB5_EXPORT_WITH_RC4_40_MD5",
                        "TLS_KRB5_EXPORT_WITH_RC4_40_SHA",
                        "TLS_KRB5_WITH_RC4_128_MD5",
                        "TLS_KRB5_WITH_RC4_128_SHA"
                });
            }

            InputStream in = sslsocket.getInputStream();
            OutputStream out = sslsocket.getOutputStream();

            // Write a test byte to get a reaction :)
            out.write(1);

            while (in.available() > 0) {
                System.out.print(in.read());
            }
            System.out.println("Successfully connected");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
//1 - https://www.java.com/en/download/faq/releas