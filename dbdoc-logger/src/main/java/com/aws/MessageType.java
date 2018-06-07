package com.aws;

public enum MessageType {
    TRACE, DEBUG, INFO, WARNING, ERROR, CRITICAL, MANDATORY;

    public static MessageType parse(String value) {
        if (value == null) {
            return null;
        }
        return Enum.valueOf(MessageType.class, value);
    }

    public boolean isAtLeast(MessageType threshold) {
        return this.ordinal() >= threshold.ordinal();
    }
}
