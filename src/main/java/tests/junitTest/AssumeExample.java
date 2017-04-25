package tests.junitTest;

import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;


/**
 * Created by pom on 11.04.2017.
 */
public class AssumeExample {

    @Ignore  // игнорированиен теста
    @Test
    public void testFailButIgnore() {
        System.out.println("This test is Fail but ignored="+new Date().getDay());
        Assume.assumeTrue(new Date().getDay()==2);
        System.out.println("This test is Fail but ignored="+new Date().getDay());
      //  throw new RuntimeException("What is it?");
    }


    @Test
    public void testFailButIgnore2() {
        System.out.println("This test is Fail but ignored="+new Date().getDay());
        Assume.assumeTrue(new Date().getDay()==7);
        System.out.println("This test is Fail but ignored="+new Date().getDay());
        //  throw new RuntimeException("What is it?");
    }

}
