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
            ResultSet rs = st.executeQuery("CREATE TABLE productionmodel.machine (id INT(10),name VARCHAR(64),description VARCHAR(256))");


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