package String;

import java.util.Objects;
import java.util.Optional;

public class StringP {

    public static void main(String[] args) {
        // m1();

        String s1="1111111111";
//        System.out.println( "       check=   "+compare2( "Hello1", "Hello2"));
//        System.out.println( "       check=   "+compare2( null, "Hello2"));
//        System.out.println( "       check=   "+compare2( "Hello1", null));
//
//        System.out.println( "       check=   "+compare2( "1", "2"));
//        System.out.println( "       check=   "+compare2( "2", "1"));
//        System.out.println( "       check=   "+compare2( "1", "Hello2"));
     //   System.out.println( "       check=   "+compare2( "Hello1", "2"));
//        System.out.println( "       check=   "+compare2( "1", null));
//        System.out.println( "       check=   "+compare2( null, "2"));
//        System.out.println( "       check=   "+compare2( "1.5", "2.5"));

        System.out.println( "       check=   "+compare2( s1, s1));

     //   Long a = Long.valueOf(s1);

    }

    private static  int compare2( String leftAttribute, String rightAttribute) {
        long numberNodeLeft = 0;
        long numberNodeRight = 0;
        if (nonNumber(leftAttribute) && nonNumber(rightAttribute)) {
            System.out.println(leftAttribute+ "-------------" +  rightAttribute);
            return 0;
        } else {
            if (nonNumber(leftAttribute)) {
                System.out.println(leftAttribute+ "-------------" +  rightAttribute);
                return -1;
            }
            if (nonNumber(rightAttribute)) {
                System.out.println(leftAttribute+ "-------------" +  rightAttribute);
                return 1;
            }
        }
        numberNodeLeft = Long.valueOf(leftAttribute);
        numberNodeRight = Long.valueOf(rightAttribute);
        System.out.println(leftAttribute+ "-------------" +  rightAttribute);
        return (int) (numberNodeLeft - numberNodeRight);
    };





    private static void m1() {
        String s = " \"  ORCL12EE_PRIVATE_TEST_ORA_PG.168.15.19   2\"  2   ";
        System.out.println(stringWithPoint(s));

        s = " \"  ORCL12EE_PRIVATE_TEST_ORA_PG.168.15.19        ";
        System.out.println(stringWithPoint(s));

        s = "   ORCL12EE_PRIVATE_TEST_ORA_PG.168.15.19        ";
        System.out.println(stringWithPoint(s));

        s = "";
        System.out.println("----"+stringWithPoint(s));

        s = null;
        System.out.println("----"+stringWithPoint(s));


        System.out.println(deleteBraces("  (2341   ", "(", ")"));

        String sInt = s.valueOf(5);
    }

    public static String stringWithPoint(String attribute) {
        attribute = Optional.ofNullable(attribute).orElse("");
        attribute = attribute.trim();
        if(  !attribute.isEmpty() && attribute.contains(".")
            &&!(attribute.charAt(0)=='"' &&   attribute.charAt(attribute.length()-1)=='"') ) {
            attribute=doubleQuotes(attribute);
        }
        return attribute;
    }

    public static String doubleQuotes(String line) {
        return "1" + line + "1";
    }

    public static String unwrap(String str, String left, String right) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        StringBuilder rez = new StringBuilder();
        if (str.startsWith(left)) {
            rez.append(str.substring(left.length()));
        } else {
            rez.append(str);
        }
        if (str.endsWith(right)) {
            rez.delete(rez.lastIndexOf(right), rez.lastIndexOf(right) + right.length());
        }
        return rez.toString();
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static String deleteBraces(String text, String startsWith, String endsWith) {
        String tempText = Optional.ofNullable(text).orElse("").trim();
        if (tempText.length() > 2 && tempText.startsWith(startsWith) && tempText.endsWith(endsWith)) {
            return tempText.substring(1, tempText.length() - 1);
        }
        return text;
    }

    private static boolean nonNumber(String attributeValue) {
        boolean nonNumber = false;
        if (Objects.isNull(attributeValue) || attributeValue.isEmpty()) {
            nonNumber = true;
        } else {
            for (int i = 0; i < attributeValue.length(); i++) {
                if (!Character.isDigit(attributeValue.charAt(i))) {
                    nonNumber = true;
                }
            }
        }
        return nonNumber;
    }

}
