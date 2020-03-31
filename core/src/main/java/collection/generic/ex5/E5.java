package collection.generic.ex5;

import java.util.ArrayList;
import java.util.List;

public class E5 {

    public static void main(String ... args) {
        List<Integer> ints = new ArrayList<Integer>();
        Class<? extends List> k = ints.getClass();
        assert k == ArrayList.class;
    }


}
