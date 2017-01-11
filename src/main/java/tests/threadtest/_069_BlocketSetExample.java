package tests.threadtest;

/**
 * Created by Max on 07.01.2017.
 */
public class _069_BlocketSetExample {
    public synchronized void f(int x) throws InterruptedException {
        System.out.println("+"+x);
  //      Thread.sleep(1000);
        this.wait();
        System.out.println("-"+x);
    }
}
