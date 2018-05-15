package tests.core.interfaces;

public class A2 extends A1 {
  public A2() {

  }
  public void showA2()  {

        super.show(1);
        super.showA();

        staticA1();

        A1 a1 = new A1();

      //  throw a1;
      new Exception();
  }
}
