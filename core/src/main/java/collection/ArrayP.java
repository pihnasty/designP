package collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayP {
    public static void main(String[] args) {
        String [] strings = {"1","2","3","4","5"};

        String [][] strings1 = {{"1","2","3"},{"1","2","3"},{"1","2","3"}};
        String [][] strings2 = {{"1","2","3"},{"1","2","3"},{"1","2","3"}};

        Arrays.deepEquals(strings1,strings2);
        List<String> list = Arrays.stream(strings).map(e -> e+"New").collect(Collectors.toList());

        List<List<String>> list1 = Arrays.stream(strings1).map(e -> Arrays.stream(e).collect(Collectors.toList())).collect(Collectors.toList());

    }
}
