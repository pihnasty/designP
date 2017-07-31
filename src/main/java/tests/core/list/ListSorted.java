package tests.core.list;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pihnastyi.O on 7/5/2017.
 */
public class ListSorted {

    private static class AttributeNames {
        private static final String DATABASE_NAME = "database_name";
        private static final String USER_NAME = "user_name";
        private static final String SQL_HISTORY_TABLE = "SQL_history_table";

        private static final String STAT_QUERY_HISTORY_TOP = "stat-query-history-top";
        private static final String STAT_USER_NAMES = "stat-user_names";
        private static final String STAT_GROUPBY_COUNT = "stat-groupby-count";
        private static final String STAT_JOIN_COUNT = "stat-join-count";
        private static final String STAT_ORDERBY_COUNT = "stat-orderby-count";
        private static final String STAT_WHERE_COUNT = "stat-where-count";
        private static final String STAT_IS_EXPENSIVE_QUERY = "stat-is-expensive-query";

        private static final String STAT_QUERY_TEXT = "stat-query-text";
        private static final String STAT_QUERY_COUNT = "stat-query-count";
        private static final String STAT_QUERY_ID = "stat-query-id";
        private static final String STAT_SQL_ROW_NO = "stat-sql-row-no";
        private static final String STAT_QUERY_PARTS = "stat-query-parts";

        private static final String STAT_COLLECTED = "stat-collected";
        private static final String STAT_COLLECT_MODE = "stat-collect-mode";
    }

