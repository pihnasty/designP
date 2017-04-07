package tests.core.map.hashmap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pihnastyi.O on 3/23/2017.
 */
public class SortedHashMapMyComporated {
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
                                {{            put("one",new HashMap<String, Integer>(){{put(AttributeNames.STAT_QUERY_COUNT,5);}});
                                              put("atwo",new HashMap<String, Integer>(){{put(AttributeNames.STAT_QUERY_COUNT,5);}});
                                              put("three",new HashMap<String, Integer>(){{put(AttributeNames.STAT_QUERY_COUNT,31);}});
                                              put("four",new HashMap<String, Integer>(){{put(AttributeNames.STAT_QUERY_COUNT,5);}});
                                              put("five",new HashMap<String, Integer>(){{put(AttributeNames.STAT_QUERY_COUNT,31);}});        }};


        System.out.println(sortedTopN(map));


    }

    private static Map<String, Map<String, Integer>> sortedTopN(Map<String, Map<String, Integer>> unSortedHashMap) {
        String statQueryHistoryTop = "3";
        Integer count = Integer.parseInt(statQueryHistoryTop);

        Map<String, Map<String, Integer>> sortedHashMap = new HashMap<>();

        if(unSortedHashMap.size()<count) return unSortedHashMap;


            Comparator<Map.Entry <String, Map<String, Integer>>> comparator =  new Comparator<Map.Entry <String, Map<String, Integer>>>() {


                public int compare(Map.Entry <String, Map<String, Integer>>e1, Map.Entry <String, Map<String, Integer>>e2) {


                    int resultCompareValue = e1.getValue().get(AttributeNames.STAT_QUERY_COUNT).compareTo( e2.getValue().get(AttributeNames.STAT_QUERY_COUNT));

                    return (resultCompareValue)==0
                            ?
                            (e1.getKey()).compareTo( e2.getKey() )
                                    :
                            - resultCompareValue
                             ;
                }


            };



        unSortedHashMap.entrySet().stream().sorted(
             //   Comparator.comparing(e -> -e.getValue().get(AttributeNames.STAT_QUERY_COUNT))

          //      comparator
                (e1, e2) -> {
                    int resultCompareValue = e1.getValue().get(AttributeNames.STAT_QUERY_COUNT).compareTo(e2.getValue().get(AttributeNames.STAT_QUERY_COUNT));
                    return (resultCompareValue) == 0 ? (e1.getKey()).compareTo(e2.getKey()) : -resultCompareValue;
                }



                )
                .skip(0).limit(count)
                .forEach(e -> sortedHashMap.put(e.getKey(), e.getValue()));
        return sortedHashMap;
    }

}

//    Comparator comparator =  new Comparator() {
//    public int compare(Object o1, Object o2) {
//        Map.Entry e1 = (Map.Entry) o1;
//        Map.Entry e2 = (Map.Entry) o2;
//        Comparable c1 = (Comparable) e1.getValue();
//        Comparable c2 = (Comparable) e2.getValue();
//        return c1.compareTo(c2);
//    }
//};