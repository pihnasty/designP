package jdbcTest.sybasessl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SysBaseD0 {
    public static void main(String args[])
    {
        String className = "com.sybase.jdbc4.jdbc.SybDriver";
        String url = "jdbc:sybase:Tds:";
        String host = "192.168.14.36:5443";
        String database = "test_sapase";
        String user = "min_privs";
        String password = "min_privs";
        String parameters = "?ENABLE_SSL=true";
        String connectionStr = concat_all(url, host, "/", database, parameters);

        try {
            System.setProperty("javax.net.ssl.trustStore", "C:\\JDBCDrivers\\SAP ASE SSL\\16034\\truststore.jks");
            Class.forName( className ).newInstance();

            Connection con = DriverManager.getConnection(connectionStr,user,password);
            System.out.println(connectionStr);

            Statement stmt = con.createStatement();
            String SQL = "insert into tbl_ssl_cipher values(@@ssl_ciphersuite)";
            stmt.execute(SQL);
            stmt.close();
            con.commit();

            con.commit();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
        }


    }

    private static String concat_all(String... args){
        StringBuilder resultStr = new StringBuilder();

        for (String arg : args) {
            resultStr.append(arg);
        }

        return resultStr.toString();
    }

}
