package tests;

import javax.servlet.ServletRequest;
import java.util.Date;

/**
 * Created by pom on 04.03.2017.
 */
public class CurrentTimeTest {
    public static void main(String[] args) {
        System.out.println(new Date(System.currentTimeMillis()));
    }

}
