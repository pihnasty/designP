package brus.brus_047_PrivateProtected;

/**
 * Created by Pihnastyi.O on 11/14/2016.
 */
public class PrivateProtectedExample {


     static public void main( String [] args){
         B b = new B();
         b.getB_v1();
         b.getB_v2();
         b.getB_v4();
         b.getB_v4();

         b.getA_v1();
         b.getA_v2();
     }


}


class A {
    private   int a1;
    protected int a2;

    public int getA_v1() { return a1;}
    protected int getA_v2() { return a1;}
    private int getA_v3() { return a1;}
}

class B extends A{
    public int getB_v1() { return getA_v1(); }
    public int getB_v2() { return getA_v2(); }
//    public int getB_v3() { return getA_v3(); }
    protected int getB_v4() { return a2; }
}

class C extends B{
    public int getC_v1() { return a2; }
}