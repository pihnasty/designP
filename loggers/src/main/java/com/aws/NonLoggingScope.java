package com.aws;


import com.amazon.diagnostics.logger.LoggingScope;
import com.amazon.diagnostics.logger.MessageType;

public class NonLoggingScope implements LoggingScope {

    public static final NonLoggingScope instance = new NonLoggingScope();

    private NonLoggingScope() {}


    @Override
    public void write(MessageType level, String format, Object... args) {

    }

    @Override
    public void write(String format, Object... args) {

    }

    @Override
    public void writeMilestone(String message) {

    }

    @Override
    public void close() {

    }
}
