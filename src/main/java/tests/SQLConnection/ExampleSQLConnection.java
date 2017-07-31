package tests.SQLConnection;

import java.sql.SQLException;

/**
 * Created by Pihnastyi.O on 7/28/2017.
 */
public class ExampleSQLConnection {

    public static void main(String[] args) throws SQLException {

        String sid = null;

        boolean b = !(sid==null || sid.isEmpty());

        System.out.println(b);

   //     System.out.println(sid.isEmpty());
   //     System.out.println(sid==null);



        boolean b2 = (sid!=null || !sid.isEmpty());

        System.out.println(b2);
    }



}
