package tests.jdbcTest.db2_ssl;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class TestDb2Connection {
    static String getColumnValue(ResultSet rs, int columnNum) throws SQLException, UnsupportedEncodingException {
        byte[] byteValue = rs.getBytes(columnNum);
        return byteValue == null ? null : new String(byteValue, "UTF-8");
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
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

            String sql="";
            String colunmName = "";

            sql ="  select trim(schemaname) as username,\n" +
                    "                                   trim(schemaname) as user_id,\n" +
                    "                                   create_time as created\n" +
                    "                              from syscat.schemata";



            PreparedStatement  stmt = conn.prepareStatement(sql);
            ResultSet rs =stmt.executeQuery(); //stmt.executeQuery(sql);

            colunmName = "";

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