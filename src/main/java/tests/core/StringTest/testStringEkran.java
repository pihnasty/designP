package tests.core.StringTest;

/**
 * Created by Pihnastyi.O on 10/31/2016.
 */
public class testStringEkran {
    static public void main (String [] arg){


        //teat01();
        teat02_quote();

    }

    private static void teat01() {
        int ch =   10;

        int chP = 92;

        char [] b = {(char)ch};

        char [] bP = {(char)chP};

        System.out.println("ch ="+(int)ch);

        String start = "A \"double \\| quotes A  ---" + (char)ch+"--- ";


//        char LF = (char) 0x0A;
//        char CR = (char) 0x0D;
//        char BS = (char) 0x5C;

        String LF = new String(new char[]{(char) 0x0A});
        String CR = new String(new char[]{(char) 0x0D});
        String backSlash = new String(new char[]{(char) 0x5C});
        String doubleQuotes = new String(new char[]{(char) 0x22});
        String pipe = new String(new char[]{(char) 0x7C});

        System.out.println("----------------------->"+pipe);   // =============================

        System.out.println(start);


        StringBuilder sb = new StringBuilder();

        sb.append(doubleQuotes).append(start
                .replace(backSlash, backSlash+backSlash)
                .replace(CR, backSlash+CR)
                .replace(LF, backSlash+LF)
                .replace(doubleQuotes, backSlash+doubleQuotes)
                .replace(pipe, backSlash+pipe)
        ).append(doubleQuotes);

        System.out.println(sb);
    }

    private static void teat02_quote() {

        String s = "\"17\"";

        String s2=s.substring(1,s.length()-1);


        System.out.println(s);



    }
}
