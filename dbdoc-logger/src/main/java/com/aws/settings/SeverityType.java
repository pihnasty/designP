package com.aws.settings;

public enum SeverityType {
    CRITICAL(0),
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int value;

    SeverityType(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
