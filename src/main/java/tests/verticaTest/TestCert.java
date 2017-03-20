package tests.verticaTest;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class TestCert {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.debug", "ssl");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        System.setProperty("javax.net.ssl.trustStore", "C:\\MyCertificates\\vertica\\v15_SSL\\truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "vertica");


        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        URL url = new URL("jdbc:vertica://ec2-52-205-81-122.compute-1.amazonaws.com:5433/VRT_DWH");
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setSSLSocketFactory(sslsocketfactory);

        InputStream inputstream = conn.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

        String string = null;
        while ((string = bufferedreader.readLine()) != null) {
            System.out.println("Received " + string);
        }
    }
}
