package jdbcTest.oracle.pki.test1.another;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import oracle.security.pki.OraclePKIProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.Collator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class KeyStoreTest {
    static String b = System.getProperty("KeyStoreType", "PKCS12");
    static String c = System.getProperty("KeyStoreProvider", "OraclePKI");
    private KeyStore d;
    private char[] e;

    public static void main(String[] var0) {
        String var1 = "C:\\MyCertificates\\oracle\\2019_12_20_testND\\test_convert\\wallet_trust\\ewallet.p12";
       // var1="C:\\MyCertificates\\oracle\\2019_12_20_testND\\wallet_pkey\\ewallet.p12";
        String var2 = "walletpass123";
        String var3 = "abcdefghp";
        Security.addProvider(new OraclePKIProvider());

        KeyStoreTest var8 = null;

        try {
            var8 = new KeyStoreTest(var1, var2);
        } catch (Exception var7) {
            System.out.println("Could not create/load keystore");
            var7.printStackTrace();
            System.exit(-1);
        }

        try {
            var8.a(var3);
        } catch (Throwable var6) {
            System.out.println("Error occured during tests");
            System.exit(-1);
        }

        System.out.println("Success!");
    }

    public KeyStoreTest(String var1, String var2) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
        this.d = KeyStore.getInstance(b, c);
        System.out.println("--------------------------------------------");
        System.out.println("Created KeyStore of type " + b + " using provider " + this.d.getProvider());
        FileInputStream var3 = null;
        if (var1 != null) {
            var3 = new FileInputStream(var1);
        }

        this.e = var2.toCharArray();
        this.d.load(var3, this.e);
        System.out.println("Loaded KeyStore from " + var1 + " with password " + var2);
        System.out.println("--------------------------------------------");
    }

    public void a(String var1) throws KeyStoreException {
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("Printing information about the KeyStore:");
        System.out.println("KeyStore is of type " + this.d.getType() + ".");
        System.out.println("KeyStore provider is " + this.d.getProvider());
        System.out.println("KeyStore contains " + this.d.size() + " entries.");
        System.out.println("---------------------------------------------");
        System.out.println();

        for(int var2 = 0; var2 < var1.length(); ++var2) {
            switch(var1.charAt(var2)) {
                case 'a':
                    try {
                        this.a(this.d);
                    } catch (KeyStoreException var12) {
                        System.out.println("Print provider Test Failed");
                        var12.printStackTrace();
                    }
                    break;
                case 'b':
                    try {
                        this.b(this.d);
                    } catch (KeyStoreException var11) {
                        System.out.println("Certificate Entry Test Failed");
                        var11.printStackTrace();
                    }
                    break;
                case 'c':
                    try {
                        this.c(this.d);
                    } catch (KeyStoreException var10) {
                        System.out.println("Key Entry Test Failed");
                        var10.printStackTrace();
                    }
                    break;
                case 'd':
                    try {
                        this.d(this.d);
                    } catch (KeyStoreException var9) {
                        System.out.println("Print Type Test Failed");
                        var9.printStackTrace();
                    }
                    break;
                case 'e':
                    try {
                        this.e(this.d);
                    } catch (KeyStoreException var8) {
                        System.out.println("Print Size Test Failed");
                        var8.printStackTrace();
                    }
                    break;
                case 'f':
                    try {
                        this.f(this.d);
                    } catch (KeyStoreException var7) {
                        System.out.println("Alias Test Failed");
                        var7.printStackTrace();
                    }
                    break;
                case 'g':
                    try {
                        this.g(this.d);
                    } catch (KeyStoreException var6) {
                        System.out.println("DeleteEntry Test Failed");
                        var6.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        this.h(this.d);
                    } catch (KeyStoreException var5) {
                        System.out.println("GetCertificate Test Failed");
                        var5.printStackTrace();
                    }
                    break;
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                default:
                    System.out.println("Unknown test " + var1.charAt(var2) + ", ignoring");
                    break;
                case 'p':
                    try {
                        this.a();
                    } catch (GeneralSecurityException var4) {
                        System.out.println("PrintTest Failed");
                        var4.printStackTrace();
                    }
            }
        }

    }

    private Enumeration a(Enumeration var1) {
        TreeSet var2 = new TreeSet(Collator.getInstance());

        while(var1.hasMoreElements()) {
            var2.add(var1.nextElement());
        }

        Iterator var3 = var2.iterator();
        Vector var4 = new Vector(var2.size());

        for(int var5 = 0; var3.hasNext(); ++var5) {
            var4.add(var3.next());
        }

        return var4.elements();
    }

    private void a() throws GeneralSecurityException {
        System.out.println("Printing information about KeyStore:");
        System.out.println("KeyStore is of type " + this.d.getType() + ".");
        System.out.println("KeyStore provider is " + this.d.getProvider());
        System.out.println("KeyStore contains " + this.d.size() + " entries.");
        Enumeration var1 = this.a(this.d.aliases());

        for(int var2 = 0; var1.hasMoreElements(); ++var2) {
            String var3 = (String)var1.nextElement();
            System.out.println("Entry[" + var2 + "]");
            System.out.println("  Alias: \"" + var3 + "\"");
            System.out.println("  Created At: " + this.d.getCreationDate(var3));
            if (this.d.isKeyEntry(var3)) {
                System.out.println("Private key " + this.d.getKey(var3, this.e));
                if (this.d.getCertificate(var3) != null) {
                    System.out.println("User certificate:\n" + CertificateTest.a(this.d.getCertificate(var3)));
                    Certificate[] var4 = this.d.getCertificateChain(var3);

                    for(int var5 = 1; var5 < var4.length; ++var5) {
                        System.out.println(" Signed by: ");
                        if (var4[var5] instanceof X509Certificate) {
                            System.out.println(((X509Certificate)var4[var5]).getSubjectDN());
                        } else {
                            System.out.println(var4[var5]);
                        }
                    }
                } else {
                    System.out.println("No public certificate present");
                }
            } else if (this.d.isCertificateEntry(var3)) {
                System.out.println("Trusted certificate\n" + CertificateTest.a(this.d.getCertificate(var3)));
            } else {
                System.out.println("Unknown entry type for alias: " + var3);
            }
        }

    }

    private void a(KeyStore var1) throws KeyStoreException {
        System.out.println();
        System.out.println("Using provider " + var1.getProvider());
        System.out.println();
    }

    private void b(KeyStore var1) throws KeyStoreException {
        String var2 = "CN=akoyfman-sun";
        String var3 = "CN=JavaSSL Test";
        String var4 = "CN=JavaSSL Test,OU=DAS,O=Oracle,L=Redwood Shores,ST=AZ,C=US";
        System.out.println();
        System.out.println("Testing isCertEntry");
        if (var1.isCertificateEntry(var2)) {
            System.out.println("Good- it passed");
        } else {
            System.out.println("Bad- the test failed");
        }

        if (var1.isCertificateEntry(var3)) {
            System.out.println("Bad- the test failed");
        } else {
            System.out.println("Good- it passed");
        }

        if (var1.isCertificateEntry(var4)) {
            System.out.println("Bad- the test failed");
        } else {
            System.out.println("Good- it passed");
        }

    }

    private void c(KeyStore var1) throws KeyStoreException {
        String var2 = "CN=akoyfman-sun";
        String var3 = "CN=JavaSSL Test";
        String var4 = "CN=JavaSSL Test,OU=DAS,O=Oracle,L=Redwood Shores,ST=AZ,C=US";
        System.out.println("Testing isKeyEntry");
        if (var1.isKeyEntry(var2)) {
            System.out.println("Bad- the test failed");
        } else {
            System.out.println("Good- it passed");
        }

        if (var1.isKeyEntry(var3)) {
            System.out.println("Good- it passed");
        } else {
            System.out.println("Bad- the test failed");
        }

        if (var1.isKeyEntry(var4)) {
            System.out.println("Bad- the test failed");
        } else {
            System.out.println("Good- it passed");
        }

    }

    private void d(KeyStore var1) throws KeyStoreException {
        System.out.println("Using type " + var1.getType());
        System.out.println();
    }

    private void e(KeyStore var1) throws KeyStoreException {
        System.out.println("Size of KeyStore is " + var1.size());
        System.out.println();
    }

    private void f(KeyStore var1) throws KeyStoreException {
        Enumeration var2 = this.a(var1.aliases());
        String var3 = "CN=JavaSSL Test";
        String var4 = "CN=JavaSSL Test ";
        String var5 = "CN=JavaSSL TesT";
        String var6 = "CN=JavaSSL Test,OU=DAS,O=Oracle,L=Redwood Shores,ST=CA,C=TW";
        String var7 = (String)var2.nextElement();
        System.out.println("Alias 1: " + var3);
        if (var1.containsAlias(var3)) {
            System.out.println("IS in the KeyStore");
        } else {
            System.out.println("IS NOT in the KeyStore");
        }

        System.out.println();
        System.out.println("Alias 2: " + var4);
        if (var1.containsAlias(var4)) {
            System.out.println("IS in the KeyStore");
        } else {
            System.out.println("IS NOT in the KeyStore");
        }

        System.out.println();
        System.out.println("Alias 3: " + var5);
        if (var1.containsAlias(var5)) {
            System.out.println("IS in the KeyStore");
        } else {
            System.out.println("IS NOT in the KeyStore");
        }

        System.out.println();
        System.out.println("Alias 4: " + var6);
        if (var1.containsAlias(var6)) {
            System.out.println("IS in the KeyStore");
        } else {
            System.out.println("IS NOT in the KeyStore");
        }

        System.out.println();
        System.out.println("Alias 5: " + var7);
        if (var1.containsAlias(var7)) {
            System.out.println("IS in the KeyStore");
        } else {
            System.out.println("IS NOT in the KeyStore");
        }

        System.out.println();
    }

    private void g(KeyStore var1) throws KeyStoreException {
        System.out.println("Printing information about KeyStore:");
        System.out.println("KeyStore contains " + var1.size() + " entries.");
        System.out.println("Before the entry deletion...");
        Enumeration var2 = this.a(var1.aliases());

        String var4;
        for(int var3 = 0; var2.hasMoreElements(); ++var3) {
            var4 = (String)var2.nextElement();
            System.out.println("Entry[" + var3 + "]");
            System.out.println("  Alias: \"" + var4 + "\"");
            System.out.println("  Created At: " + var1.getCreationDate(var4));
            System.out.println();
        }

        System.out.println();
        String var8 = "CN=akoyfman-sun";
        var4 = "CN=akoyfman-sun";
        var1.deleteEntry(var8);
        System.out.println("After the entry deletion...");
        Enumeration var5 = this.a(var1.aliases());

        for(int var6 = 0; var5.hasMoreElements(); ++var6) {
            String var7 = (String)var5.nextElement();
            System.out.println("Entry[" + var6 + "]");
            System.out.println("  Alias: \"" + var7 + "\"");
            System.out.println("  Created At: " + var1.getCreationDate(var7));
            System.out.println();
        }

    }

    private void h(KeyStore var1) throws KeyStoreException {
        String var2 = "CN=JavaSSL Test";
        String var3 = "CN=JavaSSL Test ";
        String var4 = "CN=JavaSSL Test,OU=DAS,O=Oracle,L=Redwood Shores,ST=CA,C=TW";

        String var8;
        try {
            Certificate var5 = var1.getCertificate(var2);
            var8 = var1.getCertificateAlias(var5);
            System.out.println();
            System.out.println("Certificate 1:");
            System.out.println(var8);
        } catch (Exception var12) {
            System.out.println("Get 1st certificate Failed");
            var12.printStackTrace();
        }

        try {
            Certificate var6 = var1.getCertificate(var3);
            var8 = var1.getCertificateAlias(var6);
            System.out.println();
            System.out.println("Certificate2:");
            System.out.println(var8);
        } catch (Exception var11) {
            System.out.println("Get 2nd certificate Failed");
            var11.printStackTrace();
        }

        try {
            Certificate var7 = var1.getCertificate(var4);
            var8 = var1.getCertificateAlias(var7);
            System.out.println();
            System.out.println("Certificate 3:");
            System.out.println(var8);
        } catch (Exception var10) {
            System.out.println("Get 3rd certificate Failed");
            var10.printStackTrace();
        }

        System.out.println();
        System.out.println("Having 2nd & 3rd certificate NULL is fine.");
    }
}

