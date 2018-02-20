package tests.jdbcTest.sqlTest.windows.authentication;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MSSQLWindowsAuthentication msWinAuth =
     //          new MSSQLWindowsAuthentication("jdbc:sqlserver://WS-PIHNASTYI\\SQLEXPRESS; databaseName=p6test; integratedSecurity=true; ");                     //    user=DBBEST\Pihnastyi.O
              //        new MSSQLWindowsAuthentication("jdbc:sqlserver://WS-PIHNASTYI\\SQLEXPRESS; databaseName=p6test; user=pom; password=Jktu1969");

  //      new MSSQLWindowsAuthentication("jdbc:sqlserver://vm-gbmssql01\\SQLSRV2012; databaseName=GOLD_TEST_SS_PG; user=min_privs; password=min_privs");
     //  new MSSQLWindowsAuthentication("jdbc:sqlserver://vm-gbmssql01\\SQLSRV2012; databaseName=GOLD_TEST_SS_PG; integratedSecurity=true; ");
        new MSSQLWindowsAuthentication("jdbc:sqlserver://vm-gbmssql01\\SQLSRV2012; databaseName=GOLD_TEST_SS_PG;  " +
                "integratedSecurity=true; domain=DBBEST;  " +
                "integSecurity = true");

                //               new MSSQLWindowsAuthentication("jdbc:sqlserver://127.0.0.1\\SQLEXPRESS; databaseName=p6test; user=pom; password=Jktu1969");
                //          new MSSQLWindowsAuthentication("jdbc:sqlserver://localhost\\SQLEXPRESS;  databaseName=p6test; user=pom; password=Jktu1969");
        //domain=DBBEST;

        //            new MSSQLWindowsAuthentication("jdbc:sqlserver://localhost:1433; databaseName=p6test; user=pom; password=Jktu1969");
  //              new MSSQLWindowsAuthentication("jdbc:sqlserver://127.0.0.1:1433; databaseName=p6test; user=pom; password=Jktu1969");
//        new MSSQLWindowsAuthentication("jdbc:sqlserver://localhost\\SQLEXPRESS:1433; databaseName=p6test; user=pom; password=Jktu1969");
//               new MSSQLWindowsAuthentication("jdbc:sqlserver://127.0.0.1\\SQLEXPRESS; databaseName=p6test; user=pom; password=Jktu1969");
     //          new MSSQLWindowsAuthentication("jdbc:sqlserver://localhost\\SQLEXPRESS;  databaseName=p6test; user=pom; password=Jktu1969");
//        new MSSQLWindowsAuthentication("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;  databaseName=p6test; user=pom; password=Jktu1969");

   //      new MSSQLWindowsAuthentication("jdbc:sqlserver://WS-PIHNASTYI\\SQLEXPRESS; databaseName=p6test; user=sa;password=Jktu1969");


             //   new MSSQLWindowsAuthentication("jdbc:sqlserver://WS-PIHNASTYI\\SQLEXPRESS;integratedSecurity=true;user=DBBEST\\Pihnastyi.O");

                //       new MSSQLWindowsAuthentication("jdbc:sqlserver://52.37.30.252:1433;integratedSecurity=true; databaseName=SS_DWH; user=DBBEST\\Pihnastyi.O;password=Jktu69");

        //new MSSQLWindowsAuthentication("jdbc:sqlserver://52.37.30.252:1433;databaseName=SS_DWH;user=min_privs;password=min_privs");   DBBEST\Pihnastyi.O
        // jdbc:sqlserver://localhost;integratedSecurity=true;

//        ResultSet rs = msWinAuth.runSql("select * from p6test.dbo.Personal");
//
//        while (rs.next()) {
//            String col1 = rs.getString(1);
//            String col2 = rs.getString(2);
//            String col3 = rs.getString(3);
//            System.out.println(col1+" "+col2+" "+col3);
//        }


    }
}


//