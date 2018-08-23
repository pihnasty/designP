package jdbcTest.mariaDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDBTest {

    public static void main(String[] argv) {

        System.out.println("-------- MariaDB JDBC Connection Testing ------------");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is MariaDB JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MariaDB JDBC Driver Registered!");
        Connection connection = null;



        try {
            connection = DriverManager
                    //      .getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
                //        .getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11219539", "sql11219539", "Prod2018Prod");
                    .getConnection("jdbc:mariadb://mariadb-10-2.cjj06khxetlc.us-west-2.rds.amazonaws.com:3306", "min_privs", "min_privs");


            if (connection != null) {
                System.out.println("You made it, take control your database now!");
            } else {
                System.out.println("Failed to make connection!");
            }

            Statement st = connection.createStatement();

 //           st.executeUpdate("INSERT INTO productionmodel.machine VALUES ( 3,'KD 2323_03',   'Press_03')");
            ResultSet rs = st.executeQuery("                        SELECT `SCHEMA_NAME`\n" +
                "                        , `DEFAULT_COLLATION_NAME`\n" +
                "                        FROM information_schema.`SCHEMATA`\n" +
                "                        WHERE  `SCHEMA_NAME` NOT IN ('aws_oracle_ext_data', 'aws_sqlserver_ext_data', 'aws_postgresql_ext_data')\n" +
                "                        ORDER BY `SCHEMA_NAME`; ");
   //         ResultSet rs = st.executeQuery("select * from sql11219539.mashine ");
     //       st.executeUpdate("CREATE TABLE machine (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(32),description VARCHAR(256))");
//CREATE TABLE employees (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, lastname VARCHAR(20), firstname VARCHAR(20), phone VARCHAR(20),  dateofbirth DATE)
            while (rs.next()) {
                System.out.println("text: " + rs.getString(1));
            }
            rs.close();


        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        } finally {
            try {
                if (connection!=null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}