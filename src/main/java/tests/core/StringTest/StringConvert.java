package tests.core.StringTest;

/**
 * Created by Pihnastyi.O on 11/27/2017.
 */
public class StringConvert {
    public static void main(String[] args) {

        String dtAttr1 = "64000";
        String charType ="2";
        int x = (Integer.parseInt(dtAttr1) / Integer.parseInt(charType));
        dtAttr1 =  String.valueOf(x);

        System.out.println("dtAttr1="+dtAttr1);


    }
}
