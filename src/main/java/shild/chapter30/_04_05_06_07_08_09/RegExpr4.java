package shild.chapter30._04_05_06_07_08_09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpr4 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("W+");
        Matcher mat = pat.matcher("W WW WWW");

        while(mat.find())
            System.out.println("Match: " + mat.group());
    }
}

/*
Match: W
Match: WW
Match: WWW
* */


// Use wildcard and quantifier.
class RegExpr5 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("e.t");
        Matcher mat = pat.matcher("extend cup end table");

        pat = Pattern.compile("e.t");
        mat = pat.matcher("extend cup end table");

        while(mat.find())
            System.out.println("Match: " + mat.group());

        pat = Pattern.compile("e.+?t");
        mat = pat.matcher("extend cup end table");

        while(mat.find())
            System.out.println("Match: " + mat.group());

    }
}

/*
        Match: ext
        Match: extend cup end t
*/


// Use the ? quantifier.

class RegExpr6 {
    public static void main(String args[]) {
        // Use reluctant matching behavoir.
        Pattern pat = Pattern.compile("e.+?d");
        Matcher mat = pat.matcher("extend cup end table");

        while(mat.find())
            System.out.println("Match: " + mat.group());
    }
}

// Use a character class.

class RegExpr7 {
    public static void main(String args[]) {
        // Match lowercase words.
        Pattern pat = Pattern.compile("[a-z]+");
        // Pattern pat = Pattern.compile("[a-z,/' '/]+");
        Matcher mat = pat.matcher("this is a test.");

        while(mat.find())
            System.out.println("Match: " + mat.group());
    }
}

// Use replaceAll().

class RegExpr8 {
    public static void main(String args[]) {
        String str = "Jon Jonathan Frank Ken Todd";

        Pattern pat = Pattern.compile("Jon.*?");
        Matcher mat = pat.matcher(str);

        System.out.println("Original sequence: " + str);

        str = mat.replaceAll("Eric ");

        System.out.println("Modified sequence: " + str);

    }
}

// Use split().

class RegExpr9 {
    public static void main(String args[]) {

        // Match lowercase words.
        Pattern pat = Pattern.compile("[ ,.!]");

        String strs[] = pat.split("one two,alpha9 12. !done.");

        for(int i=0; i < strs.length; i++)
            System.out.println("Next token: " + strs[i]);

    }
}

class RegExprPOM01 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("^e.*?");
        Matcher mat = pat.matcher("extend cup end table extd");



        while(mat.find())
            System.out.println("Match: " + mat.group());


    }
}