package tests.core.thread.wait;

import static java.lang.Thread.sleep;

public class SyncTest {

    public static void main(String[] args) throws InterruptedException {
        Object sync = new Object();
        Data data = new Data();
        Thread t = new Thread(new WaitingThread(sync, data));
        t.start();
        try{
            System.out.println("main::Sleeping");
            sleep(500);
        }catch(InterruptedException ex){
            System.err.println("main::Interrupted: "+ex.getMessage());
        }
        synchronized (sync){
            System.out.println("main::setting value to 1");
            data.value = 1;
            System.out.println("main::notifying thread");
            sync.notify();
            System.out.println("main::Thread notified");

            for (int i=1; i<10; i++) {
                System.out.println("for ");
                sleep(1000);
            }


        }
    }

    static class Data {
        public int value = 0;
    }

    static class WaitingThread implements Runnable {

        private Object sync;
        private Data data;

        public WaitingThread(Object sync, Data data) {
            this.sync = sync;
            this.data = data;
        }

        public void run() {
            System.out.println("own:: Thread started");
            synchronized(sync) {
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (data.value == 0) {
                    try {
                        System.out.println("own:: Waiting before");
                        sync.wait();
                        System.out.println("own:: Waiting after");
                    } catch (InterruptedException ex) {
                        System.err.println("own:: Interrupted: "+ex.getMessage());
                    }
                }
                System.out.println("own:: data.value = "+data.value);
            }
        }
    }
}
