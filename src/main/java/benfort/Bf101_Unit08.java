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
        textFile="This is  а Ьlock  of of text,  s~veral  words here are are \n" +
                "repeated,   and and they should  not  Ье.";
        regExp(106.1,shablon,textFile);

        shablon =//"<[hH]([1-6])>.*?</[hН]\\1>";
                 "<[hH]([1-6])>.*?</[hH]\\1>";
        textFile="<BODY> \n" +
                "<h1>Hello!</h1> \n" +
                "<H1> Welcorne   to rny Hornepage</H1>\n" +
                "Content  is divided  into  two sections  :<BR> \n" +
                "<H2>Co1dFusion</H2> \n" +
                "Information  aЬout  Macrornedia   Co1dFusion. \n" +
                "<H2>Wireless</H2> \n" +
                "Information  aЬout  Bluetooth,    802.11,  and rnore. \n" +
                "<H2>This  is not valid  НТМL</НЗ> \n" +
                "</BODY> \n" +
                "(<BODY> \n" +
                "<Hl>  Добро  пожаловать  на  мою  домашнюю  страни\u00AD\n" +
                "цу</Нl> \n" +
                "Содержание разделено на два раздела:  <BR> \n" +
                "<Н2>  ColdFusion  </Н2> \n" +
                "Информация о  Macromedia   ColdFusion  . \n" +
                "<Н2> Радио </Н2> \n" +
                "Информация о  Bluetooth,    802.11  и выше. 108  Урока \n" +
                "<Н2>  Это- неправильный  (недопустимый)  НТМL-тег \n" +
                "</НЗ> \n" +
                "</BODY>) ";
        regExp(107.1,shablon,textFile);

        shablon ="<[hH]([1-6])>.*?</[hH]\\1>";
        textFile="<H1>aacvfvfvf    fff   ffg  </H1>";
        regExp(107.2,shablon,textFile);
//        107.2 (<[hH]([1-6])>.*?</[hH]\1>)-------------------------------------------------------
//                Result -> <H1>aacvfvfvf    fff   ffg  </H1>    0

        shablon ="<([hH])([1-6])>.*?</\\1\\2>";
        textFile="<H1>aacvfvfvf    fff   ffg  </H1>";
        regExp(107.3,shablon,textFile);

        shablon ="<([hH])([1-6])>.*?</\\1\\2>\\1";
        textFile="<H1>aacvfvfvf    fff   ffg  </H1>H";
        regExp(107.4,shablon,textFile);

        shablon ="\\w+[\\w\\.]*@[\\w\\.]+\\.\\w+";
        textFile="Hello ben@forta.com is my email addre";
        regExp(111.1,shablon,textFile);

        shablon ="\\w+[\\w\\.]*@[\\w\\.]+\\.\\w+";
        textFile="Hello ben@forta.com is my email addre";
        regExp(111.1,shablon,textFile);

        shablon ="\\w+[\\w\\.]*@[\\w\\.]+\\.\\w+";
        textFile="Hello ben@forta.com is my email addre";
        regExp(111.2,shablon,textFile);

        shablon ="(\\w+[\\w\\.]*@[\\w\\.]+\\.\\w+)";
        textFile="Hello ben@forta.com is my email addre  ben@forta.com ";
        regExp(111.3,shablon,textFile,"<A HREF='mailto:$1'>$1</A>"); //

        shablon ="(M*M)";
        textFile="MaMBMaM";
        regExp(111.4,shablon,textFile,"$1  $1");

        shablon ="(M.M)";
        textFile="MaMBMaM";
        regExp(111.5,shablon,textFile,"---------$1------------"); //

        shablon ="(\\d{3})(-)(\\d{3})(-)(\\d{4})";
        textFile="313-555-1234 \n" +
                "248-555-9999 \n" +
                "810-555-9000";
        regExp(113.1,shablon,textFile,"($1) $3-$5"); //

        shablon ="(<[Hh]1>)(.*?)(</[Hh]1>)";
        textFile="<BODY> \n" +
                "<H1>Welcome  to my Homepage</H1> \n" +
                "Content  is divided  into  two sections:<BR> \n" +
                "<H2>ColdFusion</H2> \n" +
                "Information  aЬout  Macromedia   ColdFusion. \n" +
                "<H2>Wireless</H2> \n" +
                "Information  aЬout  Bluetooth,    802.11,  and more. \n" +
                "<H2>This is not valid  НТМL";
        regExp(114.1,shablon,textFile,"$1\\p{Uower}$2\\E$1"); //


    }

    private static void regExp(double number, String shablon,  String text) {
        Pattern pat;
        Matcher mat;

        out.println(number+" ("+shablon+")-------------------------------------------------------");
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