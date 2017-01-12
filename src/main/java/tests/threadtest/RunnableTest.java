package tests.threadtest;

/**
 * Created by Max on 04.01.2017.
 */
public class RunnableTest  {
}

class R implements Runnable {

    @Override
    public void run() {
        Thread t = new Thread(this);

    }
}
