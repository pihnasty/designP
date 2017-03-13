package tests;

/**
 * Created by Pihnastyi.O on 2/1/2017.
 */
public class RekurciaTest {
    static public void main (String [] args) throws InterruptedException {

        iteration (35, 2700);
        System.out.println("-----------------------");
   //     iteration2 (35, 2700);
            String s = Long.toBinaryString(1000000000);
        String s1 = Integer.toBinaryString(1000000000);
        System.out.println("---------------->"+s);

        System.out.println("---------------->"+Integer.parseInt(s));

        int i =  Integer.parseInt(s,2);

        System.out.println("---------------->"+i);

        long x=29;
        System.out.printf(Long.toString(x,2));

        System.out.printf(String.valueOf(Long.getLong(s,2)));

    }
    static public double newton (double x, double a) throws InterruptedException {
        System.out.println("x="+x);
        return 2.0/3.0*x+a/(3*x*x);
    }
    static public double newton2 (double x, double a) throws InterruptedException {
        System.out.println("x="+x);
        return 1.0/2.0*x+a/(3*x*x);
    }
    static public double iteration (double x, double a) throws InterruptedException {
        double xK = x;
        double xKp =  newton (x,a);

        while ( Math.abs(xK-xKp)>0.000000000000001 ) {
            xK=xKp;
            xKp = newton (xK,a);
        }
        return xKp;
    }
    static public double iteration2 (double x, double a) throws InterruptedException {
        double xK = x;
        double xKp =  newton2 (x,a);

        while ( Math.abs(xK-xKp)>0.0000000000000000000001 ) {
            xK=xKp;
            xKp = newton2 (xK,a);
        }
        return xKp;
    }

}
