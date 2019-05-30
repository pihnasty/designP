package String;

public class StringLineSeparator {
    public static void main(String[] args) {
        String sql = "CREATE OR REPLACE FUNCTION test_db2.admin_task_test()\n" +
                "RETURNS void\n" +
                "AS\n" +
                "$BODY$\n" +
                "DECLARE\n" +
                "    p_cnt INTEGER;\n" +
                "BEGIN\n" +
                "    SELECT\n" +
                "        COUNT(*)\n" +
                "        INTO STRICT p_cnt\n" +
                "        FROM aws_db2_ext_data.admintasks;\n" +
                "    SELECT\n" +
                "        COUNT(*)\n" +
                "        INTO STRICT p_cnt\n" +
                "        FROM aws_db2_ext_data.admintaskstatus;\n" +
                "    SELECT\n" +
                "        COUNT(*)\n" +
                "        INTO STRICT p_cnt\n" +
                "        FROM aws_db2_ext.admin_task_status;\n" +
                "    SELECT\n" +
                "        COUNT(*)\n" +
                "        INTO STRICT p_cnt\n" +
                "        FROM aws_db2_ext.admin_task_list;\n" +
                "\n" +
                "$BODY$\n" +
                "LANGUAGE  plpgsql;";


        System.out.println(sql);


        sql = "--   "+sql.replaceAll("\n","\n"+"--   ");

        System.out.println(sql);


        if(System.getProperty("os.name").toLowerCase().contains("win"))
        System.getProperties().list(System.out);





        }

    }





