package tests.core.interfaces;

public interface I1 {

  static void staticShow () {
    System.out.println("public static void staticShow");
  }

  default void show (int i) {
    System.out.println("show from I1");
  }
  default void showDefault2 (int i) {
    System.out.println("show from I1");
  }

}
