package tasks.t10727_Support_SSL_for_GreenPlum;
/*
import com.amazon.diagnostics.logger.Logger;
import com.amazon.sct.base.constants.DatabaseVendor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.security.Provider;
import java.security.Security;
import java.sql.*;
import java.util.Properties;


public class SslConnectionsTest extends BaseTest{

    @DataProvider(parallel = false)
    public Object[][] connectionParams() {
        return new Object[][] {
//  AMAZON
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "gbuser", "Dataaccesspassword!!!", "gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "amazonaws", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "TEST_PRIV", "TEST", "gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "amazonaws", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.MSSQL, "jdbc:sqlserver://gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "gbuser", "Dataaccesspassword!!!", "gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "amazonaws", "C:\\JDBC\\sqljdbc4.jar"},
//                {DatabaseVendor.MYSQL, "jdbc:mysql://gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "gbuser", "Dataaccesspassword!!!", "gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "amazonaws", "C:\\JDBC\\mysql-connector-java-5.1.6.jar"},
//                {DatabaseVendor.AURORA, "jdbc:mysql://aurora-cluster.cluster-cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "gbuser", "Dataaccesspassword!!!", "aurora-cluster.cluster-cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "amazonaws", "C:\\JDBC\\mysql-connector-java-5.1.6.jar"},
//                {DatabaseVendor.POSTGRESQL, "jdbc:postgresql://postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432/postgres", "gbuser", "Dataaccesspassword!!!", "postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432", "amazonaws_postgres", "C:\\JDBC\\postgresql-9.4-1204-jdbc42.jar"},
//                {DatabaseVendor.POSTGRESQL, "jdbc:postgresql://postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432/aws_db", "gbuser", "Dataaccesspassword!!!", "postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432", "amazonaws_aws_db", "C:\\JDBC\\mysql-connector-java-5.1.6.jar"},
//                {DatabaseVendor.POSTGRESQL, "jdbc:postgresql://postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432/GOLD_TEST_ORA", "gbuser", "Dataaccesspassword!!!", "postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432", "amazonaws_GOLD_TEST_ORA","C:\\JDBC\\postgresql-9.4-1204-jdbc42.jar"},
//                {DatabaseVendor.POSTGRESQL, "jdbc:postgresql://dev-postgresql.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432/test_pg_mysql", "gbuser2", "gbuser2", "dev-postgresql.cjj06khxetlc.us-west-2.rds.amazonaws.com:5432test_pg_mysql", "dev-amazonaws_test_pg_mysql","C:\\JDBC\\postgresql-9.4-1204-jdbc42.jar"},

//                {DatabaseVendor.ORACLEDWH, "jdbc:oracle:thin:@52.36.56.194:1521:ORA12C01", "booker", "Booker99", "52.36.56.194:1521:ORA12C01", "oracledwh","C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.REDSHIFT, "jdbc:redshift://rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com:5439/dev", "rsdbbmaster", "T8ickAvKbet3", "rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com:5439", "amazonaws_dev","C:\\JDBC\\RedshiftJDBC41-1.1.10.1010.jar"},
//                {DatabaseVendor.TERADATA, "jdbc:teradata://192.168.14.176", "dbc", "dbc", "teradata.dbbest.net", "192.168.14.176","C:\\JDBC\\tdgssconfig.jar;C:\\JDBC\\terajdbc4.jar"},

//  LOCAL NET
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@192.168.0.21:1521:BIG3T", "FLXUSER", "FLXUSER", "192.168.0.21:1521:BIG3T", "FLXUSER", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@VM-DBA-2008R2-2:1521:ORCL", "system", "system", "Oracle 11.2 (VM-DBA-2008R2-2:1521:ORCL)", "11.2_VM-DBA-2008R2-2", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@VM-Oracle12:1521:ORA", "system", "12345", "Oracle 12 (VM-Oracle12:1521:ORA)", "12_VM-Oracle12", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@VM-DBA-2008R2-1:1521:ORA10", "system", "system", "Oracle 10g (10.2.0.4.0 - 64bit) (VM-DBA-2008R2-1:1521:ORA10)", "10g-10.2.0.4.0-64bit_VM-DBA-2008R2-1", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@VM-DBA-2008R2-4:1521:ORCL", "system", "system", "Oracle 10g (VM-DBA-2008R2-4:1521:ORCL)", "10g_VM-DBA-2008R2-4", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.MSSQL, "jdbc:sqlserver://VM-DS1\\mssqlserver2012:54250", "sa", "system", "VM-DS1\\mssqlserver2012", "2012_VM-DS1", "C:\\JDBC\\sqljdbc4.jar"},

//  LOCALHOST
//                {DatabaseVendor.MSSQL, "jdbc:sqlserver://localhost\\YURYAR:6689", "sa", "Yustas", "localhost\\YURYAR", "localhost-YURYAR", "C:\\JDBC\\sqljdbc4.jar"},

//   Failed connections
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "gbuser", "wrongpassword", "gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "amazonaws", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.ORACLE, "jdbc:oracle:thin:@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "wronguser", "wrongpassword", "gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com:1521:ORCL", "amazonaws", "C:\\JDBC\\ojdbc7.jar"},
//                {DatabaseVendor.MSSQL, "jdbc:sqlserver://gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "gbuser", "wrongpassword", "gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "amazonaws"},
//                {DatabaseVendor.MSSQL, "jdbc:sqlserver://gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "wronguser", "wrongpassword", "gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "amazonaws"},
//                {DatabaseVendor.MYSQL, "jdbc:mysql://gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "gbuser", "wrongpassword", "gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "amazonaws"},
//                {DatabaseVendor.MYSQL, "jdbc:mysql://gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "wronguser", "wrongpassword", "gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "amazonaws"},
        };
    }

    //region REGION: SQL Server

    @DataProvider(parallel = false)
    public Object[][] sqlServerConnectionParams() {
        return new Object[][] {
//                 {"gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com", null, 0, "gbuser", "Dataaccesspassword!!!", false, false, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
//                {"gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com", null, 0, "gbuser", "Dataaccesspassword!!!", true, false, null, null},
//                {"gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com", null, 0, "gbuser", "Dataaccesspassword!!!", false, true, null, null},
//                {"ec2-52-37-30-252.us-west-2.compute.amazonaws.com", null, 0, "gbuser", "Dataaccesspassword!!!", true, true, null, null},
                {"gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com", null, 0, "min_privs", "min_privs", true, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
        };
    }

    @Test(dataProvider = "sqlServerConnectionParams")
    public void amazonSqlServerSSL(String serverName, String instance, int port, String username, String password, boolean useSsl, boolean checkServerCertificate
            , String trustStore, String trustStorePassword) throws Exception {
        Logger.LOADER.writeInfo("\n=VVV= SqlServer SSl Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);

        SqlStatementExecutor.loadDriver(DatabaseVendor.MSSQL, "C:\\JDBCD\\sqljdbc4.jar");
        SqlServerConnectionProperties cp = new SqlServerConnectionProperties(serverName, instance, port, username, password);

        if (useSsl)
            cp.setSslProperties(checkServerCertificate,trustStore,trustStorePassword);


        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
        try (DbLoader loader = DbLoader.getDbLoader(loaderProperties)) {
            try (Connection con = DriverManager.getConnection(cp.getUrl(), cp.getProperties());Statement stmt = con.createStatement();) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("con.getWarnings ()=" + con.getWarnings());
                DatabaseMetaData dma = con.getMetaData();
                System.out.println("Connected to " + dma.getURL());
                System.out.println("Driver " + dma.getDriverName());
                System.out.println("Version" + dma.getDriverVersion());

                String query = "select encrypt_option from  sys.dm_exec_connections where session_id = @@spid";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String encrypt_option = rs.getString("encrypt_option");
                    System.out.println("encrypt_option=" + encrypt_option);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
            }
        }

        Logger.LOADER.writeInfo("\n=^^^^= SqlServer SSL Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);

    }
    //endregion REGION: SQL Server

    //region REGION: ORACLE

    @DataProvider(parallel = false)
    public Object[][] oracleConnectionParams() {
        return new Object[][] {
//                {"52.36.56.194", 1521, "ORA12C01" , "system", "System99", false, null, null, null, null, false},
//                {"52.36.56.194", 1522, "ORA12C01" , "system", "System99", true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", false},
//                 {"ec2-52-36-56-194.us-west-2.compute.amazonaws.com", 1522, "ORA12C01" , "gbuser", "Dataaccesspassword!!!", true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "C:\\MyCertificates\\NewQACert\\identity.jks", "123qweasd", false},
//                {"52.36.56.194", 1522, "ORA12C01" , "system", "System99", true, "C:\\MyCertificates\\Oracle\\ewallet.jks", "1q2w3e4r", "C:\\MyCertificates\\Oracle\\ewallet.jks", "1q2w3e4r", false},
//                {"52.36.56.194", 1522, "ORA12C01" , "system", "System99", true, "C:\\MyCertificates\\Oracle\\ewallet.jks", "1q2w3e4r", "C:\\MyCertificates\\Oracle\\ewallet.jks", "1q2w3e4r", true},
//                  {"gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com", 1521, "ORCL" , "min_privs", "min_privs", false, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "", "", false},
                  {"gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com", 1522, "ORCL" , "gbuser", "Dataaccesspassword!!!", true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", false},
//                {"gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com", 1522, "ORCL" , "gbuser", "Dataaccesspassword!!!", true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", null, null, false},
//                {"gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com", 1522, "ORCL" , "gbuser", "Dataaccesspassword!!!", true, "C:\\MyCertificates\\QA\\amazon_oracle_rds.jks", "123456", null, null, false},
        };
    }


    @Test(dataProvider = "oracleConnectionParams")
    public void oracleSsl(String serverName, int port, String instance, String username, String password, boolean useSsl
            , String trustStore, String trustStorePassword, String keyStore, String keyStorePassword, boolean useSslAuthentication) throws Exception {
        Logger.LOADER.writeInfo("\n=VVV= Oracle SSl Test (useSsl=%b; useSslAuthentication=%b) ============", useSsl, useSslAuthentication);

        SqlStatementExecutor.loadDriver(DatabaseVendor.ORACLE, "C:\\JDBCD\\ojdbc7.jar");

        OracleConnectionProperties cp = new OracleConnectionProperties(OracleConnectionProperties.ConnectionType.BASIC_SERVICE_NAME,
                serverName, port, instance, username, password);





        if (useSsl)
            cp.setSslProperties(useSslAuthentication, trustStore, trustStorePassword, keyStore, keyStorePassword);

        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
        try (DbLoader loader = DbLoader.getDbLoader(loaderProperties)) {
            try (Connection con = DriverManager.getConnection(cp.getUrl(), cp.getProperties());Statement stmt = con.createStatement();) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("con.getWarnings ()=" + con.getWarnings());
                DatabaseMetaData dma = con.getMetaData();
                System.out.println("Connected to " + dma.getURL());
                System.out.println("Driver " + dma.getDriverName());
                System.out.println("Version" + dma.getDriverVersion());

//                String query = "SELECT decode(lower(sys_context('USERENV','NETWORK_PROTOCOL')),'tcps','Y','N') as ssl_encrypted FROM dual";
//                ResultSet rs = stmt.executeQuery(query);
//                while (rs.next()) {
//                    String ssl_encrypted = rs.getString("ssl_encrypted");
//                    System.out.println("ssl_encrypted=" + ssl_encrypted);
//                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//                }
            }
        }
        Logger.LOADER.writeInfo("\n=^^^^= Oracle SSL Test (useSsl=%b; useSslAuthentication=%b) ============", useSsl, useSslAuthentication);
    }


    @Test
    public void amazonOracle_SSL_JKS_EC2() throws Exception {
        SqlStatementExecutor.loadDriver(DatabaseVendor.ORACLE, "C:\\JDBCD\\ojdbc7.jar");

        //{DatabaseVendor.MSSQL, "jdbc:sqlserver://gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "gbuser", "Dataaccesspassword!!!", "gbmssql01.cjj06khxetlc.us-west-2.rds.amazonaws.com:1433", "amazonaws", "C:\\JDBC\\sqljdbc4.jar"},

        //ConnectionType connType, String serverName, int port, String instance, String username, String password
        OracleConnectionProperties cp = new OracleConnectionProperties(OracleConnectionProperties.ConnectionType.BASIC_SERVICE_NAME,
                "52.36.56.194", 1522, "ORA12C01", "gbuser", "Dataaccesspassword!!!");

        cp.setSslProperties(false,"C:\\MyCertificates\\amazon1.jks","1q2w3e",null,null);


        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
        DbLoader loader = DbLoader.getDbLoader(loaderProperties);

        loader.close();

    }

    @Test
    private void amazonOracle_SSL_Wallet_EC2() {
        try {
            System.out.println(" ====  vvvv === Case3 =============");
            SqlStatementExecutor.loadDriver(DatabaseVendor.ORACLE,"C:\\JDBCD\\ojdbc7.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\oraclepki.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\osdt_cert.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\osdt_core.jar");
            Provider p = (Provider) Class.forName("oracle.security.pki.OraclePKIProvider", true, ClassLoader.getSystemClassLoader()).newInstance();
            //Security.insertProviderAt(p,3);
            Security.addProvider(p);

            System.setProperty("oracle.net.tns_admin", "C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\network\\admin");

            Properties connectionProps = new Properties();
            connectionProps.setProperty("user", "gbuser");
            connectionProps.setProperty("password", "Dataaccesspassword!!!");
            //connectionProps.setProperty("oracle.net.ssl_cipher_suites",
            //        "(SSL_DH_anon_WITH_3DES_EDE_CBC_SHA,SSL_DH_anon_WITH_RC4_128_MD5," +
            //                "," +
            //                "SSL_DH_anon_WITH_DES_CBC_SHA)");
            String url =
                    "jdbc:oracle:thin:@SSL_BOOKER_ORA12C01";

            //Security.insertProviderAt(new oracle.security.pki.OraclePKIProvider(),3);

//            connectionProps.setProperty("javax.net.ssl.trustStore", "C:\\MyCertificates\\Oracle\\ewallet.p12");
//            connectionProps.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
//            connectionProps.setProperty("javax.net.ssl.trustStorePassword", "1q2w3e4r");
//            connectionProps.setProperty("javax.net.ssl.keyStore", "C:\\MyCertificates\\Oracle\\ewallet.p12");
//            connectionProps.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
//            connectionProps.setProperty("javax.net.ssl.keyStorePassword", "1q2w3e4r");

//            Security.addProvider(new oracle.security.pki.OraclePKIProvider());


            //String url =
            //        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=gboracle-ssl.cjj06khxetlc.us-west-2.rds.amazonaws.com)(PORT=1522))(CONNECT_DATA=(SERVICE_NAME=ORCL)))";


            connectionProps.setProperty("javax.net.ssl.trustStore","C:\\MyCertificates\\Oracle\\cwallet.sso");
            connectionProps.setProperty("javax.net.ssl.trustStoreType","SSO");
            //connectionProps.setProperty("javax.net.ssl.trustStorePassword", "1q2w3e4r");
            connectionProps.setProperty("javax.net.ssl.keyStore","C:\\MyCertificates\\Oracle\\cwallet.sso");
            connectionProps.setProperty("javax.net.ssl.keyStoreType","SSO");
            //connectionProps.setProperty("javax.net.ssl.keyStorePassword", "1q2w3e4r");

            Connection con = DriverManager.getConnection(url, connectionProps);

            if (con != null) {
                DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                System.out.println("SQLKeywords: " + dm.getSQLKeywords());
                con.close();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(" ====  ^^^^ === Case3 =============\n\n");
        }

    }

    @Test
    private void amazonOracle_SSL_Wallet_RDS() {
        try {
            System.out.println(" ====  vvvv === Case3 =============");
            SqlStatementExecutor.loadDriver(DatabaseVendor.ORACLE,"C:\\JDBC\\ojdbc7.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\oraclepki.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\osdt_cert.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\osdt_core.jar");
            Provider p = (Provider) Class.forName("oracle.security.pki.OraclePKIProvider", true, ClassLoader.getSystemClassLoader()).newInstance();
            //Security.insertProviderAt(p,3);
            Security.addProvider(p);

            System.setProperty("oracle.net.tns_admin", "C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\network\\admin");
            String url = "jdbc:oracle:thin:@ORA11GB_SSL_WO_SERVER_DN";


            Properties connectionProps = new Properties();
            connectionProps.setProperty("user", "gbuser");
            connectionProps.setProperty("password", "Dataaccesspassword!!!");

            //connectionProps.setProperty("oracle.net.ssl_cipher_suites", "TLS_RSA_WITH_AES_256_CBC_SHA");


//            connectionProps.setProperty("javax.net.ssl.trustStore", "C:\\MyCertificates\\Oracle\\ewallet.p12");
//            connectionProps.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
//            connectionProps.setProperty("javax.net.ssl.trustStorePassword", "1q2w3e4r");
//            connectionProps.setProperty("javax.net.ssl.keyStore", "C:\\MyCertificates\\Oracle\\ewallet.p12");
//            connectionProps.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
//            connectionProps.setProperty("javax.net.ssl.keyStorePassword", "1q2w3e4r");

            //connectionProps.setProperty("oracle.net.ssl_server_dn_match", "TRUE");

            connectionProps.setProperty("javax.net.ssl.trustStore","C:\\MyCertificates\\Oracle\\cwallet.sso");
            connectionProps.setProperty("javax.net.ssl.trustStoreType","SSO");
            connectionProps.setProperty("javax.net.ssl.keyStore","C:\\MyCertificates\\Oracle\\cwallet.sso");
            connectionProps.setProperty("javax.net.ssl.keyStoreType","SSO");

            Connection con = DriverManager.getConnection(url, connectionProps);

            if (con != null) {
                DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                System.out.println("SQLKeywords: " + dm.getSQLKeywords());
                con.close();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(" ====  ^^^^ === Case3 =============\n\n");
        }

    }

    @Test
    public void amazonOracle_JKS_RDS() {
        try {
            System.out.println(" ====  vvvv === amazonOracle_JKS_RDS =============");
            SqlStatementExecutor.loadDriver(DatabaseVendor.ORACLE,"C:\\JDBCDrivers\\ojdbc7.jar");

            Properties connectionProps = new Properties();
            connectionProps.setProperty("user", "gbuser");
            connectionProps.setProperty("password", "Dataaccesspassword!!!");
            //connectionProps.setProperty("oracle.net.ssl_cipher_suites", "TLS_RSA_WITH_AES_128_CBC_SHA");


//            String url =
//                    "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=gboracle-ssl.cjj06khxetlc.us-west-2.rds.amazonaws.com)(PORT=1522))(CONNECT_DATA=(SERVICE_NAME=ORCL))"
//                            +"(SECURITY = (SSL_SERVER_CERT_DN =\"C=US,ST=Washington,L=Seattle,O=Amazon.com,OU=RDS,CN=gboracle-ssl.cjj06khxetlc.us-west-2.rds.amazonaws.com\"))"
//                            +")";
//            connectionProps.setProperty("oracle.net.ssl_server_dn_match", "TRUE");


            System.setProperty("oracle.net.tns_admin", "C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\network\\admin");
            String url = "jdbc:oracle:thin:@ORA11GB_SSL_WO_SERVER_DN";



            connectionProps.setProperty("javax.net.ssl.trustStore","C:\\MyCertificates\\AmazonTrustStore.jks");
            connectionProps.setProperty("javax.net.ssl.trustStoreType","JKS");
            connectionProps.setProperty("javax.net.ssl.trustStorePassword", "1q2w3e");
//            connectionProps.setProperty("javax.net.ssl.keyStore","C:\\MyCertificates\\AmazonTrustStore.jks");
//            connectionProps.setProperty("javax.net.ssl.keyStoreType","JKS");
//            connectionProps.setProperty("javax.net.ssl.keyStorePassword", "1q2w3e");

            Connection con = DriverManager.getConnection(url, connectionProps);

            if (con != null) {
                DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                System.out.println("SQLKeywords: " + dm.getSQLKeywords());
                con.close();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(" ====  ^^^^ === amazonOracle_JKS_RDS =============\n\n");
        }

    }

//--------------------------------------------------------------------

    @Test
    public void oracleSSL() throws Exception {
        DbLoader.loadDriver(DatabaseVendor.ORACLE, "C:\\JDBCD\\ojdbc7.jar");

        //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Properties connectionProps = new Properties();
        //connectionProps.put("integratedSecurity", "true");
        connectionProps.setProperty("user", "system");
        connectionProps.setProperty("password", "System99");
        connectionProps.setProperty("oracle.net.ssl_cipher_suites",
                "(SSL_DH_anon_WITH_3DES_EDE_CBC_SHA,SSL_DH_anon_WITH_RC4_128_MD5,SSL_DH_anon_WITH_DES_CBC_SHA)");
        String url =
                "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=52.36.56.194)(PORT=1522))(CONNECT_DATA=(SERVICE_NAME=ORA12C01)))";

        Connection con = DriverManager.getConnection(url, connectionProps);

        if (con!=null) {
            DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
            System.out.println("Driver name: " + dm.getDriverName());
            System.out.println("Driver version: " + dm.getDriverVersion());
            System.out.println("Product name: " + dm.getDatabaseProductName());
            System.out.println("Product version: " + dm.getDatabaseProductVersion());
            System.out.println("SQLKeywords: " + dm.getSQLKeywords());
            con.close();
        }


    }

    @Test
    public void oracleSSLCase2a() throws Exception {
        DbLoader.loadDriver(DatabaseVendor.ORACLE, "C:\\JDBC\\ojdbc7.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\oraclepki.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\osdt_cert.jar;C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\jlib\\osdt_core.jar");

        System.setProperty("oracle.net.tns_admin", "C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\network\\admin");

        Properties connectionProps = new Properties();
        connectionProps.setProperty("user", "system");
        connectionProps.setProperty("password", "System99");
        connectionProps.setProperty("oracle.net.ssl_cipher_suites",
                "(SSL_DH_anon_WITH_3DES_EDE_CBC_SHA,SSL_DH_anon_WITH_RC4_128_MD5," +
                        "," +
                        "SSL_DH_anon_WITH_DES_CBC_SHA)");
        //String url =
        //        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=52.36.56.194)(PORT=1522))(CONNECT_DATA=(SERVICE_NAME=ORA12C01)))";
        String url =
                "jdbc:oracle:thin:@SSL_BOOKER_ORA12C01";

        connectionProps.setProperty("javax.net.ssl.trustStore","C:\\MyCertificates\\Oracle\\ewallet.p12");
        connectionProps.setProperty("javax.net.ssl.trustStoreType","PKCS12");
        connectionProps.setProperty("javax.net.ssl.trustStorePassword","1q2w3e4r");

        //connectionProps.setProperty("javax.net.ssl.trustStore","C:\\MyCertificates\\Oracle\\cwallet.sso");
        //connectionProps.setProperty("javax.net.ssl.trustStoreType","SSO");

        Connection con = DriverManager.getConnection(url, connectionProps);

        if (con!=null) {
            DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
            System.out.println("Driver name: " + dm.getDriverName());
            System.out.println("Driver version: " + dm.getDriverVersion());
            System.out.println("Product name: " + dm.getDatabaseProductName());
            System.out.println("Product version: " + dm.getDatabaseProductVersion());
            System.out.println("SQLKeywords: " + dm.getSQLKeywords());
            con.close();
        }
    }


    @Test
    public void oracleSSLCase2b() throws Exception {
        DbLoader.loadDriver(DatabaseVendor.ORACLE, "C:\\JDBC\\ojdbc7.jar");

        System.setProperty("oracle.net.tns_admin", "C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\network\\admin");

        Properties connectionProps = new Properties();
        connectionProps.setProperty("user", "system");
        connectionProps.setProperty("password", "System99");
        //connectionProps.setProperty("oracle.net.ssl_cipher_suites",
        //        "(SSL_DH_anon_WITH_3DES_EDE_CBC_SHA,SSL_DH_anon_WITH_RC4_128_MD5,SSL_DH_anon_WITH_DES_CBC_SHA)");
        //String url =
        //        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=52.36.56.194)(PORT=1522))(CONNECT_DATA=(SERVICE_NAME=ORA12C01)))";
        String url =
                "jdbc:oracle:thin:@SSL_BOOKER_ORA12C01";

        connectionProps.setProperty("javax.net.ssl.trustStore","C:\\MyCertificates\\Oracle\\truststore.jks");
        connectionProps.setProperty("javax.net.ssl.trustStoreType","JKS");
        connectionProps.setProperty("javax.net.ssl.trustStorePassword","1q2w3e4r");

        Connection con = DriverManager.getConnection(url, connectionProps);

        if (con!=null) {
            DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
            System.out.println("Driver name: " + dm.getDriverName());
            System.out.println("Driver version: " + dm.getDriverVersion());
            System.out.println("Product name: " + dm.getDatabaseProductName());
            System.out.println("Product version: " + dm.getDatabaseProductVersion());
            System.out.println("SQLKeywords: " + dm.getSQLKeywords());
            con.close();
        }
    }

    @Test
    public void oracleSSLCase1() throws Exception {
        DbLoader.loadDriver(DatabaseVendor.ORACLE, "C:\\JDBC\\ojdbc7.jar");

        System.setProperty("oracle.net.tns_admin", "C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\network\\admin");

        Properties connectionProps = new Properties();
        connectionProps.setProperty("user", "system");
        connectionProps.setProperty("password", "System99");

//        connectionProps.setProperty("SQLNET.CRYPTO_CHECKSUM_TYPES_CLIENT","(MD5)");
//        connectionProps.setProperty("SQLNET.ENCRYPTION_TYPES_CLIENT","(AES256)");
//        connectionProps.setProperty("SQLNET.ENCRYPTION_CLIENT","required");
//        connectionProps.setProperty("SQLNET.CRYPTO_CHECKSUM_CLIENT","required");
        connectionProps.setProperty("SSL_CLIENT_AUTHENTICATION","FALSE");



//        connectionProps.setProperty("oracle.net.ssl_cipher_suites",
//                "(SSL_DH_anon_WITH_3DES_EDE_CBC_SHA,SSL_DH_anon_WITH_RC4_128_MD5,SSL_DH_anon_WITH_DES_CBC_SHA)");
        String url =
                "jdbc:oracle:thin:@SSL_BOOKER_ORA12C01";

        Connection con = DriverManager.getConnection(url, connectionProps);

        if (con!=null) {
            DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
            System.out.println("Driver name: " + dm.getDriverName());
            System.out.println("Driver version: " + dm.getDriverVersion());
            System.out.println("Product name: " + dm.getDatabaseProductName());
            System.out.println("Product version: " + dm.getDatabaseProductVersion());
            System.out.println("SQLKeywords: " + dm.getSQLKeywords());
            con.close();
        }
    }

    @Test
    public void oracleAccess() throws Exception {

        DbLoader.loadDriver(DatabaseVendor.ORACLE, "C:\\JDBC\\ojdbc7.jar");

        ConnectionProperties c = new OracleConnectionProperties(OracleConnectionProperties.ConnectionType.BASIC_SID, "gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com", 1521, "ORCL", "gbuser", "Dataaccesspassword!!!");

        DbLoaderProperties loaderProperties = new DbLoaderProperties(c);
        //loaderProperties.setConnectionName("@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com");
        try (DbLoader loaderSID = DbLoader.getDbLoader(loaderProperties)){
            Logger.LOADER.writeInfo("SID: success");
        } catch (Throwable e) {
            Logger.LOADER.writeError(e, "SID: Errors");
        }

        ConnectionProperties cSERVICE_NAME = new OracleConnectionProperties(OracleConnectionProperties.ConnectionType.BASIC_SERVICE_NAME, "gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com", 1521, "ORCL", "gbuser", "Dataaccesspassword!!!");

        DbLoaderProperties lpSERVICE_NAME = new DbLoaderProperties(cSERVICE_NAME);
        //loaderProperties.setConnectionName("@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com");
        try (DbLoader loaderSERVICE_NAME = DbLoader.getDbLoader(lpSERVICE_NAME)){
            Logger.LOADER.writeInfo("SERVICE_NAME: success");
        } catch (Throwable e) {
            Logger.LOADER.writeError(e, "SERVICE_NAME: Errors");
        }


        ConnectionProperties cTNS_Alias = new OracleConnectionProperties("C:\\app\\yaroshenko.y\\product\\11.2.0\\client_2\\network\\admin", "ORACLEAWS", "gbuser", "Dataaccesspassword!!!");

        DbLoaderProperties lp_cTNS_Alias = new DbLoaderProperties(cTNS_Alias);
        //loaderProperties.setConnectionName("@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com");
        try (DbLoader loaderTNS_Alias = DbLoader.getDbLoader(lp_cTNS_Alias)){
            Logger.LOADER.writeInfo("TNS_Alias: success");
        } catch (Throwable e) {
            Logger.LOADER.writeError(e, "TNS_Alias: Errors");
        }

        ConnectionProperties cTNS_CON_ID = new OracleConnectionProperties(" (DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = _06_TCP) (HOST = gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com)" +
                "  (PORT = 1521) ) ) (CONNECT_DATA = (SERVER = DEDICATED) (SID = ORCL) ) )", "gbuser", "Dataaccesspassword!!!");

        DbLoaderProperties lp_cTNS_CON_ID = new DbLoaderProperties(cTNS_CON_ID);
        //loaderProperties.setConnectionName("@gboracle.cjj06khxetlc.us-west-2.rds.amazonaws.com");
        try (DbLoader loaderTNS_CON_ID = DbLoader.getDbLoader(lp_cTNS_CON_ID)){
            Logger.LOADER.writeInfo("TNS_CON_ID: success");
        } catch (Throwable e) {
            Logger.LOADER.writeError(e, "TNS_CON_ID: Errors");
        }

    }

    //endregion REGION: ORACLE

    //region REGION: PostgreSQL

    @DataProvider(parallel = false)
    public Object[][] postgreSqlConnectionParams() {
        return new Object[][] {
//                {"postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com", 5432, "postgres", "gbuser", "Dataaccesspassword!!!", true, false, null, null},
                {"postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com", 5432, "postgres", "gbuser", "Dataaccesspassword!!!", true, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
//                {"postgres1.cjj06khxetlc.us-west-2.rds.amazonaws.com", 5432, "postgres", "gbuser", "Dataaccesspassword!!!", false, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
        };
    }


    @Test(dataProvider = "postgreSqlConnectionParams")
    public void postgreSqlSsl(String serverName, int port, String dbName, String username, String password, boolean useSsl, boolean checkServerCertificate, String trustStore, String trustStorePassword) throws Exception {
        Logger.LOADER.writeInfo("\n=VVV= PostgreSql SSl Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);

        SqlStatementExecutor.loadDriver(DatabaseVendor.POSTGRESQL, "C:\\JDBCD\\postgresql-9.4-1204-jdbc42.jar");


        PostgreSqlConnectionProperties cp = new PostgreSqlConnectionProperties(serverName, port, dbName, username, password);

        if (useSsl) cp.setSslProperties(checkServerCertificate,trustStore,trustStorePassword);

        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
        try (DbLoader loader = DbLoader.getDbLoader(loaderProperties)) {
        }


        Logger.LOADER.writeInfo("\n=^^^^= PostgreSql SSL Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);

    }

    //endregion REGION: PostgreSQL

    //region REGION: MySQL
    @DataProvider(parallel = false)
    public Object[][] mySqlConnectionParams() {
        return new Object[][] {
                //{"gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com", 3306, "gbuser", "Dataaccesspassword!!!", true, false, null, null, null, null, false},
                //{"gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com", 3306, "gbuser", "Dataaccesspassword!!!", true, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", false},
                //{"gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com", 3306, "gbuser", "Dataaccesspassword!!!", true, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", true},
                {"ec2-52-37-95-115.us-west-2.compute.amazonaws.com", 3306, "gbuser", "Dataaccesspassword!!!", false, false, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", false},
        //        {"gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com", 3306, "min_privs", "min_privs", true, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e", true},
               // {"gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com", 3306, "gbuser", "Dataaccesspassword!!!", false, true, null, null, null, null, false},
        };
    }


    @Test(dataProvider = "mySqlConnectionParams")
    public void mySqlSsl(String serverName, int port, String username, String password, boolean useSsl, boolean checkServerCertificate
                           , String trustStore, String trustStorePassword, String keyStore, String keyStorePassword, boolean requireSsl) throws Exception {
        Logger.LOADER.writeInfo("\n=VVV= MySQL SSl Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);

        SqlStatementExecutor.loadDriver(DatabaseVendor.MYSQL, "C:\\JDBCD\\mysql-connector-java-5.1.6.jar");


        MySqlConnectionProperties cp = new MySqlConnectionProperties(serverName, port, username, password);

        if (useSsl)
            cp.setSslProperties(checkServerCertificate,trustStore,trustStorePassword, keyStore, keyStorePassword, requireSsl);


        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
        try (DbLoader loader = DbLoader.getDbLoader(loaderProperties)) {
            try (Connection con = DriverManager.getConnection(cp.getUrl(), cp.getProperties());Statement stmt = con.createStatement();) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("con.getWarnings ()=" + con.getWarnings());
                DatabaseMetaData dma = con.getMetaData();
                System.out.println("Connected to " + dma.getURL());
                System.out.println("Driver " + dma.getDriverName());
                System.out.println("Version" + dma.getDriverVersion());

                String query = "select case variable_value when '' then 'NO' else 'YES' end connection_ssl_status from  information_schema.session_status where variable_name = 'Ssl_cipher'";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String connection_ssl_status = rs.getString("connection_ssl_status");
                    System.out.println("connection_ssl_status=" + connection_ssl_status);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
            }
        }

        Logger.LOADER.writeInfo("\n=^^^^= MySQL SSL Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);

    }

    //endregion REGION: MySQL

    //region REGION: Redshift

    @DataProvider(parallel = false)
    public Object[][] redshiftConnectionParams() {
        return new Object[][] {
                  {"rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev","rsdbbmaster", "T8ickAvKbet3", false, false, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
             //   {"rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev","min_privs", "min_Privs2", false, false, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
             //   {"rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev","min_privs", "min_Privs2", true, true,   "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
        };
    }

    @Test(dataProvider = "redshiftConnectionParams")
    public void redshiftSsl(String serverName, int port, String dbName, String username, String password, boolean useSsl, boolean checkServerCertificate, String trustStore, String trustStorePassword) throws Exception {
        Logger.LOADER.writeInfo("\n=VVV= Redshift SSl Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);

        SqlStatementExecutor.loadDriver(DatabaseVendor.REDSHIFT, "C:\\JDBCD\\RedshiftJDBC41-1.1.10.1010.jar");


        RedshiftConnectionProperties cp = new RedshiftConnectionProperties(serverName, port, dbName, username, password);

        if (useSsl)
            cp.setSslProperties(checkServerCertificate,trustStore,trustStorePassword);

        System.out.println("");


        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
        try (DbLoader loader = DbLoader.getDbLoader(loaderProperties)) {

            try (Connection con = DriverManager.getConnection(cp.getUrl(), cp.getProperties());Statement stmt = con.createStatement();) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("con.getWarnings ()=" + con.getWarnings());
                DatabaseMetaData dma = con.getMetaData();
                System.out.println("Connected to " + dma.getURL());
                System.out.println("Driver " + dma.getDriverName());
                System.out.println("Version" + dma.getDriverVersion());

                String query = "SELECT TOP 1 sslversion, sslcipher, sslcompression, sslexpansion FROM STL_CONNECTION_LOG  WHERE pid = pg_backend_pid() ORDER BY recordtime DESC";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sslversion = rs.getString("sslversion");
                    String sslcipher = rs.getString("sslcipher");
                    String sslcompression = rs.getString("sslcompression");
                    String sslexpansion = rs.getString("sslexpansion");
                    System.out.println("sslversion=" + sslversion+"\nsslcipher="+sslcipher+"\nsslcompression="+sslcompression+"\nsslexpansion="+sslexpansion);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
            }
        }
        Logger.LOADER.writeInfo("\n=^^^^= Redshift SSL Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);
    }
    //endregion REGION: Redshift

    //region REGION: Teradata

    @DataProvider(parallel = false)
    public Object[][] teradataConnectionParams() {
        return new Object[][] {
                {"teradata.dbbest.net", 2025, "DBC","dbc", "dbc", true, true, "C:\\MyCertificates\\AmazonTrustStore.jks", "1q2w3e"},
        };
    }

    @Test(dataProvider = "teradataConnectionParams")
    public void teradataSsl(String serverName, int port, String dbName, String username, String password, boolean useSsl, boolean checkServerCertificate, String trustStore, String trustStorePassword) throws Exception {
        Logger.LOADER.writeInfo("\n=VVV= Teradata SSl Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);
        SqlStatementExecutor.loadDriver(DatabaseVendor.TERADATA, "C:\\JDBCD\\teradata\\terajdbc4.jar;C:\\JDBCD\\teradata\\tdgssconfig.jar");
        TeradataConnectionProperties cp = new TeradataConnectionProperties(serverName, port, dbName, username, password);

        if (useSsl)  cp.setSslProperties(checkServerCertificate,trustStore,trustStorePassword);

        System.out.println("");
        DbLoaderProperties loaderProperties = new DbLoaderProperties(cp);
        try (DbLoader loader = DbLoader.getDbLoader(loaderProperties)) {
        }
        Logger.LOADER.writeInfo("\n=^^^^= Teradata SSL Test (useSsl=%b; checkServerCertificate=%b) ============", useSsl, checkServerCertificate);
    }
    //endregion REGION: Teradata
}




@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataProvider {
    String name() default "";

    boolean parallel() default false;

    int[] indices() default {};
}

*/