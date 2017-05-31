package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf101_Unit08 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";
        Pattern pat;
        Matcher mat;


        shablon ="<[hH]1>.*</[hH]1>";
        textFile="<BODY> \n" +
                "<H1>Welcome  to my Homepage</H1> \n" +
                "Content  is divided  into  two sections:<BR> \n" +
                "Information    about  Macromedia   Co1dFusion. \n" +
                "<H2>ColdFusion</H2> \n" +
                "<H2>Wireless</H2> \n" +
                "Information    about Bluetooth,    802.11,  and more. \n" +
                "</ВОDУ> ";
        regExp(102.1,shablon,textFile);

        shablon ="<[hH][1-6]>.*?</[hH][1-6]>";
        textFile="<BODY> \n" +
                "<H1>Welcome  to my Homepage</H1> \n" +
                "Content  is divided  into  two sections:<BR> \n" +
                "Information    about  Macromedia   Co1dFusion. \n" +
                "<H2>ColdFusion</H2> \n" +
                "<H2>Wireless</H2> \n" +
                "Information    about Bluetooth,    802.11,  and more. \n" +
                "</ВОDУ> ";
        regExp(103.1,shablon,textFile);

        shablon ="<[hH][1-6]>.*?</[hH][1-6]>";
        textFile="<BODY> \n" +
                "<H1>Welcome  to my Homepage</H1> \n" +
                "Content  is divided  into  two sections:<BR> \n" +
                "Information    about  Macromedia   Co1dFusion. \n" +
                "<H2>ColdFusion</H2> \n" +
                "<H2>Wireless</H2> \n" +
                "Information    about Bluetooth,    802.11,  and more. \n" +
                "<H2>This  is not valid  НТМL</H3>" +
                "</ВОDУ> ";
        regExp(104.1,shablon,textFile);

        shablon ="[\\s]+(\\w+)[\\s]+\\1";
        textFile="This is  а Ьlock  of of  text,  s~veral  words here are are \n" +
                "repeated,   and and they should  not  Ье.";
        regExp(106.1,shablon,textFile);

    }

    private static void regExp(double number, String shablon,  String text) {
        Pattern pat;
        Matcher mat;

        out.println(number+" ("+shablon+")-------------------------------------------------------");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(text);
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());
    }
}