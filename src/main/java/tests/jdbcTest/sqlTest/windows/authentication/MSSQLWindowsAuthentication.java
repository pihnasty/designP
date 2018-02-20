package tests.jdbcTest.sqlTest.windows.authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSSQLWindowsAuthentication {
    public Connection conn = null;


    public MSSQLWindowsAuthentication(){
    }

    public MSSQLWindowsAuthentication(String dbURL){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(dbURL);//here put the new simple url.
            System.out.println("Connection Ok");
            System.out.println("Stop");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ResultSet runSql(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeQuery(sql);
    }
}
