package tests.jdbcTest.sqlTest.mscallable;

public class MSCallableTest {
  public static void main(String[] args) {
    String url = "jdbc:sqlserver://192.168.14.77\\SQLSRV2012;  user=min_privs; password=min_privs";


    //   !!!!!!!!!!!!!!!!     Option sql_v02 and sql_v03 are working

    String  sql = SqlCollection.sql_v03;
    MSCalleble msCalleble = new MSCalleble(url);
    msCalleble.exec(sql);
    msCalleble.execCallableStatement(sql);
  }
}
