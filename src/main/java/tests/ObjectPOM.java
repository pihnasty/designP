package tests;

/**
 * Created by Pihnastyi.O on 12/12/2016.
 */
public class ObjectPOM {
    static public void main (String [] args ) {
        //_isEmpty();
       // _isNullTest();
        //_waitTest2();
        _waitTest3();
    }

    private static void _isEmpty() {
        String s="";
        if (s.isEmpty() ) System.out.println("if (s.isEmpty() ) ");
        if ("".equals(s) ) System.out.println("if (\"\".equals(s)");
        if ("".equals(s) == s.isEmpty() ) System.out.println("if (\"\".equals(s) == s.isEmpty() )");
    }

    private static void _isNullAdd(Object o) {
        System.out.println("o-"+o.toString());
        o=null;
        System.out.println("o-"+o);
    }

    private static void _isNullTest() {
        Object o1 = new Object();
        System.out.println("o1-"+o1.toString());
        _isNullAdd(o1);
        System.out.println("o1-"+o1.toString());
    }

    private static void _waitTest() {
        Object o1 = new Object();
        System.out.println("o1-"+o1.toString());
        try {
            o1.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("o1-"+o1.toString());
    }

    private static void _waitTest2() {
        Object o1 = new Object();
        System.out.println("o1-"+o1.toString());

            synchronized (o1) {
                try {
                    o1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        System.out.println("o1-"+o1.toString());
    }
    private static void _waitTest3() {
        Object o1 = new Object();
        System.out.println("o1-"+o1.toString());

        synchronized (o1) {
            try {
                o1.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("o1-"+o1.toString());
    }

}
