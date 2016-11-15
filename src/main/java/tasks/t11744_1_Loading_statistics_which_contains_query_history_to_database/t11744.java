package tasks.t11744_1_Loading_statistics_which_contains_query_history_to_database;

///**
// * Created by Pihnastyi.O on 11/10/2016.
// */
//public class t11744 {
//}
//
//
//
//    private void show(Map<String, Integer> queries, String queryHistoryText) {
//        //show(queries, queryHistoryText);
//        Logger.LOADER.writeDebug(queryHistoryText);
//        for (Map.Entry entry : queries.entrySet()) {
//            String key = (String) entry.getKey();
//            Integer value = (Integer) entry.getValue();
//            Logger.LOADER.writeDebug("value=  %s     key=    %s", value, key);
//        }
//    }
//
//
//    private void processQueryRelatedStatistics(TreeNode node, List<StatsQueryProperty> statsQueries) throws DbLoaderException {
//
//        //   if (false)
//        if ("schema".equals(node.getType()))
//            if (node.getRoot().getAttribute("vendor") == DatabaseVendor.GREENPLUM.name()) {
//                StatsQueryProperty statsQueryHistory = null;
//                for (StatsQueryProperty statsQuery : statsQueries) {
//                    if (statsQuery.getQueryOptions().getKey().equals("load-query-history-stat-by-schema")) {
//                        String queryHistoryText = sqlStatementExecutor.getQueryCollection().get(statsQuery.getQueryOptions().getKey()).getText();
//                        statsQueryHistory = statsQuery;
//                        queryHistoryText = queryHistoryText.replace("{0}", "'" + ((GreenPlumConnectionProperties) getConnectionProperties()).getDatabaseName() + "'").replace("{1}", node.getAttribute("name"));
//                        Connection con = null;
//                        ResultSet rs;
//                        Map<String, Integer> queries = new HashMap<>();
//                        try {
//                            con = DriverManager.getConnection(((GreenPlumConnectionProperties) getConnectionProperties()).getUrlForStatistucs(),
//                                    getConnectionProperties().getProperties());
//                            rs = con.createStatement().executeQuery(queryHistoryText);
//                            while (rs.next()) queries.put(rs.getString(1), rs.getInt(2));
//                        } catch (SQLException e) {
//                            throw new DbLoaderHandledException(e.getMessage(), e, DatabaseVendor.GREENPLUM);
//                        } finally {
//                            if (con != null) try {
//                                con.close();
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        TreeAnalyzer treeAnalyzer = TreeAnalyzerFactory.getTreeAnalyzer(queries, node, DbLoader.getDbLoader(DatabaseVendor.GREENPLUM));
//                        AnalyzingResult analyzingResult = treeAnalyzer.collectStatistics();
//                        for (FullNameNodeInfoList key : analyzingResult.getStatistics().keySet()) {
//                            TreeNode treeNode = node.getRoot().getTree().searchNodeByFullName(key, true).getAssociateNode();
//                            OptimizationStatisticsManager optimizationStatisticsManager = analyzingResult.getStatistics().get(key);
//                            treeNode.setAttribute("InGroupByUsageCount", optimizationStatisticsManager.getInGroupByUsageCount().toString());
//                            treeNode.setAttribute("InJoinUsageCount", optimizationStatisticsManager.getInJoinUsageCount().toString());
//                            treeNode.setAttribute("InOrderByUsageCount", optimizationStatisticsManager.getInOrderByUsageCount().toString());
//                            treeNode.setAttribute("InWhereUsageCount", optimizationStatisticsManager.getInWhereUsageCount().toString());
//                        }
//                    }
//                }
//                if (statsQueryHistory != null) statsQueries.remove(statsQueryHistory);
//            }
//    }
//
//    private void processQueryRelatedStatistics(Context context, List<StatsQueryProperty> statsQueries, TreeNode node) throws DbLoaderException {
//
//        //   if (false)
//        if ("schema".equals(node.getType())) {
//            if (node.getRoot().getAttribute("vendor") == DatabaseVendor.GREENPLUM.name()) {
//                context.put("database_name", ((GreenPlumConnectionProperties) getConnectionProperties()).getDatabaseName());
//                StatsQueryProperty statsQueryHistory = null;
//                Connection con = null;
//                String queryName = "load-query-history-stat-by-schema";
//                //  Map<String, Query> queryCollection = sqlStatementExecutor.getQueryCollection();
//                Query queryStatHistory = sqlStatementExecutor.getQueryCollection().get(queryName);
//                Map<String, Integer> queries = new HashMap<>();
//
////                statsQueryHistory =  statsQueries.stream().filter(statsQuery -> statsQuery.getQueryOptions().getKey().equals(queryName)).collect( Collectors.toList()).get(0);
//
//                for (StatsQueryProperty statsQuery : statsQueries)
//                    if (statsQuery.getQueryOptions().getKey().equals(queryName)) statsQueryHistory = statsQuery;
//
//                try {
//                    con = DriverManager.getConnection(((GreenPlumConnectionProperties) getConnectionProperties()).getUrlForStatistucs(), getConnectionProperties().getProperties());
//                    List<Map<String, String>> rows = queryStatHistory.executeSelectQuery(con, "", statsQueryHistory.getQueryOptions().getResult(), context.get());
//
//
//                    queryStatHistory.executeSelectQuery(con, "", statsQueryHistory.getQueryOptions().getResult(), context.get())
//                            .stream().map( row -> queries.put(row.get("stat-query-text"), Integer.parseInt(row.get("stat-query-count")))).count();
//
////                    for (Map<String, String> row : rows) {
////
////
////                        queries.put(row.get("stat-query-text"), Integer.parseInt(row.get("stat-query-count")));
////
////                    }
//
//
//                } catch (SQLException e) {
//                    throw new DbLoaderHandledException(e.getMessage(), e, DatabaseVendor.GREENPLUM);
//                } finally {
//                    if (con != null) try {
//                        con.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                TreeAnalyzer treeAnalyzer = TreeAnalyzerFactory.getTreeAnalyzer(queries, node, DbLoader.getDbLoader(DatabaseVendor.GREENPLUM));
//                AnalyzingResult analyzingResult = treeAnalyzer.collectStatistics();
//                for (FullNameNodeInfoList key : analyzingResult.getStatistics().keySet()) {
//                    TreeNode treeNode = node.getRoot().getTree().searchNodeByFullName(key, true).getAssociateNode();
//                    OptimizationStatisticsManager optimizationStatisticsManager = analyzingResult.getStatistics().get(key);
///*  For testing
//                    treeNode.setAttribute("InGroupByUsageCount", optimizationStatisticsManager.getInGroupByUsageCount().toString());
//                    treeNode.setAttribute("InJoinUsageCount", optimizationStatisticsManager.getInJoinUsageCount().toString());
//                    treeNode.setAttribute("InOrderByUsageCount", optimizationStatisticsManager.getInOrderByUsageCount().toString());
//                    treeNode.setAttribute("InWhereUsageCount", optimizationStatisticsManager.getInWhereUsageCount().toString());
//*/
//                }
//                if (statsQueryHistory != null) statsQueries.remove(statsQueryHistory);
//            }
//        }
//    }