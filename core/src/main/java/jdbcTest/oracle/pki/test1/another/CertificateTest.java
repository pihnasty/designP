package jdbcTest.oracle.pki.test1.another;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class CertificateTest {
    public CertificateTest() {
    }

    public static String a(Certificate var0) {
        if (!(var0 instanceof X509Certificate)) {
            return var0.toString();
        } else {
            X509Certificate var1 = (X509Certificate)var0;
            StringBuffer var2 = new StringBuffer(8192);
            var2.append("Version X509v").append(var1.getVersion());
            var2.append("\n");
            var2.append("Serial #:     ");
            var2.append(a(var1.getSerialNumber().toByteArray()));
            var2.append("\n");
            var2.append("Subject Name: ").append(var1.getSubjectDN().getName());
            var2.append("\n");
            var2.append("Issued By:    ").append(var1.getIssuerDN().getName());
            var2.append("\n");
            var2.append("Valid from:   ").append(var1.getNotBefore());
            var2.append("\n");
            var2.append("Valid until:  ").append(var1.getNotAfter());
            var2.append("\n");
            var2.append("KeyUsage:     \n");
            var2.append(a(var1.getKeyUsage())).append("\n");
            var2.append("Basic Constr: ");
            var2.append(var1.getBasicConstraints()).append("\n");
            return var2.toString();
        }
    }

    public static String a(byte[] var0) {
        if (var0 == null) {
            return "";
        } else {
            StringBuffer var1 = new StringBuffer(var0.length * 3 + 16);

            for(int var2 = 0; var2 < var0.length; ++var2) {
                if (var0[var2] == 0) {
                    var1.append("00");
                } else if (var0[var2] < 0) {
                    var1.append(Integer.toHexString(256 + var0[var2]));
                } else if (var0[var2] < 16) {
                    var1.append("0");
                    var1.append(Integer.toHexString(var0[var2]));
                } else {
                    var1.append(Integer.toHexString(var0[var2]));
                }

                var1.append(":");
            }

            var1.deleteCharAt(var1.length() - 1);
            return var1.toString().toUpperCase();
        }
    }

    public static String a(boolean[] var0) {
        if (var0 != null && var0.length >= 9) {
            StringBuffer var1 = new StringBuffer(1024);
            var1.append("DigitalSignature ").append(var0[0]).append("\n");
            var1.append("NonRepudiation   ").append(var0[1]).append("\n");
            var1.append("KeyEncipherment  ").append(var0[2]).append("\n");
            var1.append("DataEncipherment ").append(var0[3]).append("\n");
            var1.append("KeyAgreement     ").append(var0[4]).append("\n");
            var1.append("KeyCertSign      ").append(var0[5]).append("\n");
            var1.append("CRLSign          ").append(var0[6]).append("\n");
            var1.append("EncipherOnly     ").append(var0[7]).append("\n");
            var1.append("DecipherOnly     ").append(var0[8]).append("\n");

            for(int var2 = 9; var2 < var0.length; ++var2) {
                var1.append("Unknown          ").append(var0[var2]).append("\n");
            }

            return var1.toString();
        } else {
            return "";
        }
    }
}
