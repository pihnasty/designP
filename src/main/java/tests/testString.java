package tests;

/**
 * Created by Pihnastyi.O on 10/20/2016.
 */
public class testString {
    public static void main(String [] agrs){
        String s;

        s=null;

        if (  s == null  ||  s.isEmpty())  System.out.println("Yes");


                String queryHistory =  " select query_text, query_count\n" +
                        "                            from\n" +
                        "                            (\n" +
                        "                            select query_text, count(*) query_count\n" +
                        "                            from public.queries_history\n" +
                        "                            where db = {0}\n" +                          // {0}     'postgres'
                        "                             and\n" +
                        "                             query_text <>''\n" +
                        "                             and lower(query_text) like '%{1}%'    " +                     // '%green_dwh%'      '%{1}%'

                        "                            group by query_text\n" +
                        "                            ) q\n" +
                        "                            order by query_count desc;";






        System.out.println(queryHistory.replace("{0}", "'postgres'").replace("{1}", "green_dwh"));

       String sLjader = "DbLoaderNоDataToProcessingException";


        char[] chArray = sLjader.toCharArray();


        for (int i=0; i<chArray.length; i++) {
            System.out.println("code="+(int)chArray[i]+"\tsumbol="+chArray[i]);
        }



    }
}
