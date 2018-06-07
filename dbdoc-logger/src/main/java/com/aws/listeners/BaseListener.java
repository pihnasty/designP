package com.aws.listeners;

import com.aws.LogListener;
import com.aws.LogMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Base default implementation of LogListener interface
 */
public abstract class BaseListener implements LogListener, AutoCloseable {
    private String messageFormat = LogMessage.DEFAULT_MESSAGE_FORMAT;
    private Predicate<LogMessage> messageFilter = message -> true;

    protected BaseListener() {}

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    protected abstract void writeImpl(LogMessage message) throws IOException;

    public void close() throws Exception {
    }

    public void write(LogMessage message) throws Exception {
        if(messageFilter.test(message))
            writeImpl(message);
    }

    public Predicate<LogMessage> getMessageFilter() {
        return messageFilter;
    }

    public void setMessageFilter(Predicate<LogMessage> messageFilter) {
        this.messageFilter = Objects.requireNonNull(messageFilter);
    }

    protected String getMessageText(LogMessage message) {
        return message.toString(messageFormat);
    }

    @Override public void idle() throws Exception {
        // Does nothing by default
    }
}
