package tests.core.interfaces;

public class A1 implements I2{
  static void staticShowA1 () {
    System.out.println("staticShowA1");
   }
   public void showA() {
     I2.super.show(1);
     I1.staticShow();
     //super.;
     }

  public static void staticA1() {
    System.out.println("staticA1()");

  }
}
