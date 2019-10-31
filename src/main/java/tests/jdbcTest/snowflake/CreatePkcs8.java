package tests.jdbcTest.snowflake;

import net.snowflake.client.jdbc.internal.org.bouncycastle.openssl.PKCS8Generator;
import net.snowflake.client.jdbc.internal.org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import net.snowflake.client.jdbc.internal.org.bouncycastle.openssl.jcajce.JcaPKCS8Generator;
import net.snowflake.client.jdbc.internal.org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8EncryptorBuilder;
import net.snowflake.client.jdbc.internal.org.bouncycastle.operator.OperatorCreationException;
import net.snowflake.client.jdbc.internal.org.bouncycastle.operator.OutputEncryptor;
import net.snowflake.client.jdbc.internal.org.bouncycastle.util.io.pem.PemObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class CreatePkcs8{
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, OperatorCreationException, InvalidKeySpecException {

        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = kpGen.generateKeyPair();


        //unencrypted form of PKCS#8 file
        JcaPKCS8Generator gen1 = new JcaPKCS8Generator(keyPair.getPrivate(), null);
        PemObject obj1 = gen1.generate();
        StringWriter sw1 = new StringWriter();
        try (JcaPEMWriter pw = new JcaPEMWriter(sw1)) {
            pw.writeObject(obj1);
        }
        String pkcs8Key1 = sw1.toString();
        FileOutputStream fos1 = new FileOutputStream("C:\\MyCertificates\\snowflake\\test\\un_key.p8");
        fos1.write(pkcs8Key1.getBytes());
        fos1.flush();
        fos1.close();

        //encrypted form of PKCS#8 file
        JceOpenSSLPKCS8EncryptorBuilder encryptorBuilder = new JceOpenSSLPKCS8EncryptorBuilder(PKCS8Generator.PBE_SHA1_RC2_128);
        encryptorBuilder.setRandom(new SecureRandom());
        encryptorBuilder.setPasssword("min_privs".toCharArray()); // password
        OutputEncryptor encryptor = encryptorBuilder.build();

        JcaPKCS8Generator gen2 = new JcaPKCS8Generator(keyPair.getPrivate(), encryptor);
        PemObject obj2 = gen2.generate();
        StringWriter sw2 = new StringWriter();
        try (JcaPEMWriter pw = new JcaPEMWriter(sw2)) {
            pw.writeObject(obj2);
        }
        String pkcs8Key2 = sw2.toString();
        FileOutputStream fos2 = new FileOutputStream("C:\\MyCertificates\\snowflake\\test\\rsa_key_oleh.p8");
        fos2.write(pkcs8Key2.getBytes());
        fos2.flush();
        fos2.close();
    }
}