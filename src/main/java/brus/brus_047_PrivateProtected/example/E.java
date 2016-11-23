package brus.brus_047_PrivateProtected.example;

import brus.brus_047_PrivateProtected.privateP.A;
import brus.brus_047_PrivateProtected.protectedP.B;


public class E {

    static public void main (String [] args){
        E2 e2 = new E2();
        e2.getAfromE();

        E3 e3 = new E3();
        e3.getAfromE();


    }




}


class E1 extends A{
    private int e1;
//    public int getAfromE ()  { return getA();}
}

class E2 extends B {
    private int e1;
   protected int getAfromE ()  { return getB();}
}

class E3 extends E2 {
    private int e1;

}
