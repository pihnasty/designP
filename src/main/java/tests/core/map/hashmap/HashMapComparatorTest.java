package tests.core.map.hashmap;

import java.util.*;

public class HashMapComparatorTest {
    public static void main(String[] args) {
        Map map = new HashMap() {{
            put("one",1);
            put("two",2);
            put("three",3);
            put("four",4);
            put("five",1);
        }};
        List entryList = new ArrayList(map.entrySet());
        Collections.sort(entryList, new Comparator() {
            public int compare(Object o1, Object o2) {
                Map.Entry e1 = (Map.Entry) o1;
                Map.Entry e2 = (Map.Entry) o2;
                Comparable c1 = (Comparable) e1.getValue();
                Comparable c2 = (Comparable) e2.getValue();
                return c1.compareTo(c2);
            }
        });
        System.out.print(entryList);
        // [five=fifth, one=first, four=fourth, two=second, three=third]
    }
}
