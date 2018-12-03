package jdbcTest.sybase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SybaseTest {


    public static void main(String[] argv) {

        String queryText = "                        SELECT  u.name   AS schema_name,\n" +
            "                                o.name   AS view_name,\n" +
            "                                o.crdate AS create_date\n" +
            "                          FROM      test_sapase.dbo.sysobjects o\n" +
            "                               JOIN test_sapase.dbo.sysusers u ON o.uid = u.uid\n" +
            "                         WHERE o.type = 'V'\n" +
            "                           AND u.name = 'dbo'--schema_name";

        System.out.println("-------- SybaseTest JDBC Connection Testing ------------");

        try {
            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;



        try {
            connection = DriverManager
                    //      .getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
                //        .getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11219539", "sql11219539", "Prod2018Prod");
                    .getConnection("jdbc:sybase:Tds:192.168.14.36:5443/test_sapase?ENABLE_SSL=true&EncryptionMethod=SSL&TrustStore=C:\\MyCertificates\\sybase\\truststore.jks&ValidateServerCertificate=true&loginTimeout=20&TrustStorePassword=sybase"
                        ,"min_privs","min_privs");


            if (connection != null) {
                System.out.println("You made it, take control your database now!");
            } else {
                System.out.println("Failed to make connection!");
            }

          //  Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            Statement st = connection.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);


 //           st.executeUpdate("INSERT INTO productionmodel.machine VALUES ( 3,'KD 2323_03',   'Press_03')");
            ResultSet rs = st.executeQuery( queryText);
   //         ResultSet rs = st.executeQuery("select * from sql11219539.mashine ");
     //       st.executeUpdate("CREATE TABLE machine (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(32),description VARCHAR(256))");
//CREATE TABLE employees (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, lastname VARCHAR(20), firstname VARCHAR(20), phone VARCHAR(20),  dateofbirth DATE)
            while (rs.next()) {

rs.getType();

                rs.updateString(2, "hello");
                System.out.println("text: " + rs.getString(2)+"    ----   " );


            }

            System.out.println("---------------------------------------------------------------------------");

            while (rs.next()) {



                System.out.println("text: " + rs.getString(2)+"    ----   " );


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