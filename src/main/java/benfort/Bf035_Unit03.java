package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf035_Unit03 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";
        Pattern pat;
        Matcher mat;


        shablon ="[ns]a.\\.xls";
        textFile="salesl.xls \n" +
                "ordersЗ.xls \n" +
                "sales2.xls \n" +
                "salesЗ.xls \n" +
                "apacl.xls \n" +
                "europe2 .xls \n" +
                "nal.xls \n" +
                "na2.xls \n" +
                "sal.xls";
        regExp(35,shablon,textFile);


        textFile="The phrase \"regular   expression\"    is often  abbreviated    as RegEx or regex   not REGEX";
        shablon ="[Rr]eg[Ee]x";
        regExp(37,shablon,textFile);

        textFile=   "sales1.xls \n" +
                "orders3.xls \n" +
                "sales2.xls \n" +
                "sa1es3.xls \n" +
                "apac1.xls \n" +
                "europe2.xls \n" +
                "sam.xls \n" +
                "na1.xls \n" +
                "na2.xls \n" +
                "sa1.xls \n" +
                "ca1.xls";
        shablon = shablon ="[ns]a[0123456789]\\.xls";
        regExp(38,shablon,textFile);

        shablon = shablon ="[ns]a[0-9]\\.xls";
        regExp(39,shablon,textFile);

        String textHTML=  "<BODY  ВGCOLOR=\"#336633\"  TEXT=\"#FFFFFF\" МARGINWIDТH=\"O\" МARGINНEIGHT=\"O\" " +
                " TOPМARGIN=\"O\" LEFТМARGIN=\"O\">";
        shablon = shablon ="#[0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa9A-Fa-f]";
        regExp(41,shablon,textHTML);

        shablon = shablon ="[ns]a[^0-9]\\.xls";
        regExp(42,shablon,textFile);

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