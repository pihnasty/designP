package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListP {
    public static void main(String[] args) {

        //deepEquals();

        List<String> list_1 = new ArrayList<>();
        list_1.add("A1");
        list_1.add("A2");
        list_1.add("A3");

        List<String> list_12 = new ArrayList<>();
        list_12.add("A12");
        list_12.add("A22");
        list_12.add("A32");

        List<String> list_2 = new ArrayList<>(list_1);
        list_1.set(1,"aSet");
        System.out.println(list_2);

        ArrayList<List<String>> lists_P1 = new ArrayList<>();
        lists_P1.add(list_1);
        lists_P1.add(list_12);
        ArrayList<List<String>> lists_P2 = new ArrayList<>();

        lists_P2=(ArrayList<List<String>>)lists_P1.clone();


        lists_P2.get(1).set(1,"A22set122");
        System.out.println(lists_P2);
        System.out.println(lists_P1);











    }

    private static void deepEquals() {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();

        List list = new ArrayList();
        list.clear();

        String [] strings = {"1","2","3","4","5"};

        String [][] strings1 = {{"1","2","3"},{"1","2","3"},{"1","2","3"}};
        String [][] strings2 = {{"1","2","3"},{"1","2","3"},{"1","2","3"}};


        Arrays.deepEquals(strings1,strings2);


        List<String> stringList = new ArrayList<>();

        stringList = Arrays.asList(strings);


        stringList.set(1,"3");


        System.out.println();
    }
}
