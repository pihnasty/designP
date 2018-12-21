package jdbcTest.jdbcTestDB2;



import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.Objects;

public class ReadingCLOB {

    static final String JDBC_DRIVER = "com.ibm.db2.jcc.DB2Driver";
    static final String DB_URL = "jdbc:db2://192.168.13.224:50000/TEST_DB2";

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

            int i = 9;
            switch (i) {
                case 1: sql ="  select trim(schemaname) as username,\n" +
                        "                                   trim(schemaname) as user_id,\n" +
                        "                                   create_time as created\n" +
                        "                              from syscat.schemata";
                    colunmName =  "username";
                    break;
                case 2: sql ="select text from syscat.views where viewschema = 'TEST_DB2' and viewname = 'SIMPLE_VIEW'";
                    colunmName =  "text";
                    break;
                case 3: sql ="SELECT 1 FROM sysibm.sysdummy1";
                    colunmName =  "1";
                    break;
                case 4: sql ="select routineschema as owner\n" +
                        "     , routinename as function_name\n" +
                        "     , specificname as unique_name\n" +
                        "     , remarks as comment_text\n" +
                        "     , case when return_typeschema <> 'SYSIBM' or return_typemodule is not null then return_typeschema end as return_type_schema\n" +
                        "     , return_typemodule as return_type_module\n" +
                        "     , return_typename as return_type_name\n" +
                        "     , decode( functiontype, 'C', 'COLUMN', 'R', 'ROW', 'S', 'SCALAR', 'T', 'TABLE' ) as function_type\n" +
                        "     , language as function_language\n" +
                        "     , dialect as function_dialect\n" +
                        "     , deterministic\n" +
                        "     , nullcall as null_call\n" +
                        "     , spec_reg\n" +
                        "     , func_path\n" +
                        "     , qualifier\n" +
                        "     , text as function_text\n" +
                        "     , newsavepointlevel as new_savepoint_level\n" +
                        "     , secure\n" +
                        "  from syscat.routines\n" +
                        " where routinetype = 'F'\n" +
                        "   and origin in ( 'U', 'E', 'Q' )\n" +
                        "   and routinemodulename is null\n" +
                        "   and routineschema = 'TEST_DB2' \n" +
                        " order by routineschema, routinename, specificname";
                    colunmName =  "func_path";
                    break;
                case 5: sql ="select func_path from syscat.columns where tabschema = 'TEST_DB2' and tabname = 'SIMPLE_TABLE' \n" +
                        "and colname = 'ID'";
                    colunmName =  "func_path";
                    break;
                case 6: sql ="select routineschema as owner\n" +
                        "     , routinename as function_name\n" +
                        "     , specificname as unique_name\n" +
                        "     , remarks as comment_text\n" +
                        "     , case when return_typeschema <> 'SYSIBM' or return_typemodule is not null then return_typeschema end as return_type_schema\n" +
                        "     , return_typemodule as return_type_module\n" +
                        "     , return_typename as return_type_name\n" +
                        "     , decode( functiontype, 'C', 'COLUMN', 'R', 'ROW', 'S', 'SCALAR', 'T', 'TABLE' ) as function_type\n" +
                        "     , language as function_language\n" +
                        "     , dialect as function_dialect\n" +
                        "     , deterministic\n" +
                        "     , nullcall as null_call\n" +
                        "     , spec_reg\n" +
                        "     , func_path\n" +
                        "     , qualifier\n" +
                        "     , text as function_text\n" +
                        "     , newsavepointlevel as new_savepoint_level\n" +
                        "     , secure\n" +
                        "  from syscat.routines\n" +
                        " where routinetype = 'F'\n" +
                        "   and origin in ( 'U', 'E', 'Q' )\n" +
                        "   and routinemodulename is null\n" +
                        " --  and routineschema = {0}\n" +
                        " order by routineschema, routinename, specificname";
                    colunmName =  "func_path";
                    break;
                case 7: sql ="SELECT 1,7 FROM sysibm.sysdummy1";
                    colunmName =  "1";
                    break;
                case 8: sql ="select routineschema as owner\n" +
                        "     , routinename as function_name\n" +
                        "     , specificname as unique_name\n" +
                        "     , remarks as comment_text\n" +
                        "     , case when return_typeschema <> 'SYSIBM' or return_typemodule is not null then return_typeschema end as return_type_schema\n" +
                        "     , return_typemodule as return_type_module\n" +
                        "     , return_typename as return_type_name\n" +
                        "     , decode( functiontype, 'C', 'COLUMN', 'R', 'ROW', 'S', 'SCALAR', 'T', 'TABLE' ) as function_type\n" +
                        "     , language as function_language\n" +
                        "     , dialect as function_dialect\n" +
                        "     , deterministic\n" +
                        "     , nullcall as null_call\n" +
                        "     , spec_reg\n" +
                        "     , func_path\n" +
                        "     , qualifier\n" +
                        "     , text as function_text\n" +
                        "     , newsavepointlevel as new_savepoint_level\n" +
                        "     , secure\n" +
                        "  from syscat.routines\n" +
                        " where routinetype = 'F'\n" +
                        "   and origin in ( 'U', 'E', 'Q' )\n" +
                        "   and routinemodulename is null\n" +
                        " --  and routineschema = {0}\n" +
                        " order by routineschema, routinename, specificname";
                    colunmName =  "func_path";
                    break;
                case 9: sql ="     select trim(viewschema)\t\tas owner\n" +
                        "                                 , trim(viewname)\t\tas view_name\n" +
                        "                                 , text as text\n" +
                        "                                 , length(text)\tas text_length\n " +
                        "                                 , t.create_time\t as created" +

                        "                              from syscat.views\n" +
                        "                             where viewschema = 'TEST_DB2'\n" +
                        "                          --     and viewname IN ( {($1)} )";
                    colunmName =  "created";
                    break;
            }
            stmt = conn.prepareStatement(sql);
            ResultSet rs =stmt.executeQuery(); //stmt.executeQuery(sql);

            String text ="";
            while (rs.next()) {
                switch (i) {
                    case 1:
                        text = rs.getString(colunmName);
                        break;
                    case 2:

                        text = rs.getString(colunmName);
//                        Clob clob = rs.getClob(colunmName);
//                        text = clob.getSubString(1, (int) clob.length());

                        break;
                    case 3:  text = rs.getString(colunmName);
                        break;
                    case 4:
                        Clob clob = rs.getClob(colunmName);

                        if(Objects.nonNull(clob)) {
                            text = clob.getSubString(1, (int) clob.length());
                        }

//                        rs.getCharacterStream(colunmName);
//                        if(Objects.nonNull(clob)) {
//                            char[] charList = new char [100];
//                            rs.getCharacterStream("func_path").read(charList,1,40);
//                            text = clob.getSubString(1, (int) clob.length());
//                        }

                        System.out.print ("unique_name: " + rs.getString("unique_name")+"   ");
                        break;
                    case 5:  text = rs.getString(colunmName);
                        break;
                    case 6:

                        text=getColumnValue(rs, 14);
                        System.out.print ("unique_name: " + rs.getString("unique_name")+"   ");
                        break;
                    case 7:

                        text = rs.getString(2);
                        //text=getColumnValue(rs, 1);

                        break;
                    case 8:    text = rs.getString(14);
                        break;
                    case 9:    text = rs.getString(colunmName);

                        // System.out.println(rs.getString(4));
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
