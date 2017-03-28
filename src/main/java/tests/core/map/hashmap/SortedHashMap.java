package tests.core.map.hashmap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Pihnastyi.O on 3/23/2017.
 */
public class SortedHashMap {
    public static void main(String[] args) {

//        Stream<Map.Entry<String,Integer>> st = map.entrySet().stream();

  //      (map.entrySet().stream().sorted(Comparator.comparing(e -> -e.getValue())).skip(0).limit(2)).forEach(e ->result.put(e.getKey(),e.getValue()));


        Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>()
                                {{            put("one",new HashMap<String, Integer>(){{put("count",5);}});
                                              put("two",new HashMap<String, Integer>(){{put("count",2);}});
                                              put("three",new HashMap<String, Integer>(){{put("count",31);}});
                                              put("four",new HashMap<String, Integer>(){{put("count",4);}});
                                              put("five",new HashMap<String, Integer>(){{put("count",31);}});        }};


        System.out.println(sortedTopN(map));


    }

    private static Map<String, Map<String, Integer>> sortedTopN(Map<String, Map<String, Integer>> unSortedHashMap) {
        String statQueryHistoryTop = "3";
        Integer count = Integer.parseInt(statQueryHistoryTop);

        Map<String, Map<String, Integer>> sortedHashMap = new HashMap<>();

        if(unSortedHashMap.size()<count) return unSortedHashMap;

        unSortedHashMap.entrySet().stream().sorted(Comparator.comparing(e -> -e.getValue().get("count")))
                .skip(0).limit(count)
                .forEach(e -> sortedHashMap.put(e.getKey(), e.getValue()));
        return sortedHashMap;
    }

    private static Map<String, Map<String, Integer>> sortedTopN2(Map<String, Map<String, Integer>> unSortedHashMap){
        Map<String, Map<String, Integer>> sortedHashMap = new HashMap<>();
        Stream<Map.Entry<String,Map<String, Integer>>> stream = sortedHashMap.entrySet().stream();

        stream.sorted(
                Comparator.comparing(e -> - e.getValue().get("count") )
        ).skip(0).limit(2).forEach(e ->sortedHashMap.put(e.getKey(),e.getValue()));
        return sortedHashMap;
    }



}
