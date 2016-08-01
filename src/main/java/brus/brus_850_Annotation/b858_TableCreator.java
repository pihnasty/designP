package brus.brus_850_Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pihnastyi.O on 7/29/2016.
 */
public class b858_TableCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        System.out.println(args[0].toString());

        for(String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getDeclaredAnnotation(DBTable.class);

            if (dbTable==null) {
                System.out.println("No DBTable");
                System.exit(0);
            }
            String tableName= (dbTable.name().length() >0) ? dbTable.name() : cl.getName().toUpperCase();

            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length<1) {
                    System.out.println("No annotation"); continue; }
                System.out.println("Аннотация поля");
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    columnName = (sInt.name().length() > 0) ? sInt.name() : field.getName().toUpperCase();
                    columnDefs.add(columnName);


                }
            }



        }


    }
}

