package jdbcTest.snowflake.printed.p8;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;

public class SslConnection {
    public static void main(String[] args) throws Exception {
        //   File f = new File("C:\\MyCertificates\\snowflake\\rsa_key.p8");
        File f = new File("C:\\MyCertificates\\snowflake\\test\\MyComp\\rsa_key2.p8");
        //File f = new File("C:\\MyCertificates\\snowflake\\test\\MyComp\\rsa_key.p8");

        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();

        String encrypted = new String(keyBytes);
        // String passphrase = System.getenv("PRIVATE_KEY_PASSPHRASE");
        String passphrase = "min_privs";
        encrypted = encrypted.replace("-----BEGIN ENCRYPTED PRIVATE KEY-----", "");
        encrypted = encrypted.replace("-----END ENCRYPTED PRIVATE KEY-----", "");
        EncryptedPrivateKeyInfo pkInfo = new EncryptedPrivateKeyInfo(Base64.getMimeDecoder().decode(encrypted));
        PBEKeySpec keySpec = new PBEKeySpec(passphrase.toCharArray());
        SecretKeyFactory pbeKeyFactory = SecretKeyFactory.getInstance(pkInfo.getAlgName());
        PKCS8EncodedKeySpec encodedKeySpec = pkInfo.getKeySpec(pbeKeyFactory.generateSecret(keySpec));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey encryptedPrivateKey = keyFactory.generatePrivate(encodedKeySpec);

        Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
        String url = "jdbc:snowflake://xc03594.snowflakecomputing.com";
        Properties prop = new Properties();
        prop.put("user", "min_privs_seq");
        prop.put("account", "xc03594");
        prop.put("privateKey", encryptedPrivateKey);

        Connection conn = DriverManager.getConnection(url, prop);
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery("select * from \"sf_test\".\"INFORMATION_SCHEMA\".\"TABLES\"");
        res.next();
        System.out.println("Hello World "+res.getString(1)+"   "+res.getString(2)+"   "+res.getString(3)+"   "+res.getString(4));
        conn.close();
    }
}
