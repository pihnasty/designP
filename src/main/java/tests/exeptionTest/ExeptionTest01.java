package tests.exeptionTest;

/**
 * Created by Pihnastyi.O on 12/28/2016.
 */
public class ExeptionTest01 {
    public static void main(String[] args) {
        System.out.println(d());
    }

    private static int d() {
        try {
            System.out.println(0);
            //throw new NullPointerException();
          //  return 3;
            d();
        } finally {
            System.out.println(1);
       //     return 2;
            //     throw new ArithmeticException();
            d();
        }
        return 4;
    }
}
