package jdbcTest.snowflake.printed.p8.archive;

import sun.security.util.DerValue;
import sun.security.x509.AlgorithmId;

import java.security.AlgorithmParameters;
import java.util.Arrays;

/*
 * @test
 * @summary Bad encoding/decoding of password-based encryption scheme 2 parameters (PBES2)
 */
public class PBES2AlgorithmId {
    public static void main(String[] args) throws Exception
    {
        final byte[] PBES2_PARAM = new byte[]{
                // SEQUENCE(2 elem)
                0x30, 0x3D,
                //   OBJECT IDENTIFIER 1.2.840.113549.1.5.13
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0D,
                //   SEQUENCE(2 elem)
                0x30, 0x30,
                //     SEQUENCE(2 elem)
                0x30, 0x1B,
                //       OBJECT IDENTIFIER 1.2.840.113549.1.5.12
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0C,
                //       SEQUENCE(2 elem)
                0x30, 0x0E,
                //         OCTET STRING(8 bytes) 1817E5A9E645CFCB
                0x04, 0x08, 0x18, 0x17, (byte) 0xE5, (byte) 0xA9, (byte) 0xE6, 0x45, (byte) 0xCF, (byte) 0xCB,
                //         INTEGER 2048
                0x02, 0x02, 0x08, 0x00,
                //     SEQUENCE(2 elem)
                0x30, 0x11,
                //       OBJECT IDENTIFIER 1.3.14.3.2.7
                0x06, 0x05, 0x2B, 0x0E, 0x03, 0x02, 0x07,
                //       OCTET STRING(8 byte) 0B6D3ABC9AF43B40
                0x04, 0x08, 0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40
        };

        final byte[] PBES2_ALG_AND_PARAM_WITHOUT_KEYLENGTH = new byte[]{
                // SEQUENCE(2 elem)
                0x30, 0x4B,
                //   OBJECT IDENTIFIER 1.2.840.113549.1.5.13
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0D,
                //   SEQUENCE(2 elem)
                0x30, 0x3E,
                //     SEQUENCE(2 elem)
                0x30, 0x29,
                //       OBJECT IDENTIFIER 1.2.840.113549.1.5.12
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0C,
                //       SEQUENCE(2 elem)
                0x30, 0x1C,
                //         OCTET STRING(8 bytes) 1817E5A9E645CFCB
                0x04, 0x08, 0x18, 0x17, (byte) 0xE5, (byte) 0xA9, (byte) 0xE6, 0x45, (byte) 0xCF, (byte) 0xCB,
                //         INTEGER 2048
                0x02, 0x02, 0x08, 0x00,
                //         SEQUENCE(2 elem)
                0x30, 0x0C,
                //           OBJECT IDENTIFIER 1.2.840.113549.2.7
                0x06, 0x08, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x02, 0x07,
                //           NULL
                0x05, 0x00,
                //     SEQUENCE(2 elem)
                0x30, 0x11,
                //       OBJECT IDENTIFIER 1.3.14.3.2.7
                0x06, 0x05, 0x2B, 0x0E, 0x03, 0x02, 0x07,
                //       OCTET STRING(8 byte) 0B6D3ABC9AF43B40
                0x04, 0x08, 0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40
        };

        final byte[] PBES2_ALG_AND_PARAM_WITHOUT_PRF = new byte[]{
                // SEQUENCE(2 elem)
                0x30, 0x40,
                //   OBJECT IDENTIFIER 1.2.840.113549.1.5.13
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0D,
                //   SEQUENCE(2 elem)
                0x30, 0x33,
                //     SEQUENCE(2 elem)
                0x30, 0x1E,
                //       OBJECT IDENTIFIER 1.2.840.113549.1.5.12
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0C,
                //       SEQUENCE(2 elem)
                0x30, 0x11,
                //         OCTET STRING(8 bytes) 1817E5A9E645CFCB
                0x04, 0x08, 0x18, 0x17, (byte) 0xE5, (byte) 0xA9, (byte) 0xE6, 0x45, (byte) 0xCF, (byte) 0xCB,
                //         INTEGER 2048
                0x02, 0x02, 0x08, 0x00,
                //         INTEGER 16
                0x02, 0x01, 0x10,
                //     SEQUENCE(2 elem)
                0x30, 0x11,
                //       OBJECT IDENTIFIER 1.3.14.3.2.7
                0x06, 0x05, 0x2B, 0x0E, 0x03, 0x02, 0x07,
                //       OCTET STRING(8 byte) 0B6D3ABC9AF43B40
                0x04, 0x08, 0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40
        };

        final byte[] PBES2_ALG_AND_PARAM_WITH_KEYLENGHT_AND_PRF_BUT_NOT_AN_AES_CIPHER = new byte[]{
                // SEQUENCE(2 elem)
                0x30, 0x4E,
                //   OBJECT IDENTIFIER 1.2.840.113549.1.5.13
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0D,
                //   SEQUENCE(2 elem)
                0x30, 0x41,
                //     SEQUENCE(2 elem)
                0x30, 0x2C,
                //       OBJECT IDENTIFIER 1.2.840.113549.1.5.12
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0C,
                //       SEQUENCE(2 elem)
                0x30, 0x1F,
                //         OCTET STRING(8 bytes) 1817E5A9E645CFCB
                0x04, 0x08, 0x18, 0x17, (byte) 0xE5, (byte) 0xA9, (byte) 0xE6, 0x45, (byte) 0xCF, (byte) 0xCB,
                //         INTEGER 2048
                0x02, 0x02, 0x08, 0x00,
                //         INTEGER 16
                0x02, 0x01, 0x10,
                //         SEQUENCE(2 elem)
                0x30, 0x0C,
                //           OBJECT IDENTIFIER 1.2.840.113549.2.7
                0x06, 0x08, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x02, 0x07,
                //           NULL
                0x05, 0x00,
                //     SEQUENCE(2 elem)
                0x30, 0x11,
                //       OBJECT IDENTIFIER 1.3.14.3.2.7
                0x06, 0x05, 0x2B, 0x0E, 0x03, 0x02, 0x07,
                //       OCTET STRING(8 byte) 0B6D3ABC9AF43B40
                0x04, 0x08, 0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40
        };

        final byte[] PBES2_ALG_AND_PARAM_WITH_KEYLENGHT_AND_PRF_AND_AES_CIPHER = new byte[]{
                // SEQUENCE(2 elem)
                0x30, 0x5A,
                //   OBJECT IDENTIFIER 1.2.840.113549.1.5.13
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0D,
                //   SEQUENCE(2 elem)
                0x30, 0x4D,
                //     SEQUENCE(2 elem)
                0x30, 0x2C,
                //       OBJECT IDENTIFIER 1.2.840.113549.1.5.12
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0C,
                //       SEQUENCE(2 elem)
                0x30, 0x1F,
                //         OCTET STRING(8 bytes) 1817E5A9E645CFCB
                0x04, 0x08, 0x18, 0x17, (byte) 0xE5, (byte) 0xA9, (byte) 0xE6, 0x45, (byte) 0xCF, (byte) 0xCB,
                //         INTEGER 2048
                0x02, 0x02, 0x08, 0x00,
                //         INTEGER 16
                0x02, 0x01, 0x10,
                //         SEQUENCE(1 elem)
                0x30, 0x0C,
                //           OBJECT IDENTIFIER 1.2.840.113549.2.7
                0x06, 0x08, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x02, 0x07,
                //           NULL
                0x05, 0x00,
                //     SEQUENCE(2 elem)
                0x30, 0x1D,
                //       OBJECT IDENTIFIER 2.16.840.1.101.3.4.1.2
                0x06, 0x09, 0x60,(byte) 0x86, 0x48, 0x01, 0x65, 0x03, 0x04, 0x01, 0x02,
                //       OCTET STRING(8 byte) 0B6D3ABC9AF43B40
                0x04, 0x10, 0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40,
                0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40
        };

        final byte[] PBES2_PARAM_WITH_KEYLENGH_AND_PRF_AND_AES_CIPHER = new byte[]{
                //   SEQUENCE(2 elem)
                0x30, 0x4D,
                //     SEQUENCE(2 elem)
                0x30, 0x2C,
                //       OBJECT IDENTIFIER 1.2.840.113549.1.5.12
                0x06, 0x09, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x05, 0x0C,
                //       SEQUENCE(2 elem)
                0x30, 0x1F,
                //         OCTET STRING(8 bytes) 1817E5A9E645CFCB
                0x04, 0x08, 0x18, 0x17, (byte) 0xE5, (byte) 0xA9, (byte) 0xE6, 0x45, (byte) 0xCF, (byte) 0xCB,
                //         INTEGER 2048
                0x02, 0x02, 0x08, 0x00,
                //         INTEGER 16
                0x02, 0x01, 0x10,
                //         SEQUENCE(1 elem)
                0x30, 0x0C,
                //           OBJECT IDENTIFIER 1.2.840.113549.2.7
                0x06, 0x08, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x02, 0x07,
                //           NULL
                0x05, 0x00,
                //     SEQUENCE(2 elem)
                0x30, 0x1D,
                //       OBJECT IDENTIFIER 2.16.840.1.101.3.4.1.2
                0x06, 0x09, 0x60,(byte) 0x86, 0x48, 0x01, 0x65, 0x03, 0x04, 0x01, 0x02,
                //       OCTET STRING(8 byte) 0B6D3ABC9AF43B40
                0x04, 0x10, 0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40,
                0x0B, 0x6D, 0x3A, (byte) 0xBC, (byte) 0x9A, (byte) 0xF4, 0x3B, 0x40
        };

        try {
            AlgorithmId algId = AlgorithmId.parse(new DerValue(PBES2_PARAM));
        } catch (Exception e) {
            System.out.println("1) Currently fails here because the expectation of PBES2Parameters is also an algorithm id.");
            e.printStackTrace();
        }

        // WARNING !!!!
        // From here, submitting directly to PBES2Parameters the Algorithm Id, to make it happy and pursue.
        // Proper test case needs the two first line of the bytes arrays above containing the PBES2 algorithm to be
        // removed once the first issue above has been solved.
        try {
            AlgorithmParameters algParams = AlgorithmParameters.getInstance("1.2.840.113549.1.5.13","SunJCE");
            algParams.init(PBES2_PARAM);
        } catch (Exception e) {
            System.out.println("1) Currently fails here because the expectation of PBES2Parameters is also an algorithm id.");
            e.printStackTrace();
        }

        try {
            AlgorithmParameters algParams = AlgorithmParameters.getInstance("1.2.840.113549.1.5.13","SunJCE");
            algParams.init(PBES2_ALG_AND_PARAM_WITHOUT_KEYLENGTH);
        } catch (Exception e) {
            System.out.println("2) Currently fails here because PBES2Parameters does not support optional Keylength properly.");
            e.printStackTrace();
        }

        try {
            AlgorithmParameters algParams = AlgorithmParameters.getInstance("1.2.840.113549.1.5.13","SunJCE");
            algParams.init(PBES2_ALG_AND_PARAM_WITHOUT_PRF);
        } catch (Exception e) {
            System.out.println("3) Currently fails here because PBES2Parameters does not support default prf.");
            e.printStackTrace();
        }

        try {
            AlgorithmParameters algParams = AlgorithmParameters.getInstance("1.2.840.113549.1.5.13","SunJCE");
            algParams.init(PBES2_ALG_AND_PARAM_WITH_KEYLENGHT_AND_PRF_BUT_NOT_AN_AES_CIPHER);
        } catch (Exception e) {
            System.out.println("4) Currently fails here because PBES2Parameters does not support non AES ciphers.");
            e.printStackTrace();
        }

        AlgorithmParameters algParams = AlgorithmParameters.getInstance("1.2.840.113549.1.5.13","SunJCE");
        algParams.init(PBES2_ALG_AND_PARAM_WITH_KEYLENGHT_AND_PRF_AND_AES_CIPHER);
        if (!Arrays.equals(algParams.getEncoded(),PBES2_PARAM_WITH_KEYLENGH_AND_PRF_AND_AES_CIPHER)) {
            System.out.println("5) Currently fails here because PBES2Parameters does not encode params properly.");
            System.out.print("Expected:");
            System.out.println(Arrays.toString(PBES2_PARAM_WITH_KEYLENGH_AND_PRF_AND_AES_CIPHER));
            System.out.print("Actual:");
            System.out.println(Arrays.toString(algParams.getEncoded()));
            if (Arrays.equals(algParams.getEncoded(),PBES2_ALG_AND_PARAM_WITH_KEYLENGHT_AND_PRF_AND_AES_CIPHER)) {
                System.out.println("Actual is the full algorithm id, not the params alone.");
            }
        }
    }
}
