package jdbcTest.oracle.pki.test1.p12;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import javax.crypto.KeyGenerator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class PK12test {
    private static  String keyStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.jks";
    private static final String keyStorePass = "System99";

    private static   String trustStoreWalletPathP12 = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_trust\\ewallet.p12";
    private static   String keyStoreWalletPathP12   = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_pkey\\ewallet.p12";
    private static   String trustStoreWalletPass = "walletpass123";
    private static   String keyStoreWalletPass   = "walletpass123";


    private static   String trustStoreWalletPathP12_test = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT.p12";
    private static   String trustStoreWalletPathP12_test2 = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.p12";


    private static   String trustStoreWalletPathP12_test3 = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_trust\\ewallet3.p12";


    private static final String trustStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT.jks";
    private static final String trustStorePass = "System99";
//    private static final String keyStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.jks";
//    private static final String keyStorePass = "System99";

    public static void main(String[] args) throws Exception {

//   https://www.pixelstech.net/article/1420427307-Different-types-of-keystore-in-Java----PKCS12
//--------Создать хранилище ключей PKCS12------------------------------------------------------------------
        if (false) {
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(null, null);

                keyStore.store(new FileOutputStream(trustStoreWalletPathP12_test), trustStoreWalletPass.toCharArray());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

//--------Хранить секретный ключ---------------------------------------------------------------------------
        if (false) {
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(null, null);

                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(128);
                Key key = keyGen.generateKey();
                keyStore.setKeyEntry("secret", key, trustStoreWalletPass.toCharArray(), null);

                keyStore.store(new FileOutputStream(trustStoreWalletPathP12_test), trustStoreWalletPass.toCharArray());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
//---------Хранить закрытый ключ-------------------------------------------------------------------------
        if (false) {

            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(new FileInputStream(trustStoreWalletPathP12_test), trustStoreWalletPass.toCharArray());
                keyStore.load(null, null);
                ;

                CertAndKeyGen gen = new CertAndKeyGen("RSA", "SHA1WithRSA");
                gen.generate(1024);

                Key key = gen.getPrivateKey();
                X509Certificate cert = gen.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);

                X509Certificate[] chain = new X509Certificate[1];
                chain[0] = cert;

                keyStore.setKeyEntry("private", key, trustStoreWalletPass.toCharArray(), chain);

                keyStore.store(new FileOutputStream(trustStoreWalletPathP12_test), trustStoreWalletPass.toCharArray());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

//---------Магазин сертификат-----------------------------------------------------------------------------
        if (false) {
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(new FileInputStream(trustStoreWalletPathP12_test),trustStoreWalletPass.toCharArray());
                keyStore.load(null, null);
                ;

                CertAndKeyGen gen = new CertAndKeyGen("RSA", "SHA1WithRSA");
                gen.generate(1024);

                X509Certificate cert = gen.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);

                keyStore.setCertificateEntry("cert", cert);

                keyStore.store(new FileOutputStream(trustStoreWalletPathP12_test), trustStoreWalletPass.toCharArray());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

//---------Загрузить закрытый ключ-----------------------------------------------------------------------------
        if (true) {
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(new FileInputStream(trustStoreWalletPathP12_test), trustStoreWalletPass.toCharArray());

                Key pvtKey = keyStore.getKey("private", trustStoreWalletPass.toCharArray());
                System.out.println(pvtKey.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

//---------Загрузить цепочку сертификатов----------------------------------------------------------------------

        if (false) {
            try{
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(new FileInputStream(trustStoreWalletPathP12_test), trustStoreWalletPass.toCharArray());

                Key pvtKey = keyStore.getKey("private", trustStoreWalletPass.toCharArray());
                System.out.println(pvtKey.toString());

                java.security.cert.Certificate[] chain =  keyStore.getCertificateChain("private");
                for(java.security.cert.Certificate cert:chain){
                    System.out.println(cert.toString());
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

//---------Загрузить сертификат----------------------------------------------------------------------

        if (false) {
            try{
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(new FileInputStream(trustStoreWalletPathP12_test),  trustStoreWalletPass.toCharArray());

                java.security.cert.Certificate cert =  keyStore.getCertificate("private");

                System.out.println(cert);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

//---------Импорт и экспорт ключей и сертификатов----------------------------------------------------------------------

        if (false) {
            try{
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(new FileInputStream(trustStoreWalletPathP12_test),  trustStoreWalletPass.toCharArray());

                Key pvtKey = keyStore.getKey("private",  trustStoreWalletPass.toCharArray());
                java.security.cert.Certificate[] chain =  keyStore.getCertificateChain("private");

                KeyStore jksStore = KeyStore.getInstance("JKS");
                jksStore.load(null, null);;
                jksStore.setKeyEntry("jksPrivate", pvtKey, trustStoreWalletPass.toCharArray(), chain);
                jksStore.store(new FileOutputStream(trustStoreWalletPathP12_test2), trustStoreWalletPass.toCharArray());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

//----------Импорт и экспорт ключей и сертификатов 2----------------------------------------------------------------


        if (false) {
            try{
                KeyStore keyStore = KeyStore.getInstance("JKS");
                keyStore.load(new FileInputStream(trustStorePath),  trustStorePass.toCharArray());

                Key pvtKey = keyStore.getKey("orakey",  trustStorePass.toCharArray());
                java.security.cert.Certificate[] chain =  keyStore.getCertificateChain("orakey");

                KeyStore jksStore = KeyStore.getInstance("PKCS12");
                jksStore.load(null, null);;
                jksStore.setKeyEntry("orakey", pvtKey, trustStoreWalletPass.toCharArray(), chain);
                jksStore.store(new FileOutputStream(trustStoreWalletPathP12_test3), trustStoreWalletPass.toCharArray());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

//        KeyStore ks;
//
//        if (false) {
//            keyStoreWalletPathP12  = keyStorePath;
//            keyStoreWalletPass = keyStorePass;
//            ks = KeyStore.getInstance("JKS");
//        } else {
//            ks = KeyStore.getInstance("PKCS12");
//        }
//
//
//        ClassLoader cl = ForgeP12Loader.class.getClassLoader();
//        InputStream stream = new FileInputStream(keyStoreWalletPathP12 );
//        ks.load(stream, keyStoreWalletPass.toCharArray());
//        Key key = ks.getKey(
//                "orakey"
//                //"DUMMY_CERT2"
//                , keyStoreWalletPass.toCharArray());
    }
}
