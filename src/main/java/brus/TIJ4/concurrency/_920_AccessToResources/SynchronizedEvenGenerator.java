package brus.TIJ4.concurrency._920_AccessToResources;//: concurrency/SynchronizedEvenGenerator.java

import static javafx.application.Platform.exit;

public class
SynchronizedEvenGenerator extends IntGenerator {
  private int currentEvenValue = 0;
  public synchronized int next() {
    ++currentEvenValue;
    System.out.println("  "+Thread.currentThread());

    Thread.yield(); // Cause failure faster
    ++currentEvenValue;
    System.exit(0);
    return currentEvenValue;
  }
  public static void main(String[] args) {
    EvenChecker.test(new SynchronizedEvenGenerator());
  }
} ///:~
