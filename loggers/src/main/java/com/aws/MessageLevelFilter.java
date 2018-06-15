package com.aws;


import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Message filter accepting messages by their level.
 * Supports separate threshold levels for different subsystems.
 */
public final class MessageLevelFilter
        extends ConcurrentHashMap<SubsystemLogger, MessageType>
        implements Predicate<LogMessage>, BiPredicate<SubsystemLogger, MessageType> {

    private volatile MessageType defaultThresholdLevel;

    public MessageLevelFilter(MessageType lowestLevelToAccept) {
        setDefaultThresholdLevel(lowestLevelToAccept);
    }

    public MessageLevelFilter() {
        this(MessageType.TRACE);
    }

    // region factory methods

    public static MessageLevelFilter forDebugOption(boolean acceptDebugMessages){
        return new MessageLevelFilter(acceptDebugMessages ? MessageType.TRACE : MessageType.INFO);
    }

    public static MessageLevelFilter withDefaultLevel(MessageType lowestLevelToAccept){
        return new MessageLevelFilter(lowestLevelToAccept);
    }

    // endregion factory methods

    // region setters

    public void setDefaultThresholdLevel(MessageType lowestLevelToAccept){
        defaultThresholdLevel = Objects.requireNonNull(lowestLevelToAccept);
    }

    public void setThresholdLevel(MessageType lowestLevelToAccept){
        setDefaultThresholdLevel(Objects.requireNonNull(lowestLevelToAccept));
        clear();
    }

    public void setThresholdLevel(SubsystemLogger subsystem, MessageType lowestLevelToAccept){
        if(lowestLevelToAccept == null)
            remove(subsystem);
        else
            put(subsystem, lowestLevelToAccept);
    }

    // endregion setters

    public MessageType getThresholdLevel(SubsystemLogger subsystem){
        return getOrDefault(subsystem, defaultThresholdLevel);
    }

    public boolean test(SubsystemLogger subsystem, MessageType messageType){
        return messageType.isAtLeast(getThresholdLevel(subsystem));
    }

    @Override
    public boolean test(LogMessage logMessage) {
        return test(logMessage.getSubsystem(), logMessage.getMessageType());
    }

    @Override public MessageLevelFilter negate() {
        throw new UnsupportedOperationException();
    }
}
