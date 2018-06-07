package com.aws;

/**
 * Log listener interface
 */
public interface LogListener {
    /**
     * Writes message.
     * @param message LogMessage object.
     * @throws Exception
     */
    void write(LogMessage message) throws Exception;

    /**
     * This method is called where there is no more messages in the logger queue.
     * @throws Exception
     */
    void idle() throws Exception;
}
