package tests.namespace;

/**
 * Created by Pihnastyi.O on 7/31/2017.
 */
public class A1 {
    private int n;

    A1 a1;


    public A1(int n) {
        this.n=n;
    }

    void setA1 (A1 a1) {
        a1.n = 2;

        A1 a11 = new A1(1);

        a11.n = 3;
    }

 public static void showN() {
     System.out.println("n=");
 }

    public static void main(String[] args) {
        A1 b1 = new A1(5);

        b1.showN();

    }



}
