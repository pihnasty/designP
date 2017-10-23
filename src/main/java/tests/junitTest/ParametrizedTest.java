package tests.junitTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.*;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Parameterized.class)
public class ParametrizedTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return asList (new Object[][] {{-3,3},{3,3}});
    }

    @Parameterized.Parameter(0)
    public int input;

    @Parameterized.Parameter(1)
    public int expectedResult;

    @Test
    public void test() {
  //   assertThat(abs(input), is(expectedResult));
    }

}

class Abs {
    public static int abs(int a){
        return Math.abs(a);
    }

}
