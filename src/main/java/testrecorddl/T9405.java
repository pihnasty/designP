package testrecorddl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class T9405 {
    public static void main(String[] args) {
        Log.logger.info("Assessment of the duration of the recording DDL expressions Redshift");
    }


    @DataProvider(parallel = false)
    public Object[][] redshiftConnectionParams() {
        return new Object[][] {
                {"rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev","rsdbbmaster", "T8ickAvKbet3", true, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
        };
    }

    @Test(dataProvider = "redshiftConnectionParams")
    public void redshiftSsl(String serverName, int port, String dbName, String username, String password, boolean useSsl, boolean checkServerCertificate, String trustStore, String trustStorePassword) throws Exception {



//        SqlStatementExecutor.loadDriver(DatabaseVendor.REDSHIFT, "C:\\JDBCD\\RedshiftJDBC41-1.1.10.1010.jar");
//
//
//        RedshiftConnectionProperties cp = new RedshiftConnectionProperties(serverName, port, dbName, username, password);
//
//        if (useSsl)
//            cp.setSslProperties(checkServerCertificate,trustStore,trustStorePassword);
//
//        System.out.println("");
//
//        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
//        try (DbLoader loader = DbLoader.getDbLoader(loaderProperties)) {
//
//        }
//
//        Logger.LOADER.writeInfo("\n=^^^^= Redshift SSL Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);
    }


}


