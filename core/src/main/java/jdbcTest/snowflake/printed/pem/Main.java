package jdbcTest.snowflake.printed.pem;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.*;
import java.util.Properties;

public class Main {

    protected final static Logger LOGGER = Logger.getLogger(Main.class);

    public final static String RESOURCES_DIR =  "C:\\MyCertificates\\snowflake\\test\\MyComp\\snowflake_rsa_key2.pem";

    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        LOGGER.info("BouncyCastle provider added.");

        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        try {
            PrivateKey priv = generatePrivateKey(factory, RESOURCES_DIR );
            LOGGER.info(String.format("Instantiated private key: %s", priv));

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


        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PrivateKey generatePrivateKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFile pemFile = new PemFile(filename);
        byte[] content = pemFile.getPemObject().getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privKeySpec);
    }

    private static PublicKey generatePublicKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFile pemFile = new PemFile(filename);
        byte[] content = pemFile.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }
}
