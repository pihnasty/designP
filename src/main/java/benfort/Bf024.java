package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

class Bf024 {
    public static void main(String args[]) {
        out.println("25 -------------------------------------------------------");
        Pattern pat = Pattern.compile("Ben");
        Matcher mat = pat.matcher("Hello,  my name is Ben. Please  \nmy website  at http://www  .forta.");
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());


        out.println("26 -------------------------------------------------------");
        pat = Pattern.compile("my");
        mat = pat.matcher("Hello,  my name is Ben. Please  \nmy website  at http://www  .forta.");
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());

        out.println("27 -------------------------------------------------------");
        pat = Pattern.compile("sales.");
        mat = pat.matcher("salesl.xls \n" +
                "ordersЗ.xls \n" +
                "sales2.xls \n" +
                "salesЗ. xls \n" +
                "apacl.xls \n" +
                "europe2.xls \n" +
                "nal.xl");
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());


        String shablon =".a.";
        out.println("30 ("+shablon+")-------------------------------------------------------");
        pat = Pattern.compile(shablon);
        mat = pat.matcher("salesl.xls \n" +
                "ordersЗ.xls \n" +
                "sales2.xls \n" +
                "salesЗ. xls \n" +
                "apacl.xls \n" +
                "europe2.xls \n" +
                "nal.xl");
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());

        shablon =".a..";
        out.println("30Б ("+shablon+")-------------------------------------------------------");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(
         "salesl .xls \n" +
                "ordersЗ . xls \n" +
                "sales2.xls \n" +
                "salesЗ . xls \n" +
                "apacl.xls \n" +
                "europe2 .xls \n" +
                "nal.xls \n" +
                "na2.xls \n" +
                "sal.xl");
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());

        shablon =".a.\\.xls";
        out.println("30C ("+shablon+")-------------------------------------------------------");
        pat = Pattern.compile(shablon);
        mat = pat.matcher(
                "salesl.xls \n" +
                        "ordersЗ.xls \n" +
                        "sales2.xls \n" +
                        "salesЗ.xls \n" +
                        "apacl.xls \n" +
                        "europe2 .xls \n" +
                        "nal.xls \n" +
                        "na2.xls \n" +
                        "sal.xls");
        while(mat.find()) out.println("Result -> " + mat.group() + "    "+mat.start());





    }
}