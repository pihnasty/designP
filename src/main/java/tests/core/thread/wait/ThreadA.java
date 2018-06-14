package tests.core.thread.wait;

public class ThreadA {
    public static void main(String[] args) throws InterruptedException {



        ThreadB b = new ThreadB();
        b.start();


        ThreadC b2 = new ThreadC();
        b2.start();

        Thread a = Thread.currentThread();


        Object o = new Object();

        synchronized(o)



        {

            try{
                o.wait();
                System.out.println("Waiting for b to complete...");
           //     b.wait();
            }catch(InterruptedException | IllegalMonitorStateException e){
                e.printStackTrace();
            }

            System.out.println("Total is: " + b.total);
        }

        System.out.println("Main: ");
    }
}

class ThreadB extends Thread{
    int total;
    @Override
    public void run(){
        synchronized(this){
            for(int i=0; i<10 ; i++){
                total += i;
                System.out.println(i);
            }
            System.out.println("notify()");
            notify();
        }
    }



}

class ThreadC extends Thread {
    int total;

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                total += i;
                System.out.println(i + "C^");
            }
            System.out.println("C^ notify()");
            notify();
        }
    }
}