    public static void main(String[] args) throws IOException {

        List <Map <String,String> >  teradataList = new ArrayList<>();
        //try (CSVReader reader = new CSVReader(new FileReader("C:\\ASCT\\Statistics\\Teradata\\query-stats-tera_longDELETE.csv"),';'))
        //try (CSVReader reader = new CSVReader(new FileReader("src\\main\\java\\tests\\core\\list\\query-stats-tera_longDelete.csv"),';'))
        try (CSVReader reader = new CSVReader(new FileReader("src\\main\\java\\tests\\core\\list\\query-stats-tera_K.csv"),';'))
        { //Delete
            String [] row;
            row = reader.readNext();
            while ((row = reader.readNext()) != null) {
                if (row.length == 1 && row[0].isEmpty()) continue;
                Map<String,String> r = new HashMap<>();
                r.put(AttributeNames.STAT_QUERY_TEXT,row[0]);
                r.put(AttributeNames.STAT_QUERY_COUNT,row[1]);
                r.put(AttributeNames.STAT_QUERY_ID,row[2]);
                r.put(AttributeNames.STAT_SQL_ROW_NO,row[3]);
                r.put(AttributeNames.STAT_QUERY_PARTS,row[4]);
                r.put(AttributeNames.STAT_COLLECTED,row[5]);
                teradataList.add(r);
            }

        }


//        Map<String,String> r = new HashMap<>();
//        r.put(AttributeNames.STAT_QUERY_TEXT,"12345");
//        r.put(AttributeNames.STAT_QUERY_COUNT,"5");
//        r.put(AttributeNames.STAT_QUERY_ID,"130719172351307191723517612818");
//        r.put(AttributeNames.STAT_SQL_ROW_NO,"1");
//        r.put(AttributeNames.STAT_QUERY_PARTS,"3");
//        teradataList.add(r);
//
//        r = new HashMap<>();
//        r.put(AttributeNames.STAT_QUERY_TEXT,"123455_13");
//        r.put(AttributeNames.STAT_QUERY_COUNT,"3");
//        r.put(AttributeNames.STAT_QUERY_ID,"30719172351307191723517612813");
//        r.put(AttributeNames.STAT_SQL_ROW_NO,"3");
//        r.put(AttributeNames.STAT_QUERY_PARTS,"3");
//        teradataList.add(r);
//
//        r = new HashMap<>();
//        r.put(AttributeNames.STAT_QUERY_TEXT,"123455");
//        r.put(AttributeNames.STAT_QUERY_COUNT,"5");
//        r.put(AttributeNames.STAT_QUERY_ID,"30719172351307191723517612818");
//        r.put(AttributeNames.STAT_SQL_ROW_NO,"2");
//        r.put(AttributeNames.STAT_QUERY_PARTS,"3");
//        teradataList.add(r);
//
//
//        r = new HashMap<>();
//        r.put(AttributeNames.STAT_QUERY_TEXT,"123455_5");
//        r.put(AttributeNames.STAT_QUERY_COUNT,"5");
//        r.put(AttributeNames.STAT_QUERY_ID,"530719172351307191723517612818");
//        r.put(AttributeNames.STAT_SQL_ROW_NO,"1");
//        r.put(AttributeNames.STAT_QUERY_PARTS,"3");
//        teradataList.add(r);


//        List <Map <String,String> >  teradataList2 = new ArrayList<>();
//        teradataList.stream().sorted(
//                (r1, r2) -> {
//                    int resultCompareValue = r1.get(AttributeNames.STAT_QUERY_COUNT).compareTo(r2.get(AttributeNames.STAT_QUERY_COUNT));
//                    if (resultCompareValue != 0) return -resultCompareValue;
//                    else
//                        resultCompareValue = r1.get(AttributeNames.STAT_QUERY_ID).compareTo(r2.get(AttributeNames.STAT_QUERY_ID));
//                    return (resultCompareValue == 0) ? r1.get(AttributeNames.STAT_SQL_ROW_NO).compareTo(r2.get(AttributeNames.STAT_SQL_ROW_NO)) : resultCompareValue;
//                }
//        ).forEach(e -> teradataList2.add(e));

//                Collections.sort(teradataList,
//                        (r1, r2) -> {
//                            int resultCompareValue = r1.get(AttributeNames.STAT_QUERY_COUNT).compareTo(r2.get(AttributeNames.STAT_QUERY_COUNT));
//                            if (resultCompareValue != 0) return -resultCompareValue;
//                            else
//                                resultCompareValue = r1.get(AttributeNames.STAT_QUERY_ID).compareTo(r2.get(AttributeNames.STAT_QUERY_ID));
//                            return (resultCompareValue == 0) ? r1.get(AttributeNames.STAT_SQL_ROW_NO).compareTo(r2.get(AttributeNames.STAT_SQL_ROW_NO)) : resultCompareValue;
//                        }
//                );

        teradataList.stream().sorted(
                (e1, e2) -> {
                    int resultCompareValue = (e1.get(AttributeNames.STAT_QUERY_COUNT)).compareTo(e2.get(AttributeNames.STAT_QUERY_COUNT));
                    if (resultCompareValue != 0) return -resultCompareValue;
                    else
                        resultCompareValue = (e1.get(AttributeNames.STAT_QUERY_ID)).compareTo(e2.get(AttributeNames.STAT_QUERY_ID));
                    return (resultCompareValue == 0) ? (e1.get(AttributeNames.STAT_SQL_ROW_NO)).compareTo(e2.get(AttributeNames.STAT_SQL_ROW_NO)) : resultCompareValue;
                }
        ).//limit(5).
//                       forEach(e -> teradataList2.add(e));
        collect(Collectors.toList());

        System.out.println("");

    }
}





//    private void processQueryRelatedOfflineStatistics2(TreeNode node, CSVReader reader) throws IOException {
//        Map<String, Map<String, Integer>> queries = new HashMap<>();
//        String [] row;
//        if ("schema".equals(node.getType())) {
//            String statCollect = "";
//            while ((row = reader.readNext()) != null) {
//                if (row.length ==1 && row[0].isEmpty()) continue;
//                Map<String, Integer> values = new HashMap<>();
//                values.put(AttributeNames.STAT_QUERY_COUNT, Integer.parseInt(row[1]));
//
//                if (getVendorType() == DatabaseVendor.REDSHIFT) {
//                    values.put(AttributeNames.STAT_IS_EXPENSIVE_QUERY, Integer.parseInt(row[2]));
//                    statCollect = row[3];
//                } else statCollect = row[2];
//
//                queries.put(row[0], values);
//            }
//            queries=sortedTopN(queries);
//            addAttributeHistoryStat(node, queries, statCollect);
//        }
//    }