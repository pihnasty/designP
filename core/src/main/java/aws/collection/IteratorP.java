package aws.collection;

import java.util.*;

public class IteratorP {
    public static void main(String[] args) {

        ArrayList<Integer> lists = new ArrayList<>();
        lists.add(10);
        lists.add(100);
        lists.add(100);
        lists.add(1000);


        ListIterator<Integer> iterator = lists.listIterator();
        while(iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println();
    }
}
