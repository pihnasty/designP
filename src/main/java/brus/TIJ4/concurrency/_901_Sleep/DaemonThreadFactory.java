package brus.TIJ4.concurrency._901_Sleep;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Max on 24.01.2017.
 */
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
