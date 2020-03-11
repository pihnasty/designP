package jdbcTest.oracle.pki.test1.sso;


import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.System.getenv;

public class JksOracleNoImpty {

    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    // static final String DB_URL = "jdbc:oracle:thin:@192.168.13.155:2484:orcl12.168.13.155";
 //   static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcps)(HOST=servername)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=orcl12.168.13.155)))";

   static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=192.168.13.155)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=orcl12.168.13.155)))";

  //  static final String path = "jar:file:C:///JDBCDrivers/ojdbc7.jar!/";

    private static final String pathJar = "jar:file:C:///JDBCDrivers/oracle/ojdbc8.jar!/";
    //private static final String trustStorePass = "/truststore/ewallet.p12";



    private static final String user ="min_privs";
    private static final String pass ="min_privs";

    private static final String trustStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletT.jks";
    private static final String trustStorePass = "System99";
    private static final String keyStorePath = "C:\\MyCertificates\\oracle\\OracleNew\\ewalletK.jks";
    private static final String keyStorePass = "System99";

    private static final String walletPath = "E:\\app\\Pihnastyi.O\\product\\12.1.0\\dbhome_1\\BIN\\truststoreP\\ewallet.p12";
   //private static final String walletPath ="C:\\MyCertificates\\oracle\\walletN\\wallet\\ewallet.p12";  // ewallet.p12
    private static final String walletPass = "min_privs";

    private static final boolean useSslAuthentication = true;
    private static final boolean trustStoreAuthentication = false;

    static String getColumnValue(ResultSet rs, int columnNum) throws SQLException, UnsupportedEncodingException {
        byte[] byteValue = rs.getBytes(columnNum);
        return byteValue == null ? null : new String(byteValue, "UTF-8");
    }


    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            URLClassLoader cl = new URLClassLoader(new URL[]{new URL(pathJar)});
            cl.loadClass(JDBC_DRIVER).newInstance();
            System.out.println("Connecting to database...");

            Driver driver = DriverManager.getDriver(DB_URL);
            //conn = DriverManager.getConnection(DB_URL, user, pass);
            conn = DriverManager.getConnection(DB_URL, getProperties());
            System.out.println("Creating statement...");
            int i = 2;


            String colunmName = "head_text";
            String sql = getSql(i);
            stmt = conn.prepareStatement(sql);
            List<String> columnCells = getColumn(stmt,colunmName);

            System.out.println(columnCells );

            System.out.println("finish");

        } catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static Properties getProperties() {
        Properties props = new Properties();

        if (useSslAuthentication) {
            props.setProperty("oracle.net.authentication_services","(TCPS)");
        } else {
            props.setProperty("user",user);
            props.setProperty("password", pass);
        }

        String trustStoreLocation = getenv("javax.net.ssl.trustStore");

        if (trustStoreAuthentication) {
            props.setProperty("javax.net.ssl.trustStoreType","JKS");
            props.setProperty("javax.net.ssl.trustStore",trustStorePath);
            props.setProperty("javax.net.ssl.trustStorePassword",trustStorePass);

            props.setProperty("javax.net.ssl.keyStoreType", "JKS");
            props.setProperty("javax.net.ssl.keyStore", keyStorePath);
            props.setProperty("javax.net.ssl.keyStorePassword", keyStorePass);
        } else {

            props.setProperty("javax.net.ssl.trustStoreType","PKCS12");
            props.setProperty("oracle.net.authentication_services","(TCPS)");
            props.setProperty("oracle.net.ssl_server_dn_match", "true");

            props.setProperty("javax.net.ssl.trustStore",walletPath);
            props.setProperty("javax.net.ssl.trustStorePassword",walletPass);


            props.setProperty("javax.net.ssl.keyStoreType", "JKS");


            props.setProperty("javax.net.ssl.keyStore", keyStorePath);
            props.setProperty("javax.net.ssl.keyStorePassword", keyStorePass);
        }


        return props;
    }

    private static  List<String>  getColumn(PreparedStatement stmt, String colunmName) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        String text = "";
        List<String> columnCells = new ArrayList<>();
        while (rs.next()) {
            columnCells.add( rs.getString(colunmName) );
            System.out.println("text: " + text);
        }
        rs.close();
        return columnCells;
    }

    private static String getSql(int i) {
        String sql = "";
        switch (i)        {
            case 1:
                sql = "                        SELECT Objs.object_name\n" +
                        "                             , Pbds.created body_created\n" +
                        "                             , Pbds.last_ddl_time body_last_ddl_time\n" +
                        "                             , Pbds.status body_status\n" +
                        "                             , (SELECT REGEXP_REPLACE(XMLAGG(XMLELEMENT(NOENTITYESCAPING \" \", TEXT) ORDER BY LINE).getClobVal(), '(< >)|(</ >)', '') FROM SYS.dba_SOURCE WHERE OWNER = Objs.owner AND NAME = Objs.object_name AND TYPE = 'PACKAGE')\n" +
                        "                                AS head_text\n" +
                        "                             , (SELECT REGEXP_REPLACE(XMLAGG(XMLELEMENT(NOENTITYESCAPING \" \", TEXT) ORDER BY LINE).getClobVal(), '(< >)|(</ >)', '') FROM SYS.dba_SOURCE WHERE OWNER = Objs.owner AND NAME = Objs.object_name AND TYPE = 'PACKAGE BODY')\n" +
                        "                                AS body_text\n" +
                        "                        FROM sys.dba_objects Objs\n" +
                        "                           , (select bo.owner, bo.object_name, bo.created, bo.last_ddl_time, bo.status\n" +
                        "                              from sys.dba_objects bo\n" +
                        "                              where object_type = 'PACKAGE BODY') Pbds\n" +
                        "                        WHERE Objs.object_type ='PACKAGE'\n" +
                        "                          and Objs.Owner = Pbds.Owner(+)\n" +
                        "                          and Objs.object_name = Pbds.object_name(+)\n" +
                        "                          AND Objs.subobject_name IS NULL\n" +
                        "                          AND Objs.owner = 'BANINST1'\n" +
                        "                          AND Objs.object_name IN ( 'SB_WL_SECTION_CTRL_STRINGS' )";
                break;
            case 2:
                sql = "SELECT REGEXP_REPLACE(XMLAGG(XMLELEMENT(NOENTITYESCAPING \" \", TEXT) ORDER BY LINE).getClobVal(), '(< >)|(</ >)', '') as head_text FROM SYS.dba_SOURCE WHERE OWNER = 'BANINST1' AND NAME = 'SB_WL_SECTION_CTRL_STRINGS' AND TYPE ='PACKAGE'";
                break;
            case 3:
                sql = "SELECT DBMS_XMLGEN.CONVERT(XMLAGG(XMLELEMENT(NOENTITYESCAPING VAL,TEXT).EXTRACT('//text()') ORDER BY LINE).getClobVal(),1) FROM SYS.dba_SOURCE WHERE OWNER = 'BANINST1' AND NAME = 'SB_WL_SECTION_CTRL_STRINGS' AND TYPE ='PACKAGE BODY';";
                break;
            case 4:
                sql = "SELECT REGEXP_REPLACE(XMLAGG(XMLELEMENT(NOENTITYESCAPING \" \", TEXT) ORDER BY LINE).getClobVal(), '(< >)|(</ >)', '') as head_text FROM SYS.dba_SOURCE WHERE OWNER = 'BANINST1' AND NAME = 'SB_WL_SECTION_CTRL_STRINGS' AND TYPE ='PACKAGE'";
                break;

            default:
                sql = "";
        }
        return sql;
    }



    // --------------------------

    static void loadNewDriver(String filesPath) throws Exception {

    }

}
