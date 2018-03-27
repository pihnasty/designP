package tests.core.operation.test;

public class Sdvig {
    public static void main(String[] args) {
        byte b1 = 64;
        byte b2 = 0;
        b2 = (byte) (b1 << 2);
        System.out.println("b1="+b1+" "+ "b2="+b2);

        byte a=64, b;
        int i;
        i=a<<2;
        b=(byte) (a<<2);
        System.out.println("a="+a+" "+"i="+i+" b="+b);

        System.out.println(Integer.toBinaryString(i));

        int j;
        int num=0xFFFFFFE;

        for (j=0;j<4;j++) {
            num = num << 1;
            System.out.println(num+"  "+Integer.toBinaryString(num));
        }

        System.out.println(Integer.toBinaryString(-10));

        int x1 = -1;
        System.out.println("-1=>"+Integer.toBinaryString(x1));

    }
}

//         536870908    11111111111111111111111111100
//        1073741816   111111111111111111111111111000
//        2147483632  1111111111111111111111111110000
//              -32  1111 1111 1111 1111 1111 1111 1110 0000
//         11111111111111111111111111110110
//     -1=>11111111111111111111111111111111