package brus.TIJ4.concurrency._894;//: concurrency/LiftOff.java
// Demonstration of the Runnable interface.

public class LiftOff implements Runnable {
  protected int countDown = 10; // Default
  private static int taskCount = 0;
  private final int id = taskCount++;
  public LiftOff() {}
  public LiftOff(int countDown) {
    this.countDown = countDown;
  }
  public String status() {
    return "#" + id + "(" +
      (countDown > 0 ? countDown : "Liftoff!") + "), ";
  }
  public void run() {
    //System.out.println(countDown);
    while(countDown-- > 0) {
      //System.out.println(countDown);
      System.out.print(status());
      Thread.yield();   //  https://habrahabr.ru/post/164487/
    }
  }
} ///:~
