package jdbcTest.oracle.pki.test1.testSSL;

import sun.security.x509.X509CertImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * https://www.pixelstech.net/article/1420427307-Different-types-of-keystore-in-Java----PKCS12
 */
public class TestSSL2_Experiment2 {

    private static   String trustStoreWalletPathP12 = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_trust\\ewallet.p12";
    private static   String keyStoreWalletPathP12   = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_pkey\\ewallet.p12";
    private static   String trustStoreWalletPass = "walletpass123";
    private static   String keyStoreWalletPass   = "walletpass123";


    private static   String trustStoreWalletPathP12_test = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT.p12";
    private static   String trustStoreWalletPathP12_test2 = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.p12";


    private static   String trustStoreWalletPathP12_test3 = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_trust\\ewallet3.p12";


    private static final String trustStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT.jks";
    private static final String trustStorePass = "System99";

    private static  String keyStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.jks";
    private static final String keyStorePass = "System99";




    public static void main(String[] args) throws Exception {


        String trustStoreWalletPath = "C:\\MyCertificates\\oracle\\OracleNew\\outputT.p12";
        String keyStoreWalletPath  = "C:\\MyCertificates\\oracle\\OracleNew\\outputK.p12";





 //       Certificate certT= loadingCertificate ("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");


   //     StoreCertificate("PKCS12","cn=vm-baninst1","C:\\MyCertificates\\oracle\\OracleNew\\outputT.p12", trustStoreWalletPass,certT);



  //      Certificate certK= loadingCertificate ("JKS",keyStorePath, keyStorePass, "orakey");
 /*
        StoreCertificate("PKCS12","orakey",keyStoreWalletPath, keyStoreWalletPass,certK);





        // loadingCertificateChain ("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");


        Certificate [] certKchain =loadingCertificateChain ("JKS",keyStorePath, keyStorePass,"orakey");
        StoreCertificateChain("PKCS12","C:\\MyCertificates\\oracle\\2020_01_13_test\\outputK.p12",keyStoreWalletPass,certKchain);

*/

 // Loading private key

//        Certificate certT= loadingCertificate ("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");
//        String trustStorePath2 = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT2.jks";
//        StoreCertificate("JKS","cn=vm-baninst1",trustStorePath2, trustStoreWalletPass,certT);

//        Certificate[] chain = loadingPrivateChain("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");
//        Key key = loadingPrivateKey ("JKS",keyStorePath, keyStorePass, "orakey");
//
        String trustStorePath2 = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT2.jks";
//        StoreCertificateChainKey("JKS",trustStorePath2, keyStorePass,  chain, key, "cn=vm-baninst1");



//          Certificate certT= loadingCertificate ("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");
//          StoreCertificate("JKS","cn=vm-baninst1",trustStorePath2, trustStoreWalletPass,certT);


//        Certificate[] chain2 = new Certificate[1];
//        Key key2 = loadingPrivateKey ("JKS",keyStorePath, keyStorePass, "orakey");
//        Certificate certT= loadingCertificate ("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");
//        chain2[0] = certT;
//        StoreCertificateChainKey("JKS",trustStorePath2, trustStorePass,  chain2, key2, "cn=vm-baninst1");
////--------------------------------------------------------------------------
//        Certificate[] chain = loadingPrivateChain("JKS",keyStorePath, keyStorePass, "orakey");
//        Key key = loadingPrivateKey ("JKS",keyStorePath, keyStorePass, "orakey");
//
//        String keyStorePath2 = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK2.jks";
//        StoreCertificateChainKey("JKS",keyStorePath2, keyStorePass,  chain, key, "orakey");





/*
        Certificate[] chain2 = new Certificate[1];
        Key key2 = loadingPrivateKey ("JKS",keyStorePath, keyStorePass, "orakey");
        Certificate certT= loadingCertificate ("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");
        chain2[0] = certT;
        String trustStorePathP12 = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletTP12.p12";
        StoreCertificateChainKey("PKCS12",trustStorePathP12, trustStoreWalletPass,  chain2, key2, "cn=vm-baninst1");
//--------------------------------------------------------------------------
*/

    //    loadingCertificate ("PKCS12","C:\\MyCertificates\\oracle\\2020_01_14\\ewallet2.p12",  trustStoreWalletPass, "orakey");
    //    loadingCertificate ("PKCS12","C:\\MyCertificates\\oracle\\2020_01_15\\ewallet.p12",  trustStoreWalletPass, "orakey");

//--------------------------------------------------------------------------

        String trustStorePathP12 = "C:\\MyCertificates\\oracle\\OracleNew\\ewallet_T_05.p12";
        Certificate certT= loadingCertificate ("JKS",trustStorePath, trustStorePass, "cn=vm-baninst1");
        StoreCertificate("PKCS12","cn=vm-baninst1",trustStorePathP12, trustStoreWalletPass,certT);       // "PKCS12"
//---------------------------------------------------------------------------
        Certificate[] chain = loadingPrivateChain("JKS",keyStorePath, keyStorePass, "orakey");
        Key key = loadingPrivateKey ("JKS",keyStorePath, keyStorePass, "orakey");

        String keyStorePathP12 = "C:\\MyCertificates\\oracle\\OracleNew\\ewallet_K_05.p12";
        StoreCertificateChainKey("PKCS12",keyStorePathP12, keyStoreWalletPass,  chain, key, "orakey");


//        loadingCertificate ("JKS",trustStorePathP12,  trustStoreWalletPass, "cn=vm-baninst12");
//        loadingCertificate ("PKCS12","C:\\MyCertificates\\oracle\\2020_01_14\\ewallet2.p12",  trustStoreWalletPass, "orakey");

    }

