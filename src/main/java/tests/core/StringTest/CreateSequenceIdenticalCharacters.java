package tests.core.StringTest;

public class CreateSequenceIdenticalCharacters {

    public static void main(String[] args) {
        String s = new String(new char[5]);
        System.out.println("-"+s+"-");
        StringBuilder sb = new StringBuilder("                                                          ");
        System.out.println("-"+sb.substring(0,1)+"-");


        char [] a =  new char [20];

        for (int i=0; i<20;i++) a[i]=' ';

        System.out.println("-"+a[1]+"-");

        String str = new String(a);

        System.out.println("-"+str+"-");


    }

}
