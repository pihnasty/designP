package shild.chapter11._06synch;

import static java.lang.Thread.sleep;

// This program is not synchronized.
class Callme {
    synchronized   void  call(String msg) {
        System.out.print("[" + msg);
        try {
            sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]"+ "->"+ msg);
    }
}

class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;

    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

 public void run() {
        System.out.println(" \n input in run()="+msg);
        target.call(msg);
        System.out.println("\n output from run()"+msg);
    }
}

public class Synch {
    public static void main(String args[]) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");

        // wait for threads to end
//        try {
//            System.out.println("input in join()1");
//            ob1.t.join();
//            System.out.println("input in join()2");
//            ob2.t.join();
//            System.out.println("input in join()3");
//            ob3.t.join();
//        } catch(InterruptedException e) {
//            System.out.println("Interrupted");
//        }
    }
}
