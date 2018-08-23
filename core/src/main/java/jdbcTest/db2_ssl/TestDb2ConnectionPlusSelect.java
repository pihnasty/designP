package jdbcTest.db2_ssl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestDb2ConnectionPlusSelect {
    public static void main(String[] args) {
        try
        {
//      String url = "jdbc:db2://52.37.95.115:50000/TEST_DB2";
            String url = "jdbc:db2://ec2-34-209-100-126.us-west-2.compute.amazonaws.com:50002/TEST_DB2";
            String user = "min_privs";
            String password = "min_privs";
            Connection conn;

            final Properties props = new Properties();
            props.put( "user", user );
            props.put( "password", password );
            props.put( "sslConnection", "true" );
            props.put( "sslTrustStoreLocation", "E:\\VerPOM\\designP\\src\\main\\java\\tests\\jdbcTest\\db2_ssl\\truststore.jks" );
            props.put( "sslTrustStorePassword", "123qweasd" );

            Class.forName( "com.ibm.db2.jcc.DB2Driver" );

            conn = DriverManager.getConnection( url, props );
            System.out.println( "Connection has been established!" );
            conn.close();
        }

        catch (ClassNotFoundException e)
        {
            System.err.println("Could not load JDBC driver");
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }

        catch(SQLException ex)
        {
            System.err.println("SQLException information");
            while(ex != null) {
                System.err.println ("Error msg: " + ex.getMessage());
                System.err.println ("SQLSTATE: " + ex.getSQLState());
                System.err.println ("Error code: " + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException(); // For drivers that support chained exceptions
            }
        }
    }
}