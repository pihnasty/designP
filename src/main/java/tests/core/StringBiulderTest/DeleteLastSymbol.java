package tests.core.StringBiulderTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pihnastyi.O on 11/20/2017.
 */
public class DeleteLastSymbol {
    public static void main(String[] args) {
        String s1 = "; /*     123    */          /* rrrr */   CREATE  REPLACE \r\n" +
                " VIEW test_db2.V_UPDATABLE_ -- CHECK_CASCADED  (N) AS \r\n" +
                "SELECT\n" +
                "    n AS n\n" +
                " /*POM*/  FROM TEST_DB2.\n" +
                "   V_UPDATABLE_CHECK_LOCAL\n" +
                "   WHERE n > 100 WITH CASCADED CHECK \n" +
                "      d           OPTION \n" +
                " -- ; -- d -- ; REPLACE2  \n" +
                "                  ;";



        StringBuilder sb = new StringBuilder(s1);


        List<Map<String, Object>> listComment1 = regExp("/\\*.*?\\*/", sb);
        listComment1.stream().forEach(e -> System.out.println(e.get("startPosition") + "   " + e.get("endPosition") + "  " + e.get("text")));

        System.out.println("-------2--------------");
        List<Map<String, Object>> listComment2 = regExp("-- .*?[\r]?\n", sb);
        listComment2.stream().forEach(e -> System.out.println(e.get("startPosition") + "   " + e.get("endPosition") + "  " + e.get("text")));

        System.out.println("-------21--------------");
        List<Map<String, Object>> listComment21 = regExp("-- .*$", sb);
        listComment21.stream().forEach(e -> System.out.println(e.get("startPosition") + "   " + e.get("endPosition") + "  " + e.get("text")));

        System.out.println("-------3--------------");
        List<Map<String, Object>> listComment3 = regExp("# .*?[\r]?\n", sb);
        listComment3.stream().forEach(e -> System.out.println(e.get("startPosition") + "   " + e.get("endPosition") + "  " + e.get("text")));

        System.out.println("-------31--------------");
        List<Map<String, Object>> listComment31 = regExp("#.*$", sb);
        listComment31.stream().forEach(e -> System.out.println(e.get("startPosition") + "   " + e.get("endPosition") + "  " + e.get("text")));


        System.out.println(  sb + "size="+ sb.length());
        System.out.println( "---------------------------" );
        //System.out.println(  regExpReplaseComment ("-- .*?[\r]?\n", sb)  );
//        System.out.println(  regExpReplaseComment ("/\\*.*?\\*/", sb)  );
         semicolonLastSymbolDelete (sb);

        System.out.println(sb + "size="+ sb.length());



//        System.out.println("-------2--------------");
//        List <Map<String,Object> > listComment3 = regExp("-- .*(?m)^\\s*-- .*$",sb);
//        listComment3.stream().forEach(  e-> System.out.println(e.get("startPosition")+"   "+ e.get("endPosition")    +"  " + e.get("text")   ));


//        System.out.println("-------1a--------------");
//        List <Map<String,Object> > listComment1a = regExp("-- .*(?m)^\\s*-- .*$",sb);
//        listComment1a.stream().forEach(  e-> System.out.println(e.get("startPosition")+"   "+ e.get("endPosition")    +"  " + e.get("text")   ));
//
//        System.out.println("-------1b--------------");
//        List <Map<String,Object> > listComment1b = regExp("-- .*^\\s*-- .*$",sb);
//        listComment1b.stream().forEach(  e-> System.out.println(e.get("startPosition")+"   "+ e.get("endPosition")    +"  " + e.get("text")   ));
//
//        System.out.println("------5---------------");
//        List <Map<String,Object> > listComment5 = regExp("(?m)^\\s*#.*$",sb);
//        listComment5.stream().forEach(  e-> System.out.println(e.get("startPosition")+"   "+ e.get("endPosition") )    );

    }

    private static List<Map<String, Object>> regExp(String shablon, StringBuilder text) {

        List<Map<String, Object>> coordinatesExpressions = new ArrayList();

        Pattern pat = Pattern.compile(shablon, Pattern.DOTALL);
        Matcher mat = pat.matcher(text);
        while (mat.find()) {
            Map<String, Object> map = new HashMap<>();
            map.put("startPosition", mat.start());
            map.put("endPosition", mat.end());
            map.put("text", mat.group());
            coordinatesExpressions.add(map);
        }

        return coordinatesExpressions;

    }

    private static StringBuilder regExpReplaseComment (String shablon, StringBuilder originalText) {
        StringBuilder text = new StringBuilder(originalText);
        List<Map<String, Object>> coordinatesExpressions = new ArrayList();
        Pattern pat = Pattern.compile(shablon, Pattern.DOTALL);
        Matcher mat = pat.matcher(text);
        while (mat.find()) {
            Map<String, Object> map = new HashMap<>();
            map.put("startPosition", mat.start());
            map.put("endPosition", mat.end());
            map.put("text", mat.group());
            coordinatesExpressions.add(map);
        }
        Function<Integer,StringBuilder> constructStringSpaces = (size)-> { StringBuilder sb = new StringBuilder();
            for (int i=0; i<size; i++) sb.append(" ");
            return sb; };
        coordinatesExpressions.stream().forEach(m ->
                        text.replace((int) m.get("startPosition"), (int) m.get("endPosition"), new StringBuilder(constructStringSpaces.apply(
                                 (int) m.get("endPosition") -(int) m.get("startPosition")
                        )).toString() )
                );
        return text;
    }

    private static void semicolonLastSymbolDelete(StringBuilder sb) {
        StringBuilder _sb =
                regExpReplaseComment("/\\*.*?\\*/",
                        regExpReplaseComment("#.*$",
                                regExpReplaseComment("#.*?[\r]?\n",
                                        regExpReplaseComment("-- .*$",
                                                regExpReplaseComment("-- .*?[\r]?\n", sb)
                                        )
                                )
                        )
                );
        int positionLastSymbol = _sb.lastIndexOf(";");
        if (positionLastSymbol != -1)
            positionLastSymbol = (";".equals(_sb.substring(_sb.lastIndexOf(";")).trim())) ? positionLastSymbol : -2;
        if (positionLastSymbol >= 0) sb.deleteCharAt(positionLastSymbol);
    }
}
