package benfort;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf045_Unit04 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";
        Pattern pat;
        Matcher mat;


        shablon ="myArray[O]";
        textFile="var rnyArray new Array" +
                " if (myArray[O] 01 {";
        regExp(45,shablon,textFile);

        shablon ="myArray[O]";
        textFile="var rnyArray new Array" +
                " if (myArrayO 01 {";
        regExp(45,shablon,textFile);

        shablon ="myArray\\[O\\]";
        textFile="var rnyArray new Array" +
                " if (myArray[O] 01 {";
        regExp(46,shablon,textFile);

        shablon ="\\\\";
        textFile="\\home\\ben\\sales\\";
        Scanner in = new Scanner(System.in);
        // String s = in.next();
        regExp(48,shablon,textFile);

        shablon ="/";
        textFile="/home/ben/sales/";
        regExp(48,shablon,textFile);

        shablon ="\r\n\r\n";
        textFile="\"10l\",\"Ben•,     \"Forta\" \n" +
                "\"103\",\"RoЬerta\",\"RoЬertson\" \n" +
                "\"104\",\"Bob\",\"";
        regExp(49,shablon,textFile);

        shablon ="myArray\\[\\d\\]";
        textFile="var rnyArray new Array" +
                " if (myArray[0] 01   myArray[10] {";
        regExp(51,shablon,textFile);

        shablon ="myArray\\[\\d\\d\\]";
        regExp(51,shablon,textFile);

        shablon ="\\w\\d\\w\\d\\w\\d";
        textFile="11213" +
                "A1C2E3 " +
                "48075 " +
                "48237 " +
                "МlB4F2 " +
                "90046 " +
                "Н1Н2Н2 ";
        regExp(52,shablon,textFile);

        shablon ="\\w\\d\\w\\d\\w\\d";
        textFile="A1B2E3"+
                "A1C2E3";
        regExp(52,shablon,textFile);

        shablon ="\\x0A";
        textFile="a \n b \\x0A";
        regExp(55,shablon,textFile);

        shablon ="\\x0A";
        textFile="b \\x0A";
        regExp(55,shablon,textFile);

        shablon ="#[\\p{XDigit}][\\p{XDigit}][\\p{XDigit}][\\p{XDigit}][\\p{XDigit}][\\p{XDigit}]";
        textFile="<BODY  ВGCOLOR=\"#336633\"  TEXT=\"#FFFFFF\" МARGINWIDТH=\"O\" МARGINНEIGHT=\"O\" " +
                " TOPМARGIN=\"O\" LEFТМARGIN=\"O\">";
        regExp(56,shablon,textFile);

    }

    private static void regExp(int number, String shablon,  String text) {
        Pattern pat;
        Matcher mat;

        out.println(number+" ("+shablon+")-------------------------------------------------------");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(text);
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());
    }
}