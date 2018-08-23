package jdbcTest.jdbcTestDB2;


import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB295cast {

    static final String JDBC_DRIVER = "com.ibm.db2.jcc.DB2Driver";
    static final String DB_URL = "jdbc:db2://91.222.250.86:51000/TEST_DB2";

    static final String path = "jar:file:C:///JDBCDrivers/db2/db2jcc4.jar!/";

    static final String USER = "min_privs";
    static final String PASS = "min_privs";


    static String getColumnValue(ResultSet rs, int columnNum) throws SQLException, UnsupportedEncodingException {
            byte[] byteValue = rs.getBytes(columnNum);
            return byteValue == null ? null : new String(byteValue, "UTF-8");
    }


    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            URLClassLoader cl = new URLClassLoader(new URL[]{new URL(path)});
            cl.loadClass(JDBC_DRIVER).newInstance();
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");

            String sql="";
            String colunmName = "";

            int i = 1;
            switch (i) {
                case 1: sql ="select  CAST(NULL AS VARCHAR(128)) as owner\n" +
                    "                                 , CAST(NULL AS VARCHAR(2)) as module_name\n" +
                    "                                 , CAST(NULL AS VARCHAR(10)) as dialect\n" +
                    "                                 , CAST(NULL AS TIMESTAMP) as create_time\n" +
                    "                                 , CAST(NULL AS VARCHAR(254)) as comment_text\n" +
                    "                              from SYSIBM.SYSDUMMY1\n" +
                    "                                   where 3 < 6\n" +
                    "                                      and CAST(NULL AS VARCHAR(4096)) = ?";
                    colunmName =  "owner";
                    break;
            }
            //    length({0}) > 254
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"TEST_DB1234567890123456789012345678901234567890123454678901234567890");
            ResultSet rs =stmt.executeQuery(); //stmt.executeQuery(sql);

            String text ="";
            while (rs.next()) {
                switch (i) {
                    case 1:
                        text = rs.getString(colunmName);
                        break;
                }
                System.out.println("text: " + text);
            }

            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

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
}
