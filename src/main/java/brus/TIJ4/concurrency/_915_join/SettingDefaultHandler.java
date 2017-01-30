package brus.TIJ4.concurrency._915_join;//: concurrency/SettingDefaultHandler.java
import brus.TIJ4.concurrency._915_join.ExceptionThread;

import java.util.concurrent.*;

public class SettingDefaultHandler {
  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(
      new MyUncaughtExceptionHandler());
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(new ExceptionThread());
  }
} /* Output:
caught java.lang.RuntimeException
*///:~
