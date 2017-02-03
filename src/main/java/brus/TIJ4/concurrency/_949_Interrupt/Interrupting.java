package brus.TIJ4.concurrency._949_Interrupt;//: concurrency/Interrupting.java
// Interrupting a blocked thread.
import java.util.concurrent.*;
import java.io.*;

import static brus.TIJ4.concurrency.Print.print;

class SleepBlocked implements Runnable {
  public void run() {
    try {
      //if (false)
        TimeUnit.SECONDS.sleep(100);
    } catch(InterruptedException e) {
      print("InterruptedException SleepBlocked");
    }
    print("Exiting SleepBlocked.run()");
  }
}

class IOBlocked implements Runnable {
  private InputStream in;
  public IOBlocked(InputStream is) { in = is; }
  public void run() {
    try {
      print("Waiting for read():");
      in.read();
    } catch(IOException e) {
      if(Thread.currentThread().isInterrupted()) {
        print("Interrupted from blocked I/O");
      } else {
        throw new RuntimeException(e);
      }
    }
    print("Exiting IOBlocked.run()");
  }
}

class SynchronizedBlocked implements Runnable {
  int i=0;
  public synchronized void f() {
    while(true) // Never releases lock
    {
      System.out.println(i++);
      Thread.yield();
    }
  }
  public SynchronizedBlocked() {
    new Thread() {
      public void run() {
        f(); // Lock acquired by this thread
      }
    }.start();
  }
  public void run() {
    print("Trying to call f()");
    f();
    print("Exiting SynchronizedBlocked.run()");
  }
}

public class Interrupting {
  private static ExecutorService exec =
    Executors.newCachedThreadPool();
  static void test(Runnable r) throws InterruptedException, ExecutionException {
    Future<?> f = exec.submit(r);
    TimeUnit.MILLISECONDS.sleep(100);
    print("Interrupting " + r.getClass().getName());
    f.cancel(true); // Interrupts if running
    //System.out.println("result="+f.get());

    print("Interrupt sent to " + r.getClass().getName());
  }
  public static void main(String[] args) throws Exception {
 //   test(new SleepBlocked());
  //  test(new IOBlocked(System.in));
    test(new SynchronizedBlocked());
    TimeUnit.SECONDS.sleep(1);
    print("Aborting with System.exit(0)");
    System.exit(0); // ... since last 2 interrupts failed
  }
} /* Output: (95% match)
Interrupting SleepBlocked
InterruptedException
Exiting SleepBlocked.run()
Interrupt sent to SleepBlocked
Waiting for read():
Interrupting IOBlocked
Interrupt sent to IOBlocked
Trying to call f()
Interrupting SynchronizedBlocked
Interrupt sent to SynchronizedBlocked
Aborting with System.exit(0)
*///:~
