package jdbcTest.teradata;

/**
 * Created by Pihnastyi.O on 10/16/2017.
 */

import java.sql.*;
import java.util.Properties;

public class jdbcTestTeradata {
    public static void main(String[] args)
            throws ClassNotFoundException {
    //    String url = "jdbc:teradata://192.168.15.221/";
        //String url = "jdbc:teradata://192.168.15.221/CHARSET=UTF16,LOGMECH=LDAP,LOGDATA=td_ldap_usr@@min_privs";
      //  String url = "jdbc:teradata://192.168.15.221/CHARSET=UTF16,LOGMECH=LDAP,LOGDATA='authcid=td_ldap_usr  password=min_privs'";
        String url =  "jdbc:teradata://192.168.15.221/tmode=TERA,LOGDATA=td_ldap_usr@@min_privs,dbs_port=1025,database=DBC,LOGMECH=LDAP,encryptdata=OFF,charset=UTF16";
        try {
            // Load the Teradata Driver
            Class.forName("com.teradata.jdbc.TeraDriver");

            // Connect to the Teradata database specified in the URL
            // and submit userid and password.
            System.out.println("Connecting to " + url);

           Properties props = new Properties();
//            props.setProperty("CHARSET", "UTF16");
//            props.setProperty("LOGMECH", "LDAP");
//            props.setProperty("LOGDATA", "td_ldap_usr@@min_privs");

//             TeraDriver driver = (TeraDriver) DriverManager.getDriver(url);

             props.setProperty("user", "");
             props.setProperty("password", "");

            props.remove("user");
            props.remove("password");


            Connection con = DriverManager.getConnection(url,props);

            System.out.println("Established successful connection");

            String sql = "      select CAST('Teradata '||infodata AS varchar(1024)) as engine_info\n" +
                    "    , infodata as version\n" +
                    "      from dbc.dbcinfo\n" +
                    "      where infokey = 'VERSION'";




            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); //stmt.executeQuery(sql);
            while (rs.next()) {
                String textColumn = rs.getString(1);
                System.out.println(textColumn);
            }
            con.close();
            System.out.println("Disconnected");
        } catch (SQLException ex) {
            getExeption(ex);
            throw new IllegalStateException("Sample failed.");
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
