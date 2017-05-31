package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;
import static java.lang.System.setOut;

class Bf078_Unit06 {
    public static void main(String args[]) {
        String shablon="";
        String textFile="";
        Pattern pat;
        Matcher mat;


        shablon ="cat";
        textFile="The cat scattered    his food all over the room. ";
        regExp(78,shablon,textFile);

        shablon ="\\bcat\\b";
        regExp(80,shablon,textFile);

        shablon ="\\bcap";
        textFile="The captain   wore his cap  and  capе  proudly  as he sat \n" +
                "listening    to the recap  of how his crew saved the men from \n" +
                "а  capsized   vessel. ";
        regExp(81,shablon,textFile);

        shablon ="cap\\b";
        regExp(82.1,shablon,textFile);
        System.out.println("shablon.length()="+shablon.length());

        shablon ="\\B-\\B";
        textFile="Please  enter  the nine-digit    id as it appears  on your color -\n" +
                "coded pass-key. ";
        regExp(83.1,shablon,textFile);

        shablon ="\\<cap";
        textFile="The captain   wore his cap  and  capе  proudly  as he sat \n" +
                "listening    to the recap  of how his crew saved the men from \n" +
                "а  capsized   vessel. ";
        regExp(83.2,shablon,textFile);

        shablon ="<\\?xml.*\\?>";
        textFile="<?xml version=\"l.O\"  encoding=\"UТF-8\"  ?> \n" +
                "<wsdl  :.definitions     targetNarnespace=\"http:         /tips.   cf\" \n" +
                "xmlns:impl=\"http://tips         .cf• xmlns:intf=\"http://tips.cf\" \n" +
                "xmlns:apachesoap=\"http://xml.apache.org/xml-soap ";
        regExp(84,shablon,textFile);

        shablon ="<\\?xml.*\\?>";
        textFile="This is bad, real  bad! \n" +
                "<?xml version=\"l.O\"  encoding=\"UТF-8\"  ?> \n" +
                "<wsdl  :.definitions     targetNarnespace=\"http:         /tips.   cf\" \n" +
                "xmlns:impl=\"http://tips         .cf• xmlns:intf=\"http://tips.cf\" \n" +
                "xmlns:apachesoap=\"http://xml.apache.org/xml-soap ";
        regExp(85.1,shablon,textFile);

        shablon ="^\\s*<\\?xml.*\\?>";
        textFile=
                "<?xml version=\"l.O\"  encoding=\"UТF-8\"  ?> \n" +
                "<wsdl  :.definitions     targetNarnespace=\"http:         /tips.   cf\" \n" +
                "xmlns:impl=\"http://tips         .cf• xmlns:intf=\"http://tips.cf\" \n" +
                "xmlns:apachesoap=\"http://xml.apache.org/xml-soap ";
        regExp(85.2,shablon,textFile);

        shablon ="^.*\\s";
        textFile=
                "<?xml version=\"l.O\"  encoding=\"UТF-8\"  ?> \n" +
                        "<wsdl  :.definitions     targetNarnespace=\"http:         /tips.   cf\" \n" +
                        "xmlns:impl=\"http://tips         .cf• xmlns:intf=\"http://tips.cf\" \n" +
                        "xmlns:apachesoap=\"http://xml.apache.org/xml-soap ";
        regExp(86.1,shablon,textFile);

        shablon ="(?m)^\\s*//.*$";
        textFile=
                "<SCRIPТ> \n" +
                        "function   doSpellCheck(form,      field) \n" +
                        "// Make sure not empty \n" +
                        "if (field .value  == '')  { \n" +
                        "return  false; \n" +
                        "} \n" +
                        "// Init \n" +
                        "var windoWName='spellWindow'; \n" +
                        "var \n" +
                        "spellCheckURL='spell.ctm?formname=comment&fieldname='+field . \n" +
                        "nате; \n" +
                        "// Done \n" +
                        "return  false; \n" +
                        "</SCRIPT> ";
        regExp(87.1,shablon,textFile);

        shablon ="^\\s*//.*$";
        textFile=
                "// <SCRIPТ> \n" +
                        "function   doSpellCheck(form,      field) \n" +
                        "// Make sure not empty \n" +
                        "if (field .value  == '')  { \n" +
                        "return  false; \n" +
                        "} \n" +
                        "// Init \n" +
                        "var windoWName='spellWindow'; \n" +
                        "var \n" +
                        "spellCheckURL='spell.ctm?formname=comment&fieldname='+field . \n" +
                        "nате; \n" +
                        "// Done \n" +
                        "return  false; \n" +
                        "</SCRIPT> ";
        regExp(88.1,shablon,textFile);


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