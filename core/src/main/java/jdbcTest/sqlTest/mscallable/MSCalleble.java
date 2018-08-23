package jdbcTest.sqlTest.mscallable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSCalleble {

  private String dbURL;


  public MSCalleble(String dbURL) {
    this.dbURL = dbURL;
  }

  public void exec(String sql) {

    try (Connection connection = DriverManager.getConnection(dbURL);
         Statement statement = connection.createStatement()) {
      System.out.println("Connection Ok");
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        System.out.println(rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      System.out.println("\n\n\n" + sql);
    }
  }

  public void execCallableStatement(String sql) {

    try (Connection con = DriverManager.getConnection(dbURL);
         CallableStatement statement = con.prepareCall(sql)) {
      statement.execute();
      System.out.println("Connection Ok");
      System.out.println("Stop");

      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}
