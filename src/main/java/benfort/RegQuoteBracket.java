package benfort;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class RegQuoteBracket {
    public static void main(String args[]) {
        String
                shablon="\"\\s*\\(";
        shablon="::.*?[,::)]";
   //     shablon="\".+\"?";

   //      shablon="\".+\"";
        shablon="\".*?\"";

        String textFile="\" substring\"  \n  \n\r (\"substring\"::text) 1, 2)        (\"sub string\"::text, 1, 2)               (id + \"substring\"::integer::numeric)";
//        regExp(shablon,textFile);

        Map<Integer,Integer> mapCondition = new HashMap<>();

        mapCondition  = regExp("\"\\s*\\(",textFile);
        out.println(textFile);
        Map<Integer,Integer> mapCommon = new HashMap<>();
        mapCommon  = regExp("\".*?\"",textFile);
        String text2 = unQuoteFunction(textFile,  mapCondition, mapCommon);




        mapCommon  = regExp("\".*?\"",text2);
        String text3 = unQuoteField(text2,  mapCommon);

        Map<Integer,Integer> mapType = new HashMap<>();
        mapType  = regExp("::.*?[,::\\)]",text3 );
        String text4 =  unDeleteType (text3, mapType);

        mapType  = regExp("::.*?[,::\\)]",text4 );
        String text5 =  unDeleteType (text4, mapType);

        out.println(textFile);



          }


    private static String unDeleteType (String text, Map<Integer,Integer> mapType) {

        StringBuilder statement = new StringBuilder(text);

        mapType.keySet().stream().sorted(Comparator.reverseOrder()).forEach(
                key ->
                {
                    statement.delete(key,mapType.get(key));
                }
        );
        return statement.toString();
    }


    private static String unQuoteFunction(String text, Map<Integer,Integer> mapCondition, Map<Integer,Integer> mapCommon) {

        StringBuilder statement = new StringBuilder(text);

        mapCondition.keySet().stream().sorted(Comparator.reverseOrder()).forEach(
                key ->
                {
                    statement.deleteCharAt(key);
                    statement.deleteCharAt(getKeyByValue(key,mapCommon));

                }
        );
        return statement.toString();
    }


    private static String unQuoteField(String text, Map<Integer,Integer> mapCommon) {

        StringBuilder statement = new StringBuilder(text);

        mapCommon.keySet().stream().sorted(Comparator.reverseOrder()).forEach(
                key ->  {
                    String name = statement.substring(key+1,mapCommon.get(key)-1);
                    if(name.equals(quote(name))) {
                        statement.deleteCharAt(mapCommon.get(key));
                        statement.deleteCharAt(key);
                    }
                }
        );
        return statement.toString();
    }


    private static String quote(String text) {
        return text;
    }



    private static int getKeyByValue(int value, Map<Integer,Integer> mapCommon) {
        Integer key=-1;

        for ( Map.Entry<Integer,Integer> entry  : mapCommon.entrySet()) {
            if( entry.getValue().equals(value)) {
                key=entry.getKey();
            }
        }
        return key;
    }







    private static Map<Integer, Integer> regExp(String shablon, String text) {
        Map<Integer, Integer> result = new HashMap<>();
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile(shablon);
        mat = pat.matcher(text);
        while (mat.find()) {
            result.put(mat.start(), mat.end()-1);
        }
        return result;
    }

    private static void regExp2(double number, String shablon,  String text) {
        Pattern pat;
        Matcher mat;


        out.println(number+" ("+shablon+")-------------------------------------------------------");
        out.println(number+" text: \n\""+text+"\" \n");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(text);
        out.println(number+"-------------------------------------------------------");
        while(mat.find()) out.println("Result -> -" + mat.group() + "-    "+mat.start());
    }

    private static void regExp(double number, String shablon, String text, String replaceText) {
        Pattern pat;
        Matcher mat;
        StringBuffer buffer = new StringBuffer();

        out.println(number + " (" + shablon + ")-------------------------------------------------------");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(text);
        while (mat.find()) {
            if (!"".equals(replaceText)) {
                mat.appendReplacement(buffer, replaceText);
                out.println("mat.group() -> " + mat.group() + "    " + mat.start());
                out.println("                    buffer ->" + buffer);
            } else
                out.println("mat.group() -> " + mat.group() + "    " + mat.start());
        }
        if (!"".equals(replaceText)) {
            mat.appendTail(buffer);
            out.println("Buffer -> \n" + buffer);
        }

        out.println("Text -> \n" + text);

    }

    private static void regExp(double number, String shablon, String text, String replaceText, String rule) {
        Pattern pat;
        Matcher mat;
        StringBuffer buffer = new StringBuffer();

        out.println(number + " (" + shablon + ")-------------------------------------------------------");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(text);
        while (mat.find()) {
            if (!"".equals(replaceText)) {
                mat.replaceAll(replaceText);


            } else
                out.println("mat.group() -> " + mat.group() + "    " + mat.start());
        }
        out.println("Text -> " + text);

    }

    public static void replaceRegex() {
        StringBuffer buffer = new StringBuffer();

        Pattern regexp = Pattern.compile("<[a-z]+>");
        Matcher m = regexp.matcher("<a><b-><1><c><d/>");
        while (m.find())
            m.appendReplacement(buffer, "text");
        m.appendTail(buffer);

        System.out.println(buffer);
    }

    public static void appendReplacementExample() {
        Pattern pattern = Pattern.compile("a*b");
        Matcher matcher = pattern.matcher("aabtextaabtextabtextb the end");
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            System.out.println("buffer before="+buffer);
            matcher.appendReplacement(buffer, "-");
            // buffer = "-" -> "-text-" -> "-text-text-" -> "-text-text-text-"
            System.out.println("buffer after="+buffer);
        }
        matcher.appendTail(buffer);
// buffer = "-text-text-text- the end"
    }

}
