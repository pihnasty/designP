package jdbcTest.snowflake.printed.pem.variant1;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.*;
import java.util.Properties;

public class Main2 {

    public final static String fileName =  "C:\\MyCertificates\\snowflake\\test\\MyComp\\snowflake_rsa_key2.pem";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
        // Security.addProvider(new BouncyCastleProvider());


        //     System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
   //     Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        /*
               Security.addProvider(new BouncyCastleProvider());
               KeyFactory factory = KeyFactory.getInstance("RSA", "BC");


        * */

        Provider provider = Security.getProvider("SunJCE");
        Security.addProvider(provider);


        KeyFactory factory = KeyFactory.getInstance("RSA","SHA1PRNG");
        try {
            PrivateKey priv = generatePrivateKey(factory,fileName );
            Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
            String url = "jdbc:snowflake://xc03594.snowflakecomputing.com";
            Properties prop = new Properties();
            prop.put("user", "min_privs_seq_open");
            prop.put("account", "xc03594");
            prop.put("privateKey", priv);

            Connection conn = DriverManager.getConnection(url, prop);
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("select * from \"sf_test\".\"INFORMATION_SCHEMA\".\"TABLES\"");
            res.next();
            System.out.println("Hello World "+res.getString(1)+"   "+res.getString(2)+"   "+res.getString(3)+"   "+res.getString(4));
            conn.close();


        } catch (InvalidKeySpecException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static PrivateKey generatePrivateKey(KeyFactory factory, String filename) throws InvalidKeySpecException, IOException {
        PrivateKey privateKey;
        try (PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)))) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
            privateKey =factory.generatePrivate(privKeySpec);
        }
        return privateKey;
    }

}
