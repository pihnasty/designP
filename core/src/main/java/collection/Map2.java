package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Map2 {
    public static void main(String[] args) {

        Map<String, String> concat = new HashMap<String, String>() {{
            put("concatKey", "");
            put("columnValue","");
        }};


        concat.put("concatKey", "8");
        concat.put("columnValue","9");

        concat.put("columnValue",null);

        System.out.println(concat);

        List<String> list = new ArrayList<>();
        list.add("+++++");
        list.add("==============");

        String str = list.stream().collect(Collectors.joining(","));

        System.out.println(str);


    }
}

