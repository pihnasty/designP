package tests.core.bytes.test;

public class Bytes {
    public static void main(String[] args) {
        byte b;
        int i=257;
        b=(byte)i;
        System.out.println("b="+b);

        byte b1 = 20;
        byte b2 = 20;
        byte b3;
        b3= (byte) (b1*b2);

        float f1 = 10e37F;
        float f2 = 10e30F;
        float f3 = f1*f2;

        short s1=100;
        short s2=100;
        short s3= (short) (b1*b2);

    }

}
