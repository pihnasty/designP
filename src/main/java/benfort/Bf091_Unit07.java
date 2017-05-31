package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf091_Unit07 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";
        Pattern pat;
        Matcher mat;


        shablon ="&nbsp;{2,}";
        textFile="Hello,  my  nате  is Ben&nbsp;Forta,     and I am the author  of \n" +
                "Ьooks  on SQL, ColdFusion,    WAP, Windows&nbsp;&nbsp;2000,       and \n" +
                "other  subjects  . ";
        regExp(91.1,shablon,textFile);

        shablon ="(&nbsp;){2,}";
        textFile="Hello,  my  nате  is Ben&nbsp;Forta,     and I am the author  of \n" +
                "Ьooks  on SQL, ColdFusion,    WAP, Windows&nbsp;&nbsp;2000,       and \n" +
                "other  subjects. ";
        regExp(92.1,shablon,textFile);

        shablon ="\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        textFile="Pinging  hog.forta.com     [12.159.46.200]  with 32 bytes  of data: ";
        regExp(93.1,shablon,textFile);

        shablon ="(\\d{1,3}\\.){3}\\d{1,3}";
        textFile="Pinging  hog.forta.com     [12.159.46.200]  with 32 bytes  of data: ";
        regExp(94.1,shablon,textFile);

        shablon ="19|20\\d{2}";
        textFile="ID: 042 \n" +
                "SEX:  М \n" +
                "DOB: 1967-08-17 \n" +
                "Status : Active";
        regExp(95.1,shablon,textFile);

        shablon ="(19|20)\\d{2}";
        regExp(95.2,shablon,textFile);

        shablon ="(19|20|21)\\d{2}";
        textFile="ID: 042 \n" +
                "SEX:  М \n" +
                "DOB: 1967-08-17 \n" +
                "DOB: 2067-08-17 \n" +
                "DOB: 2167-08-17 \n" +
                "Status : Active";
        regExp(97.1,shablon,textFile);

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