package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListP {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
    arrayList.clear();

        List list = new ArrayList();
        list.clear();

        String [] strings = {"1","2","3","4","5"};

        String [][] strings1 = {{"1","2","3"},{"1","2","3"},{"1","2","3"}};
        String [][] strings2 = {{"1","2","3"},{"1","2","3"},{"1","2","3"}};


        Arrays.deepEquals(strings1,strings2);


        List<String> stringList = new ArrayList();

        stringList = Arrays.asList(strings);


        stringList.set(1,"3");


        System.out.println();








    }
}
