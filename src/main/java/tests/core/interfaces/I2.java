package tests.core.interfaces;

public interface I2 extends I1{
  default void show (int i) {
    System.out.println("show from I2");
 //   I1.super.show(1);

  }
  static void staticShowI2 () {
    System.out.println("public static void staticShow");
  }

  }
