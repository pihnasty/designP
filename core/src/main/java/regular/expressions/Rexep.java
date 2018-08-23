package regular.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rexep {

    public static final String TEXT = "012 0";

    public static void main(String[] args) {

  //      System.out.println();
   //     test(TEXT);

   //     containNumbersOnly("12,5,5");

        System.out.println(isNumber("0"));

    }

    public static boolean test(String testString) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(testString);
        if(m.find()){
            System.out.println(m.group());
        }
        return m.matches();
    }

    public static  boolean containNumbersOnly(String source){
        boolean result = false;
        Pattern // pattern = Pattern.compile("[0-9]+.[0-9]+"); //correct pattern for both float and integer.
        pattern = Pattern.compile("\\d+.\\d+"); //correct pattern for both float and integer.

        result = pattern.matcher(source).matches();
        if(result){
            System.out.println("\"" + source + "\""  + " is a number");
        }else
            System.out.println("\"" + source + "\""  + " is a String");
        return result;
    }

    public static boolean isNumber (String text) {
        return  Pattern.compile("^[-+]?\\d+(\\.\\d+)?$").matcher(text).matches();
    }
}