package utilp.hashset;

import java.util.HashSet;

public class HashSetP{

    public static void main(String[] args) {
        HashSetP hashSetP = new HashSetP();
        hashSetP.case01();


    }

    private void case01 () {

        HashSet<String> hashSet = new HashSet<>();

        String s1 ="Hello";
        String s2 ="Hello";

        hashSet.add(s1);
        hashSet.add(s2);

        hashSet.add(s1);
        hashSet.add(s1);
    }

}
