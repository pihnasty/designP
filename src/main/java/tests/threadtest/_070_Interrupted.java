package tests.threadtest;

/**
 * Created by Max on 11.01.2017.
 */
public class _070_Interrupted {
    public static void main(String[] arg) throws InterruptedException {
        _intrrupted();
       //_sleep();
       // _wait();

    }
    public static void _intrrupted() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread myThread = Thread.currentThread();
                //while (true) {
                while (!myThread.isInterrupted()) {

                    System.out.println(myThread.isInterrupted());
                    for(long k=0; k<1_000_000_000L; k++) {}
                    System.out.println(this.getClass());
                    System.out.println(myThread.getContextClassLoader());
                    System.out.println(myThread.getStackTrace());
                    System.out.println("isInterrupted()="+myThread.isInterrupted());
                    System.out.println("isInterrupted()="+Thread.interrupted());
                }

            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    public static void _sleep(){
        Thread.currentThread().interrupt();
        try {
       //     Thread.interrupted();
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
    }

    public static void _wait(){
        Thread.currentThread().interrupt();
        try {
           Object lock = new Object();
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException wait");
        }
    }

}



