package aws.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

public class SetP {
    public static void main(String[] args) {
        String s = "1";
        String s1 = new String(s);
        String s2 = new String(s);

        Set<Character> set = new HashSet<>();
        set.add('1');
        set.add('2');
        set.add('3');
        set.add('4');
        set.add('1');

    }
}
