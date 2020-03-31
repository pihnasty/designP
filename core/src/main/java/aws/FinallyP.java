package aws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FinallyP {
    public static void main(String[] args) {
        "Меня зовут ".concat("Олег");
        char[] data = new char[] {'0','1','2','3','4','5','6','7'};
        String.copyValueOf(data);
        String.copyValueOf(data,1,5);   //  12345

        String s1 = new String("s1");

        String s2 = new String("s1");

        "s1".compareTo(s2);

        "Oleh".startsWith("O");

        "Oleh".indexOf("eh");

        "Hello".substring(1,3);

        StringBuffer stringBuffer = new StringBuffer("Hello");
        stringBuffer.delete(3,4);
        stringBuffer.insert(2,"12");


        System.out.println(stringBuffer);

        String s4 = stringBuffer.substring(2,3);


        System.out.println(s4);

        ArrayList list = new ArrayList<>( Arrays.asList(1,2,40,56,71));

        Collections.binarySearch(list,50);
        list.subList(2,3);




    }
}
