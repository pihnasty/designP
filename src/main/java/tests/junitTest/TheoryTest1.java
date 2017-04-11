package tests.junitTest;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class TheoryTest1 {

    @DataPoint
    public static String a = "a";

    @DataPoint
    public static String b = "b";

    @Theory
    public void test(String str1, String str2) {
        System.out.println(str1 + " and " + str2);

    }
}
