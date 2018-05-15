package tests.core.exeptions;

public class Main {
  public static void main(String[] args) {
    System.out.println("");
    try {
      try {
        System.out.print("A");
        throw new Exception("1");
      } catch (Exception e) {
        System.out.print("B");
        throw new Exception("2",e);
      } finally {
        System.out.print("C");
        throw new Exception("3");
      }
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
  }
}
