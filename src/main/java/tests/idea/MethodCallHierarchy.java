package tests.idea;

/**
 * Ctrl + Shift + H	                                Иерархия метода
 * Ctrl + Alt   + H	                                Иерархии вызовов
 */
public class MethodCallHierarchy {

   static void main(String [] args) {
       A1 a1 = new A1();
       a1.mA1();
   }
}



class A1 {
  public void mA1() {
      A2 a2 = new A2();
      a2.mA2();

      A3 a3 = new A3();
      a3.mA3();
  }
}

class A2 {
    void mA2() {}
}

class A3 {
    void mA3() {
        A4 a4 = new A4();
        a4.mA4();
    }
}

class A4 {
    void mA4() {

    }
}
