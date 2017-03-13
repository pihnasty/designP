package tests.sqlExceptionTest;

import java.sql.SQLException;

/**
 * Created by Pihnastyi.O on 3/13/2017.
 */
public class SQLExeptionTest01 {
    public static void main(String[] args) {
        SQLException e = new SQLException();

        printSQLException(e);
        while(e != null) {
            System.out.println("SQLState:" + e.getSQLState());
            System.out.println("Error Code:" + e.getErrorCode());
            System.out.println("Message:" + e.getMessage());
            Throwable t = e.getCause();
            while(t != null) {
                System.out.println("Cause:" + t);
                t = t.getCause();
            }
            e = e.getNextException();
        }
    }


    public static void printSQLException(SQLException ex) {

        for (Throwable e : ex) {
            System.err.println("POM Begin--------------------------" );
            if (e instanceof SQLException) {
                System.err.println("POM 2--------------------------" );
                if (ignoreSQLException(
                        ((SQLException)e).
                                getSQLState()) == false) {

                    System.err.println("POM 3--------------------------" );
                    e.printStackTrace(System.err);
                    System.err.println("SQLStatePOM: " +
                            ((SQLException)e).getSQLState());

                    System.err.println("Error CodePOM: " +
                            ((SQLException)e).getErrorCode());

                    System.err.println("MessagePOM: " + e.getMessage());

                    Throwable t = ex.getCause();
                    while(t != null) {
                        System.out.println("CausePOM: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }

    public static boolean ignoreSQLException(String sqlState) {

        if (sqlState == null) {
            System.out.println("The SQL state is not defined!POM");
            return false;
        }

        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32")){
            System.out.println("X0Y32POM");
            return true;
        }

        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55")) {
            System.out.println("42Y55POM");
            return true;
        }
        return false;
    }


}