    private static Certificate loadingCertificate (String type, String store, String passord, String sertName) {
        Certificate cert=null;
        try{
            KeyStore keyStore = KeyStore.getInstance(type);
            keyStore.load(new FileInputStream(store ),passord.toCharArray());

            cert = keyStore.getCertificate(sertName);

     //       System.out.println(cert.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return cert;
    }

    private static Certificate [] loadingCertificateChain (String type, String store, String passord, String sertName)
            throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(type);
        keyStore.load(new FileInputStream(store), passord.toCharArray());

        Key pvtKey = keyStore.getKey(sertName, passord.toCharArray());
        System.out.println(pvtKey.toString());

        Certificate[] chain = keyStore.getCertificateChain(sertName);

        for (Certificate cert : chain) {
            System.out.println(((X509CertImpl) cert).getName());
            System.out.println(cert.toString());
        }
        return chain;
    }

    private static void StoreCertificate(String type, String sertName, String store, String passord, Certificate cert) {
        try{
            KeyStore keyStore = KeyStore.getInstance(type);
//  keyStore.load(new FileInputStream("output.p12"),"password".toCharArray());
            keyStore.load(null, null);;

            keyStore.setCertificateEntry(sertName, cert);

            keyStore.store(new FileOutputStream(store), passord.toCharArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void StoreCertificateChain2(String type, String sertName, String store, String passord, X509Certificate cert) {
        try{
            KeyStore keyStore = KeyStore.getInstance(type);
            keyStore.load(null, null);;

            X509Certificate[] chain = null;
            chain[0] = cert;

          //  keyStore.setKeyEntry(ALIAS, certGen.getPrivateKey(), keyStorePassword.toCharArray(), chain);


            keyStore.setCertificateEntry(sertName, cert);

            keyStore.store(new FileOutputStream(store), passord.toCharArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void StoreCertificateChain(String type, String store, String passord, Certificate[] chain) {
        try{
            KeyStore keyStore = KeyStore.getInstance(type);
//  keyStore.load(new FileInputStream("output.p12"),"password".toCharArray());
            keyStore.load(null, null);;

            for (Certificate cert : chain) {
                keyStore.setCertificateEntry(((X509CertImpl) cert).getName(), cert);
                System.out.println(cert.toString());
            }


            keyStore.store(new FileOutputStream(store), passord.toCharArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    private static Certificate[] loadingPrivateChain (String type, String store, String passord, String sertName) {
        Certificate[] chain =null;
        try{
            KeyStore keyStore = KeyStore.getInstance(type);
            keyStore.load(new FileInputStream(store),passord.toCharArray());

            Key key = keyStore.getKey(sertName, passord.toCharArray());
            chain =  keyStore.getCertificateChain(sertName);
            if (chain != null) {
                for (Certificate cert : chain) {
                    System.out.println(cert.toString());
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return chain;
    }

    private static Key loadingPrivateKey (String type, String store, String passord, String sertName) {
        Key key = null;
        Certificate[] chain =null;
        try{
            KeyStore keyStore = KeyStore.getInstance(type);
            keyStore.load(new FileInputStream(store),passord.toCharArray());

            key = keyStore.getKey(sertName, passord.toCharArray());
            chain =  keyStore.getCertificateChain(sertName);
            if (chain != null) {
                for (Certificate cert : chain) {
                    System.out.println(cert.toString());
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return key;
    }

    private static void StoreCertificateChainKey(String type, String store, String passord, Certificate[] chain, Key key, String name) {
        try{
            KeyStore keyStore = KeyStore.getInstance(type);
//  keyStore.load(new FileInputStream("output.p12"),"password".toCharArray());
            keyStore.load(null, null);;

            if (chain != null) {
                for (Certificate cert : chain) {
                    System.out.println(cert.toString());
                }
            }

            keyStore.setKeyEntry(name, key, passord.toCharArray(), chain);
            keyStore.store(new FileOutputStream(store), passord.toCharArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
