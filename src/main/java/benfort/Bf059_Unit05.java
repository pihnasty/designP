package benfort;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf059_Unit05 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";
        Pattern pat;
        Matcher mat;


        shablon ="\\w+@\\w+\\.\\w+";
        textFile="Send personal   email to  Ьen@forta.com. For questions  aЬout \n" +
                "а Ьооk  use support@forta.com.       Feel free to send unsolicited \n" +
                "email to spam@forta.com    (wouldn't   it  Ье  nice if it were \n" +
                "that  simple , huh?)";
        regExp(61,shablon,textFile);

        shablon ="\\w+@\\w+\\.\\w+";
        textFile="Send personal   email to ben@forta.com    or \n" +
                "ben.forta@forta.com. For questions  aЬout а  book use \n" +
                "support@forta.com. If your message  is urgent  try \n" +
                "ben@urgent.forta.com      . Feel free to send unsolicited     email \n" +
                "to spam@forta.com    (wouldn't   it  Ье  nice if it were that \n" +
                "simple,  huh?";
        regExp(62,shablon,textFile);

        shablon ="[\\w.]+@[\\w.]+\\.\\w+";
        textFile="Send personal   email to ben@forta.com    or \n" +
                "ben.forta@forta.com . For questions  aЬout а  book use \n" +
                "support@forta.com. If your message  is urgent  try \n" +
                "ben@urgent.forta.com      . Feel free to send unsolicited     email \n" +
                "to spam@forta.com    (wouldn't   it  Ье  nice if it were that \n" +
                "simple,  huh?";
        regExp(63,shablon,textFile);

        shablon ="[\\w.]+@[\\w.]+";
        textFile="Send personal   email to ben@forta.com    or \n" +
                "ben.forta@forta.com. For questions  aЬout а  book use \n" +
                "support@forta.com. If your message  is urgent  try \n" +
                "ben@urgent.forta.com. Feel free to send unsolicited     email \n" +
                "to spam@forta.com    (wouldn't   it  Ье  nice if it were that \n" +
                "simple,  huh?";
        regExp(63,shablon,textFile);

        shablon ="[\\w.]+@[\\w.]+\\.\\w+";
        textFile="Hello .ben@forta.com is my email addre";
        regExp(65,shablon,textFile);

        shablon ="\\w+[\\w.]*@[\\w.]+\\.\\w+";
        textFile="Hello .ben@forta.com is my email addre";
        regExp(66,shablon,textFile);

        shablon ="http://[\\w./]+";
        textFile="The URL is http://www.forta.com/,  to connect  securely   use \n" +
                "https://www.forta.com/         instead";
        regExp(67,shablon,textFile);

        shablon ="https?://[\\w./]+";
        regExp(68.1,shablon,textFile);

        shablon ="[\r]?\n[\r]?\n";
    //    shablon ="\r\n\r\n";
        textFile="\"10l\",\"Ben\",\"Forta• \n" +
                "\"102\",\"Jim\",\"James•  \r\n\r\n" +
                "\"103\",\"Roberta\",\"Robertson• \n\n" +
                "\"104\",\"Bob\",\"Bobson";
        regExp(68.2,shablon,textFile);

        shablon ="#[\\p{XDigit}]{6}";
        textFile="<BODY  ВGCOLOR=\"#336633\"  TEXT=\"#FFFFFF\" МARGINWIDТH=\"O\" МARGINНEIGHT=\"O\" " +
                " TOPМARGIN=\"O\" LEFТМARGIN=\"O\">";
        regExp(71.1,shablon,textFile);

        shablon ="\\d{1,2}[-\\/]\\d{1,2}[-\\/]\\d{2,4}";
        textFile="4/8/03 \n" +
                "10-6-2004 \n" +
                "2/2/2 \n" +
                "01-01-01";
        regExp(71.2,shablon,textFile);

        shablon ="\\d+:\\$\\d{3,}\\.\\d{2}";
        textFile="1001:$496.80 \n" +
                "1002:$1290.69 \n" +
                "1003:$26.43 \n" +
                "1004:$613.42 \n" +
                "1005:$7.61 \n" +
                "1006:$414.90 \n" +
                "1007:$25.00";
        regExp(73.1,shablon,textFile);

        shablon ="<[ВЬ]>.*</[ВЬ]> ";
        textFile="This offer  is not  availaЬle  to customers   living  in \n" +
                "<В>АК</В>  and <В>АК2</В>";
        regExp(75.1,shablon,textFile);

        shablon ="<[ВЬ]>.*</[ВЬ]>";
        textFile="This offer  is not  availaЬle  to customers   living  in \n" +
                "<В>АK</В> and  <В>А2222322323</В>";
        regExp(75.1,shablon,textFile);

        shablon ="<[ВЬ]>.*?</[ВЬ]>";
        textFile="This offer  is not  availaЬle  to customers   living  in \n" +
                "<В>АK</В> and  <В>А</В> ";
        regExp(76.1,shablon,textFile);



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