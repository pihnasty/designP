package tests.threadtest;

/**
 * Created by Max on 07.01.2017.
 */
public class _03_Delegat implements Runnable {
     private final _069_BlocketSetExample ref;
     private final  int k;
    public _03_Delegat(_069_BlocketSetExample ref, int k ){
        this.ref = ref;
        this.k = k;
    }

    @Override
    public void run() {
        try {
            ref.f(k);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
