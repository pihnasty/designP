package com.aws;


/**
 * Notifies one or more waiting threads that an event has occurred.
 */
public class ManualResetEvent {

    private final Object sync = new Object();
    private volatile boolean open = false;

    /**
     * Initializes a new instance of the ManualResetEvent class with a Boolean value indicating
     * whether to set the initial state to signaled.
     * @param open true to set the initial state signaled; false to set the initial state to nonsignaled.
     */
    public ManualResetEvent(boolean open) {
        this.open = open;
    }

    /**
     * Blocks the current thread until the current ManualResetEvent receives a signal.
     * @throws InterruptedException
     */
    public void waitOne() throws InterruptedException {
        synchronized (sync) {
            while (!open) {
                sync.wait();
            }
        }
    }

    /**
     * Blocks the current thread until the current ManualResetEvent receives a signal,
     * using a long integer to specify the time interval in milliseconds.
     * @param milliseconds The number of milliseconds to wait.
     * @return true if the current instance receives a signal; otherwise, false.
     * @throws InterruptedException
     */
    public boolean waitOne(long milliseconds) throws InterruptedException {
        synchronized (sync) {
            if (open)
                return true;
            sync.wait(milliseconds);
            return open;
        }
    }

    /**
     * Sets the state of the event to signaled, allowing one or more waiting threads to proceed.
     */
    public void set() {//open start
        synchronized (sync) {
            open = true;
            sync.notifyAll();
        }
    }

    /**
     * Sets the state of the event to nonsignaled, causing threads to block.
     */
    public void reset() {//close stop
        open = false;
    }

}