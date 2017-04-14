package g.u168;



import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by pom on 06.04.2017.
 */
public class TreeMapTest {
    @Test
    public void testEmpty() {
        TreeMap<String,String> map = new TreeMap<>();
        if (map.size() !=0) {
            throw new AssertionError();
        }
    }

    @Test
    public void testOne() {
        TreeMap<String,String> map = new TreeMap<>();
        map.put("A","A");
        if (map.size() !=1) {
            throw new AssertionError();
        }
    }

    @Test
    public void testOne2() {
        TreeMap<String,String> map = new TreeMap<>();
        map.put("A","A");
        List<String> stringList = new ArrayList();
        Assertions.assertThat(stringList).hasSize(4);
    }

    @Test ( expectedExceptions = IllegalArgumentException.class , timeOut = 10)
    public void testOne3() {
        TreeMap<String,String> map = new TreeMap<>();
        map.put("A","A");
        List<String> stringList = new ArrayList();

        throw new IllegalArgumentException();


    }
}
