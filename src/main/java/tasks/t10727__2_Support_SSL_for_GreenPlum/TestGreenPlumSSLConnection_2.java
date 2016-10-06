package tasks.t10727__2_Support_SSL_for_GreenPlum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

    public class TestGreenPlumSSLConnection_2 {
        private static final String DB_SERVER_NAME = "192.168.13.59";
        private static final String SSL_PORT = "5432";
        private static final String DB_SID = "postgres";
        private static final String DB_USER = "min_privs";
        private static final String DB_PASSWORD = "Min_privs99";
        private static final String CA_ROOT_CERT_FILE = "C:\\MyCertificates\\greenPlumTest.jks";

        public static void main(String[] args) {
            final Properties properties = new Properties();
            final String connectionString = String.format(  "jdbc:postgresql://%s:%s/%s", DB_SERVER_NAME, SSL_PORT, DB_SID);
            properties.put("user", DB_USER);
            properties.put("password", DB_PASSWORD);
            properties.put("ssl", "true");
            properties.put("sslmode", "verify-full");
             properties.put("sslrootcert", CA_ROOT_CERT_FILE);

            System.setProperty("javax.net.ssl.trustStore", "C:\\MyCertificates\\greenPlumTest.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "111111");
            // Declare the JDBC objects.
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                // Establish the connection.
                con = DriverManager.getConnection(connectionString, properties);
                //con = DriverManager.getConnection(connectionString, DB_USER, DB_PASSWORD);

                // Create and execute an SQL statement that returns some data.
                String SQL = "            select ns.nspname  as schema_name\n" +
                        "                                , t.relname   as table_name\n" +
                        "                                from pg_class t\n" +
                        "                                , pg_namespace ns\n" +
                        "                                where t.relkind in ('r')\n" +
                        "                                and ns.oid = t.relnamespace\n" +
                        "                                and\tns.nspname not like 'pg_%'\n" +
                        "                                and\tns.nspname not in ('information_schema', 'gp_toolkit')\n" +
                        "                                and\n" +
                        "                                (ns.nspname, t.relname) not in (select distinct schemaname, partitiontablename\n" +
                        "                                                                from pg_partitions)\n" +
                        "                                and t.relstorage <> 'x'\n" +
                        "                                order by ns.nspname, t.relname;";
//                String SQL =  "SELECT 1";
                stmt = con.createStatement();
                rs = stmt.executeQuery(SQL);

                // Iterate through the data in the result set and display it.
                while (rs.next()) {
                    //System.out.println(rs.getString("schema_name") );
                    System.out.println(rs.getString(1) );
                }
            }

            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (rs != null) try { rs.close(); } catch(Exception e) {}
                if (stmt != null) try { stmt.close(); } catch(Exception e) {}
                if (con != null) try { con.close(); } catch(Exception e) {}
            }
        }
    }




