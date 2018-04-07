package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf117_Unit09 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";

        shablon ="<[Tt][Ii][Ti][Ll][Ee]>.*</[Tt][Ii][Ti][Ll][Ee]>";
        textFile="<HEAD> \n" +
                "<TITLE>Ben  Forta's   Homepage</TITLE> \n" +
                "</HEAD>";
        regExp(117.1,shablon,textFile);

        shablon =".+(?=:)";
        textFile="http://www.forta.com/ \n" +
                "https://mail.forta.com/ \n" +
                "ftp://ftp.forta.com/ ";
        regExp(119.1,shablon,textFile);

        shablon =".+(:)";
        textFile="http://www.forta.com/ \n" +
                "https://mail.forta.com/ \n" +
                "ftp://ftp.forta.com/ ";
        regExp(120.1,shablon,textFile);

        shablon =".+:";
        textFile="http://www.forta.com/ \n" +
                "https://mail.forta.com/ \n" +
                "ftp://ftp.forta.com/ ";
        regExp(120.2,shablon,textFile);



        shablon ="\\$[0-9.]+";
        textFile="АБС01:  $23.45 \n" +
                "HGG42: $5.31 \n" +
                "CFMX1: $899.00 \n" +
                "ХТС99:  $69.96 \n" +
                "Total  items  found:  ";
        regExp(122.1,shablon,textFile);

        shablon ="[0-9]+\\.";
        textFile="АБС01:  $23.45 \n" +
                "HGG42: $5.31 \n" +
                "CFMX1: $899.00 \n" +
                "ХТС99:  $69.96 \n" +
                "Total  items  found:  ";
        regExp(122.2,shablon,textFile);

        shablon ="[0-9.]";   // Это неправильная реализация шаблона. Правильная    shablon ="[0-9]+\\.";    122.2
        textFile="АБС01:  $23.45 \n" +
                "HGG42: $5.31 \n" +
                "CFMX1: $899.00 \n" +
                "ХТС99:  $69.96 \n" +
                "Total  items  found:  ";
        regExp(122.3,shablon,textFile);

        shablon ="(?<=\\$)[0-9.]+";
        textFile="АБС01:  $23.45 \n" +
                "HGG42: $5.31 \n" +
                "CFMX1: $899.00 \n" +
                "ХТС99:  $69.96 \n" +
                "Total  items  found:  ";
        regExp(123.1,shablon,textFile);

        shablon ="[0-9.]+(?=\\$)";
        textFile="АБС01:  $23.45$ \n" +
                "HGG42: $5.31 \n" +
                "CFMX1: $899.00$ \n" +
                "ХТС99:  $69.96 \n" +
                "Total  items  found:  ";
        regExp(123.1,shablon,textFile);



        shablon ="<[Tt][Ii][Ti][Ll][Ee]>.*</[Tt][Ii][Ti][Ll][Ee]>";
        textFile="<HEAD> \n" +
                "<TITLE>Ben  Forta's   Homepage</TITLE> \n" +
                "</HEAD>";
        regExp(124.1,shablon,textFile);




        shablon ="<[Tt][Ii][Ti][Ll][Ee]>.*(?=</[Tt][Ii][Ti][Ll][Ee]>)";
        textFile="<HEAD> \n" +
                "<TITLE>Ben  Forta's   Homepage</TITLE> \n" +
                "</HEAD>";
        regExp(124.2,shablon,textFile);


        shablon ="(?<=\\<[Tt][Ii][Ti][Ll][Ee]>).*(?=</[Tt][Ii][Ti][Ll][Ee]>)";
        textFile="<HEAD> \n" +
                "<TITLE>Ben  Forta's   Homepage</TITLE> \n" +
                "</HEAD>";
        regExp(124.3,shablon,textFile);



        shablon ="(?<=\\$)\\d+";
        textFile="I paid $30 for 100 apples,  50 oranges,   and 60 pears.  I \n" +
                 "saved $5 on this  order. ";
        regExp(126.1,shablon,textFile);


        shablon ="\\b(?<!\\$)\\d+\\b";
        textFile="I paid $30 for 100 apples,  50 oranges,   and 60 pears.  I \n" +
                "saved $5 on this  order. ";
        regExp(127.1,shablon,textFile);

        shablon ="(?<!\\$)\\d+";
        textFile="I paid $30 for 100 apples,  50 oranges,   and 60 pears.  I \n" +
                "saved $5 on this  order. ";
        regExp(128.1,shablon,textFile);



        shablon ="^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        textFile="pom7@bk.ru";
        regExp(10000000.0,shablon,textFile);

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
