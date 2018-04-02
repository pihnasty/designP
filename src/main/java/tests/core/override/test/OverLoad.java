package tests.core.override.test;

public class OverLoad {
    public static void main(String[] args) {
        int i = 88;
        test (i);
    }

    static void test (double d) {
        System.out.println("d2="+d);
    }

}


