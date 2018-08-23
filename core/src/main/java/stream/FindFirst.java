package stream;

import java.util.ArrayList;

public class FindFirst {
    public static void main(String[] args) {
        StringBuilder statement = new StringBuilder();
        ArrayList partitionKeys = new ArrayList() ;
//        {
//            {add("One");}
//        };

        statement.append(partitionKeys.stream().findFirst().get());

        System.out.println(statement);

    }
}
