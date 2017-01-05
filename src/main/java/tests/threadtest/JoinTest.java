package tests.threadtest;

/**
 * Created by Max on 05.01.2017.
 */
public class JoinTest {
    public static void main(String [] args) throws InterruptedException {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for(int  i=0; i<30; i++)
                    try {
                        Thread.sleep(250);
                        System.out.println("A");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for(int  i=0; i<30; i++)
                    try {
                        Thread.sleep(1000);
                        System.out.println("b_________________________");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        System.out.println("main begin");
        t1.start();
       t2.start();
        t1.join();
       t2.join();
        System.out.println("main end");

    }
}


