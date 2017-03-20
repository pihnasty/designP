package tests;

/**
 * Created by pom on 05.03.2017.
 */
public class OperatorTest {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(9));
        // prints "11111111111111111111111111111111"
        System.out.println(Integer.toBinaryString(-9));
        System.out.println(Integer.toBinaryString(-8));
        // prints "11111111111111111111111111111111"


        String s1=Integer.toBinaryString(9 >>> 2);
        String s2=Integer.toBinaryString(9 >> 2);

        System.out.println(s1);
        System.out.println(s2);

        String d1=Integer.toBinaryString(-9 >>> 2);
        String d2=Integer.toBinaryString(-9 >> 2);

        System.out.println(d1);
        System.out.println(d2);

        System.out.println(Integer.getInteger(d1));
        System.out.println(Integer.getInteger(d2));


    }
}
