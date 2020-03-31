package jdbcTest.oracle2;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Locale;


public class WalletOracle {
    //static final String DB_URL = "jdbc:oracle:thin:@192.168.15.19:1521:ORCL";

    static final String DB_URL = "jdbc:oracle:thin:@192.168.15.19:1521/ORCL";
    static final String path = "jar:file:C:///JDBCDrivers/oracle/ojdbc8.jar!/";
    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    static final String USER = "min_privs";
    static final String PASS = "min_privs";

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            URLClassLoader loader = new URLClassLoader(new URL[] {new URL(path)});
            Locale.setDefault(Locale.US);
            Driver drv = (Driver) loader.loadClass(JDBC_DRIVER).newInstance();

            DriverManager.registerDriver((Driver)Class.forName("oracle.jdbc.OracleDriver").newInstance());
            Connection conn;
          //  conn = drv.connect(DB_URL, null);
             conn = DriverManager.getConnection(DB_URL, USER, PASS);

            conn.close();

//            DriverManager.registerDriver(drv);
//            Enumeration<Driver> drvE = DriverManager.getDrivers();
//            while (drvE.hasMoreElements()) {
//                Driver driver = (Driver) drvE.nextElement();
//                System.out.println(driver.getClass().getName());
//            }
//            conn = DriverManager.getConnection(DB_URL, USER, PASS); // DriverManager.getConnection("jdbc:mysql://localhost/utt", "utt", "utt");
//            conn.close();
            System.out.println();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
