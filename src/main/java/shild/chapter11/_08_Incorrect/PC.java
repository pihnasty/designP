package shild.chapter11._08_Incorrect;

public class PC {
    public static void main(String[] args) {


        Q q = new Q();
        Produser produser = new Produser(q);
        Consumer consumer = new Consumer(q);
    }


}

class Q {
    private int n=0;

    synchronized int get() {
        System.out.println("Выдано N:" + n);
        return n;
    }

    synchronized void put(int n) {
        this.n = n;
        System.out.println("Получено N:" + n);
    }

}

class Produser implements Runnable {
    private Q q;
    public Produser(Q q) {
        this.q=q;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        int i=1;
        while (true) {
            q.put(i++);
        }
    }
}

class Consumer implements Runnable {
    private Q q;
    public Consumer(Q q) {
        this.q=q;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        int i=1;
        while (true) {
            q.get();
        }
    }
}