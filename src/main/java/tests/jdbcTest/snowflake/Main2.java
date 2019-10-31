package tests.jdbcTest.snowflake;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;

import java.io.FileReader;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.Security;

public class Main2 {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        String path = "C:\\MyCertificates\\snowflake\\rsa_key.p8";
        String passphrase = "min_privs";//System.getenv("PRIVATE_KEY_PASSPHRASE");
        bcParcer(path, passphrase);
    }

    private static PrivateKey bcParcer(String keyFilePath, String password) throws Exception {
        PEMParser pemParser = new PEMParser(new FileReader(Paths.get(keyFilePath).toFile()));
        PKCS8EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = (PKCS8EncryptedPrivateKeyInfo) pemParser.readObject();
        pemParser.close();

        InputDecryptorProvider pkcs8Prov = new JceOpenSSLPKCS8DecryptorProviderBuilder().build(password.toCharArray());
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME);
        PrivateKeyInfo decryptedPrivateKeyInfo = encryptedPrivateKeyInfo.decryptPrivateKeyInfo(pkcs8Prov);
        PrivateKey privateKey = converter.getPrivateKey(decryptedPrivateKeyInfo);
        System.out.println(privateKey);
        return privateKey;

    }
}


/*
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>dbbest.sample</groupId>
    <artifactId>ssl-test</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.buncycastle</groupId>
            <artifactId>bcprov-ext-jdk15on</artifactId>
            <version>1.61</version>
        </dependency>
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcpkix-jdk15on</artifactId>
            <version>1.61</version>
        </dependency>
    </dependencies>

</project>
* */
