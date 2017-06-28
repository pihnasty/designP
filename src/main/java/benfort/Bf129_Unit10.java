package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf129_Unit10 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";

        shablon ="\\(?\\d{3}\\)?-?\\d{3}-\\d{4}";
        textFile="123-456-7890 \n" +
                "(123)456-7890 \n" +
                "(123)-456-7890 \n" +
                "(123-456-7890 \n" +
                "1234567890 \n" +
                "123 456 7890 ";
        regExp(129.1,shablon,textFile);


        shablon ="\\(?\\d{3}[\\)-]?\\d{3}-\\d{4}";
        textFile="123-456-7890 \n" +
                "(123)456-7890 \n" +
                "(123)-456-7890 \n" +
                "(123-456-7890 \n" +
                "1234567890 \n" +
                "123 456 7890 ";
        regExp(130.1,shablon,textFile);


        shablon ="(<[Aa]\\s+[^>]+>\\s*)?<[ Ii][Мm][Gg]\\s+[^>]+>(?(1)\\s*</[Aa]>)";
        textFile="<!--Nav bar --> \n" +
                "<TD> \n" +
                "<А  HREF=\"/home\"><IMG    SRC=\"/images/home.gif\"></A> \n" +
                "<IMG SRC=\"/images/spacer.gif\"> \n" +
                "<А  HREF=\"/search\"><IMG     SRC=\"/images/search.gif\"></A> \n" +
                "<IMG SRC=\"/images/spacer     .gif\"> \n" +
                "<А  HREF=\"/help\"><IMG    SRC=\"/images/help.gif\"></A> \n" +
                "</TD>";
        regExp(131.1,shablon,textFile);

    }

    private static void regExp(double number, String shablon,  String text) {
        Pattern pat;
        Matcher mat;


        out.println(number+" ("+shablon+")-------------------------------------------------------");
        out.println(number+" text: \n\""+text+"\" \n");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(text);
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());
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
