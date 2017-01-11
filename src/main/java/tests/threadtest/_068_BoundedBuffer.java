package tests.threadtest;

/**
 * Created by Max on 08.01.2017.
 */
public class _068_BoundedBuffer {
    public static void main(String[] arg) {
        testSingleBuffer();

    }


    public static void testSingleBuffer() {
        SingleElementBuffer singleElementBuffer = new SingleElementBuffer();
        Producer producer = new Producer(1,1000,singleElementBuffer);
        Consumer consumer = new Consumer(singleElementBuffer);

        Thread tP = new Thread(producer);
        Thread tC = new Thread(consumer);
        tP.start();
        tC.start();


    }
}

class SingleElementBuffer {
    private Integer elem = null;

    public synchronized void put (Integer newElem) throws InterruptedException {
        while (elem!=null) {
            this.wait();
        }
        this.elem = newElem;
        this.notifyAll();
    }
    public synchronized Integer get() throws InterruptedException {
        while (elem==null) {
            this.wait();
        }
        int result = this.elem;
        this.elem = null;
        this.notifyAll();
        return result;
    }
}

class Producer implements Runnable {
    private int startValue;
    private final int period;
    private final SingleElementBuffer buffer;


    public Producer(int startValue, int period, SingleElementBuffer buffer) {
        this.startValue = startValue;
        this.period = period;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(startValue+ "produced");
                buffer.put(startValue++);
                Thread.sleep(period);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName());
                return;
            }
        }
    }
}

class Consumer implements Runnable {
    private final SingleElementBuffer buffer;

    public Consumer(SingleElementBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                int elem = buffer.get();
                System.out.println(elem+"consume");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName());
                return;
            }
        }
    }
}
