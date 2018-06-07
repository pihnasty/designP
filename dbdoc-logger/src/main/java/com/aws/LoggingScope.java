package com.aws;

import com.amazon.diagnostics.logger.MessageType;

public interface LoggingScope extends AutoCloseable {
    void write(MessageType level, String format, Object... args);

    void write(String format, Object... args);

    void writeMilestone(String message);

    @Override
    void close();
}
