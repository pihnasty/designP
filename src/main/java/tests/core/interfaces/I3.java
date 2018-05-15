package tests.core.interfaces;

public interface I3 extends I2 {
  default void show(int i) {
    System.out.println("show from I3");
     I2.super.show(1);
     // I1.super.show(1);

  }

  }
