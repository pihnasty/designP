//package tests.jdbcTest.sqlTest.oracle.anydatap;
//
//
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import oracle.sql.ANYDATA;
//import oracle.sql.DATE;
//import oracle.sql.Datum;
//import oracle.sql.NUMBER;
//import oracle.sql.TypeDescriptor;
//
//public class ANYDATAOracle {
//
//    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
//    static final String DB_URL = "jdbc:oracle:thin:@192.168.15.19:1521:ORCL";
//
//    static final String path = "jar:file:C:///JDBCDrivers/ojdbc7.jar!/";
//
//
//    static final String USER = "min_privs";
//    static final String PASS = "min_privs";
//
//
//    static String getColumnValue(ResultSet rs, int columnNum) throws SQLException, UnsupportedEncodingException {
//        byte[] byteValue = rs.getBytes(columnNum);
//        return byteValue == null ? null : new String(byteValue, "UTF-8");
//    }
//
//
//    public static void main(String[] args) {
//
//
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//            URLClassLoader cl = new URLClassLoader(new URL[]{new URL(path)});
//            cl.loadClass(JDBC_DRIVER).newInstance();
//            System.out.println("Connecting to database...");
//            Driver driver = DriverManager.getDriver(DB_URL);
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Creating statement...");
//            int i = 1;
//
//
//            String colunmName = "ANYDATA_VALUE";
//            String sql = getSql(i);
//            stmt = conn.prepareStatement(sql);
//            List<String> columnCells = getColumn(stmt,colunmName);
//
//
//            System.out.println("finish");
//
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        } finally {
//
//            try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//            }
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//    }
//
//    private static  List<String>  getColumn(PreparedStatement stmt, String colunmName) throws SQLException {
//        ResultSet rs = stmt.executeQuery();
//        String text = "";
//
//        ANYDATA anydt = null;
//
//        List<String> columnCells = new ArrayList<>();
////        while (rs.next()) {
////         //   columnCells.add( rs.getString(colunmName) );
////            anydt = (ANYDATA)rs.getObject(colunmName);
////            System.out.println("text: " + anydt);
////        }
//
//        while(rs.next())
//        {
//            anydt = (ANYDATA)rs.getObject(colunmName);
//            System.out.println("   "+anydt.stringValue());
//            TypeDescriptor typedesc = anydt.getTypeDescriptor();
//            Datum embeddedDatum = anydt.accessDatum();
//            if(typedesc.getTypeCode() == TypeDescriptor.TYPECODE_DATE)
//            {
//                // the embedded object is a DATE:
//                DATE datedatum = (DATE)embeddedDatum;
//                // etc.
//            }
//            else if(typedesc.getTypeCode() == TypeDescriptor.TYPECODE_NUMBER)
//            {
//                // the embedded object is a NUMBER
//                NUMBER numberdatum = (NUMBER)embeddedDatum;
//                // etc.
//            }
//        }
//
//
//
//        rs.close();
//        return columnCells;
//    }
//
//    private static String getSql(int i) {
//        String sql = "";
//        switch (i)        {
//            case 1:
//                sql = "select\n" +
//                    "        OWNER\n" +
//                    "        ,JOB_NAME\n" +
//                    "        ,ARGUMENT_NAME\n" +
//                    "        ,ARGUMENT_POSITION\n" +
//                    "        ,ARGUMENT_TYPE\n" +
//                    "        ,VALUE\n" +
//                    "        ,ANYDATA_VALUE\n" +
//                    "        ,OUT_ARGUMENT\n" +
//                    "    from DBA_SCHEDULER_JOB_ARGS\n" +
//                    "--    where OWNER = 'TEST_ORA_PG'\n" +
//                    "  --  and JOB_NAME = 'TEST_JOB_NAMED_SCHEDULE'\n" +
//                    "    order by ARGUMENT_POSITION";
//                break;
//            case 2:
//                sql = "";
//                break;
//            default:
//                sql = "";
//        }
//        return sql;
//    }
//
//}
