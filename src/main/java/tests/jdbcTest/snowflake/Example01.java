package tests.jdbcTest.snowflake;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class Example01 {
    public static void main(String[] args) throws Exception {
        KeyStore keystore;

        File certFile = new File("C:\\MyCertificates\\snowflake\\rsa_key.p8");
        String password = new String("min_privs");

        try {
            keystore = KeyStore.getInstance("pkcs12");
            keystore.load(new FileInputStream(certFile),
                    password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}