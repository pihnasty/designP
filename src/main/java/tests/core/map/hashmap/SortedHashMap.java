package tests.core.map.hashmap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Pihnastyi.O on 3/23/2017.
 */
public class SortedHashMap {
    private static class AttributeNames {
        private static final String DATABASE_NAME = "database_name";
        private static final String USER_NAME = "user_name";

        private static final String STAT_QUERY_HISTORY_TOP = "stat-query-history-top";
        private static final String STAT_GROUPBY_COUNT = "stat-groupby-count";
        private static final String STAT_JOIN_COUNT = "stat-join-count";
        private static final String STAT_ORDERBY_COUNT = "stat-orderby-count";
        private static final String STAT_WHERE_COUNT = "stat-where-count";
        private static final String STAT_IS_EXPENSIVE_QUERY = "stat-is-expensive-query";

        private static final String STAT_QUERY_TEXT = "stat-query-text";
        private static final String STAT_QUERY_COUNT = "stat-query-count";
        private static final String STAT_COLLECTED = "stat-collected";
        private static final String STAT_COLLECT_MODE = "stat-collect-mode";
    }



    public static void main(String[] args) {

//        Stream<Map.Entry<String,Integer>> st = map.entrySet().stream();

  //      (map.entrySet().stream().sorted(Comparator.comparing(e -> -e.getValue())).skip(0).limit(2)).forEach(e ->result.put(e.getKey(),e.getValue()));


        Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>()
                                {{            put("one",new HashMap<String, Integer>(){{put("count",5);}});
                                              put("two",new HashMap<String, Integer>(){{put("count",5);}});
                                              put("three",new HashMap<String, Integer>(){{put("count",31);}});
                                              put("four",new HashMap<String, Integer>(){{put("count",5);}});
                                              put("five",new HashMap<String, Integer>(){{put("count",31);}});        }};


        System.out.println(sortedTopN(map));


    }

    private static Map<String, Map<String, Integer>> sortedTopN(Map<String, Map<String, Integer>> unSortedHashMap) {
        String statQueryHistoryTop = "3";
        Integer count = Integer.parseInt(statQueryHistoryTop);

        Map<String, Map<String, Integer>> sortedHashMap = new HashMap<>();

        if(unSortedHashMap.size()<count) return unSortedHashMap;

        unSortedHashMap.entrySet().stream().sorted(Comparator.comparing(e -> -e.getValue().get(AttributeNames.STAT_QUERY_COUNT)))
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
