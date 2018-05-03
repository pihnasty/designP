package tests.core.packages.test;

import tests.core.packages.test2.E;
// import tests.core.packages.test2.E2;

public class C {
  void test() {
    A a = new A();
    String a1 = a.sWith;
    String a2 = a.sProtected;
    String a3 = a.sPublic;

    D d = new D();
    String a5 = a.sWith;
    String a4 = d.sProtected;
    String a8 = a.sPublic;

    Z z = new Z();
//    String a9 = z.sWith;
//    String a19 = z.sProtected;
    String a6 = z.sPublic;

    E e = new E();
    String a9 = e.sPublic;


//    E2 e2 = new E2();


  }
}
