package collection;

import java.util.HashSet;
import java.util.Set;

public class SetP {
    public static void main(String[] args) {
        Set<String> values = new HashSet<>();

        String s = new String("POM");
        String s2 = new String("POM");
        String s3 = new String("POM");

        values.add(s);
        values.add(s2);
        values.add(s3);


    }
}
