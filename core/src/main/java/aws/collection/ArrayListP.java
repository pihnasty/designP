package aws.collection;


import java.util.*;
import java.util.LinkedList;

public class ArrayListP {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        Stack<Integer> stack = new Stack();
        stack.add(0);
        stack.add(1);

        HashSet<Integer> set = new HashSet();

        set.add(null);
        set.add(1);
        set.add(3);
        set.add(33);
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(7+16);
        set.add(7+2*16);
        set.add(7+3*16);

        String str = "virtual";

        System.out.println(str.hashCode());

        list.listIterator(4);


        System.out.println(118 & 15);
        System.out.println(29 & 6);

        Integer [] strings = new Integer[] {11,21,31,41,51,61,71,81,91,100};


        System.out.println(list.toArray(strings));


        ArrayList<String> list1 = new ArrayList<>();

        list.add(3,33);
        System.out.println(list);



    }
}
