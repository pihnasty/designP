package tests.junitTest;

import org.junit.*;

import java.util.Iterator;
import java.util.TreeMap;


public class TreeMapIteratorTest {



    private static Iterator<String> iter;
    private static TreeMap<String, String> map;

    public TreeMapIteratorTest() {
        System.out.println("TreeMapIteratorTest");
    }

    @BeforeClass
    public static void setUpClass () {
        System.out.println("setUpClass");
    }

    @Before
    public void setUp () {
        map = new TreeMap<>();
        iter = map.keySet().iterator();
        System.out.println("setUp @Before");
    }

    @Test
    public void test0 () {
        System.out.println("@Test0");
    }

    @Test
    public void test1 () {
        System.out.println("@Test1");
    }

    @After
    public void tearQown () {
        System.out.println("@tearQown @After");
    }

    @AfterClass
    public static  void tearQownClass () {
        System.out.println("@tearQownClass");
    }

    @Test
    public void testCanUseNextWithoutHasNext () {
        if (iter.hasNext()) {
            throw new AssertionError();
        }


        System.out.println("@TestCanUseNextWithoutHasNext");
    }



}
