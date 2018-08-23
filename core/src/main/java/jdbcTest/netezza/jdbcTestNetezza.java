package jdbcTest.netezza;

/**
 * Created by Pihnastyi.O on 10/16/2017.
 */

import java.sql.*;
import java.util.Properties;

public class jdbcTestNetezza {
    public static void main(String[] args)
            throws ClassNotFoundException {
    //    String url = "jdbc:teradata://192.168.15.221/";
        //String url = "jdbc:teradata://192.168.15.221/CHARSET=UTF16,LOGMECH=LDAP,LOGDATA=td_ldap_usr@@min_privs";
      //  String url = "jdbc:teradata://192.168.15.221/CHARSET=UTF16,LOGMECH=LDAP,LOGDATA='authcid=td_ldap_usr  password=min_privs'";
        String url =  "jdbc:netezza://192.168.13.153:5480/SYSTEM";
        try {
            // Load the Driver
            Class.forName("org.netezza.Driver");

            // Connect to the database specified in the URL
            // and submit userid and password.
            System.out.println("Connecting to " + url);



            for (int i=0; i<8; i++)     {
                Properties props = new Properties();
                props.setProperty("user", "min_privs");
                props.setProperty("password", "min_privs99");
                Connection con = DriverManager.getConnection(url,props);
                System.out.println("Established successful connection");
                getResuluSet(con);
                con.close();
                System.out.println("Disconnected");
            }



        } catch (SQLException ex) {
            getExeption(ex);
            throw new IllegalStateException("Sample failed.");
        }
    }

    private static void getResuluSet(Connection con) throws SQLException {
        String sql =  "                      select t.schema      as schema_name\n" +
                "      ,t.tablename   as table_name\n" +
                "      ,coalesce(dt.reference_count, 0) as reference_count\n" +
                "      ,NVL(rc.rowcount, 0) AS row_count\n" +
                "      ,NVL(s.used_bytes/pow(1024,2), 0) as size_in_mb\n" +
                "      ,NVL(count(c.frelid), 0)        as stat_fk_dep_count\n" +
                "      ,current_timestamp AS current_ts\n" +
                "         from   NZ_RULE_TEST.information_schema._v_table t\n" +
                "       left join\n" +
                "\t   (\n" +
                "       SELECT i.tblid, SUM(i.used_bytes) AS used_bytes\n" +
                "       FROM NZ_RULE_TEST.information_schema._v_sys_object_dslice_info i\n" +
                "       GROUP BY i.tblid\n" +
                "       ) AS s ON s.tblid = t.objid\n" +
                "       left join (SELECT DISTINCT\n" +
                "                         CON.CONRELID AS RELID,\n" +
                "                         C.OBJNAME,\n" +
                "                         CON.CONTYPE,\n" +
                "                         CON.CONFRELID AS FRELID\n" +
                "                    FROM (((DEFINITION_SCHEMA.\"_V_SYS_CONSTRAINT\" C\n" +
                "                            JOIN DEFINITION_SCHEMA.\"_T_CONSTRAINT\" CON ON ((C.OBJID = CON.OID)))\n" +
                "                            JOIN DEFINITION_SCHEMA.\"_V_OBJ_RELATION\" O ON (((O.OBJDB = CURRENT_DBID())\n" +
                "                                                                             AND (CON.CONRELID = O.BASE_TABLE))))\n" +
                "                            LEFT JOIN DEFINITION_SCHEMA.\"_T_OBJECT\" PK ON (((PK.OBJDB = CURRENT_DBID())\n" +
                "                                                                             AND (PK.OBJID = CON.CONFCONSTRID))))\n" +
                "                    WHERE (O.OBJID = O.VISIBLEID)\n" +
                "\t\t\t\t\t) as c on t.objid = c.frelid and c.CONTYPE = 'f'\n" +
                "       left join\n" +
                "(select objid, RELTUPLES AS rowcount from NZ_RULE_TEST.information_schema._V_TABLE where objtype = 'TABLE') rc ON t.objid = rc.objid\n" +
                "                left join\n" +
                "                            (\n" +
                "                                select\n" +
                "                                relid, count(objname) reference_count\n" +
                "                                from (SELECT DISTINCT\n" +
                "                                             CON.CONRELID AS RELID,\n" +
                "                                             C.OBJNAME,\n" +
                "                                             CON.CONTYPE,\n" +
                "                                             CON.CONFRELID AS FRELID\n" +
                "                                      FROM (((DEFINITION_SCHEMA.\"_V_SYS_CONSTRAINT\" C\n" +
                "                                              JOIN DEFINITION_SCHEMA.\"_T_CONSTRAINT\" CON ON ((C.OBJID = CON.OID)))\n" +
                "                                              JOIN DEFINITION_SCHEMA.\"_V_OBJ_RELATION\" O ON (((O.OBJDB = CURRENT_DBID())\n" +
                "                                                                                             AND (CON.CONRELID = O.BASE_TABLE))))\n" +
                "                                            LEFT JOIN DEFINITION_SCHEMA.\"_T_OBJECT\" PK ON (((PK.OBJDB = CURRENT_DBID())\n" +
                "                                                                                            AND (PK.OBJID = CON.CONFCONSTRID))))\n" +
                "                                      WHERE (O.OBJID = O.VISIBLEID)\n" +
                "\t\t\t\t\t\t\t\t\t  ) as q\n" +
                "                                where contype = 'f'\n" +
                "                                group by 1\n" +
                "                            ) dt\n" +
                "                ON dt.relid = rc.objid\n" +
                "where t.schema = 'NZ_RULE_TEST'\n" +
                "group by\n" +
                " t.schema,\n" +
                " t.tablename,\n" +
                " dt.reference_count,\n" +
                " rc.rowcount,\n" +
                " s.used_bytes;" ;


        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = // stmt.executeQuery();
        stmt.executeQuery(sql);
        rs.setFetchSize(10000);
        while (rs.next()) {
            String textColumn = rs.getString(3);
           if( "T_CUST".equals(rs.getString("TABLE_NAME")) )    System.out.println(textColumn);
        }

    }

    private static void getExeption(SQLException ex) {
        System.out.println();
        System.out.println("*** SQLException caught ***");

        while (ex != null) {
            System.out.println(" Error code: " + ex.getErrorCode());
            System.out.println(" SQL State: " + ex.getSQLState());
            System.out.println(" Message: " + ex.getMessage());
            ex.printStackTrace();
            System.out.println();
            ex = ex.getNextException();
        }
    }


}
