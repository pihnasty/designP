package tests.jdbcTest.mysql;


import java.sql.*;

public class MySQLTest {

    public static void main(String[] argv) {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
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
                   //     .getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11219539", "sql11219539", "h1eSJdY9Y6");
                    .getConnection("jdbc:mysql://www.db4free.net:3306/productionmodel", "production", "production");


            if (connection != null) {
                System.out.println("You made it, take control your database now!");
            } else {
                System.out.println("Failed to make connection!");
            }

            Statement st = connection.createStatement();

 //           st.executeUpdate("INSERT INTO productionmodel.machine VALUES ( 3,'KD 2323_03',   'Press_03')");
            ResultSet rs = st.executeQuery("select * from productionmodel.machine ");
    //        ResultSet rs = st.executeQuery("select * from sql11219539.mashine ");
     //       st.executeUpdate("CREATE TABLE machine (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(32),description VARCHAR(256))");
//CREATE TABLE employees (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, lastname VARCHAR(20), firstname VARCHAR(20), phone VARCHAR(20),  dateofbirth DATE)
            while (rs.next()) {
                System.out.println("text: " + rs.getString(2));
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