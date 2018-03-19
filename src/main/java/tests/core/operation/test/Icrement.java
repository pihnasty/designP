package tests.core.operation.test;

public class Icrement {
    public static void main(String[] args) {
        int a1=1;
        int a2 = 2;
        int a3 = 3;
        int i4;
        i4 = (a1++ * a2++)*2 + a3++;
        System.out.println("i4="+i4);
    }
}
