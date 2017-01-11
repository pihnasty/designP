package tests.threadtest;

/**
 * Created by Max on 07.01.2017.
 */
public class _03_synchnizeTest {
    static public void main (String [] args ) {
       //_01_Object_wait(); // Exception in thread "main" java.lang.IllegalMonitorStateException
      // _02_Object_notify(); // Exception in thread "main" java.lang.IllegalMonitorStateException
      // _03_Object_notifyAll(); // Exception in thread "main" java.lang.IllegalMonitorStateException
      // _04_Object_synchronize();
     //  _05_Object_synchronize_null(); // Exception in thread "main" java.lang.NullPointerException
     //   _06_Object_synchronize_2_ref();
     //   _07_Object_synchronize_2_synchronized();
     //   new _03_synchnizeTest()._08_Object_synchronize_method();
     //   new _03_synchnizeTest()._09_Object_synchronize_ObjectToMethod();
     //   _10_Object_synchronize_ClassToMethod();
     //   _11_Object_synchronize_ClassToMethod_02();  // одинаков 10
        _12_Delegat();   // несколько потоков заходят в один  метод
    }

    private static void _01_Object_wait() {
        Object ref = new Object();
        try {
            ref.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void _02_Object_notify() {
        Object ref = new Object();
            ref.notify();
    }
    private static void _03_Object_notifyAll() {
        Object ref = new Object();
        ref.notifyAll();
    }
    private static void _04_Object_synchronize() {
        Object ref = new Object();
        synchronized (ref)
        {
            try {
                ref.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void _05_Object_synchronize_null() {
        Object ref = null;
        synchronized (ref)
        {
            try {
                ref.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void _06_Object_synchronize_2_ref() {
        Object ref = new Object();
        Object ref1 = ref;
        synchronized (ref)
        {
            try {
                ref.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void _07_Object_synchronize_2_synchronized() {
        Object ref = new Object();
        Object ref1 = new Object();
        synchronized (ref) {
            synchronized (ref1) {
                try {
                    ref.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (ref) {
            synchronized (ref) {
                try {
                    ref.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void _08_Object_synchronize_method() {
        this.notify();
    }
    public  void _09_Object_synchronize_ObjectToMethod() {
        synchronized (this) {
            this.notify();
        }
    }
    public  static synchronized  void _10_Object_synchronize_ClassToMethod() {
        Class clazz = _03_synchnizeTest.class;
        clazz.notify();
    }
    public  static void _11_Object_synchronize_ClassToMethod_02() {
        Class clazz = _03_synchnizeTest.class;
        synchronized (clazz) {
            clazz.notify();
        }
    }
    public  static void _12_Delegat() {
        _069_BlocketSetExample ref = new _069_BlocketSetExample();
        for (int k=0; k<5; k++) {
            new Thread(new _03_Delegat(ref,k)).start();
        }
    }
}
