package jdbcTest.oracle.pki.test1.p12;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;

public class ForgeP12Loader {
    private static  String keyStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.jks";
    private static final String keyStorePass = "System99";

    private static   String trustStoreWalletPathP12 = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_trust\\ewallet.p12";
    private static   String keyStoreWalletPathP12   = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_pkey\\ewallet.p12";
    private static   String trustStoreWalletPass = "walletpass123";
    private static   String keyStoreWalletPass   = "walletpass123";
    public static void main(String[] args) throws Exception {

        KeyStore ks;

        if (false) {
            keyStoreWalletPathP12  = keyStorePath;
            keyStoreWalletPass = keyStorePass;
            ks = KeyStore.getInstance("JKS");
        } else {
            ks = KeyStore.getInstance("PKCS12");
        }


        ClassLoader cl = ForgeP12Loader.class.getClassLoader();
        InputStream stream = new FileInputStream(keyStoreWalletPathP12 );
        ks.load(stream, keyStoreWalletPass.toCharArray());
        Key key = ks.getKey(
                "orakey"
                //"DUMMY_CERT2"
                , keyStoreWalletPass.toCharArray());
    }
}
