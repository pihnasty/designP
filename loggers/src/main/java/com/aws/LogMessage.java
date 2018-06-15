package com.aws;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

/**
 * Contains log information.
 */
public final class LogMessage {
    public static final String DEFAULT_MESSAGE_FORMAT = "%1$s [%2$4d] %3$11s %4$-7s %5$s";

    private static final String NEW_LINE_TEXT = System.lineSeparator();
    DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    //private static final String INDENT_TEXT = "\t";
    //private static final String SPACE_TEXT = " ";

    //private String processName;
    //private LocalTime time;
    private LocalDateTime dateTime;
    private long threadId;
    //private String threadName;
    private SubsystemLogger subsystem;
    private MessageType messageType;
    private String message;
    //private int indentLevel;
    private Throwable throwable;

    // region c-tors

    /**
     * Constructs LogMessage instance.
     * @param subsystem Subsystem that produced the message.
     * @param messageType Message type.
     * @param message   Message body.
     * @param args [optional] message body arguments.
     * @param throwable   Occurred throwable.
     */
    public LogMessage(
            SubsystemLogger subsystem,
            MessageType messageType,
            String message,
            Object[] args,
            Throwable throwable){
        // formatting message
        if(args != null && args.length > 0)
            message = String.format(message, args);
        // automatically retrieved data
        Thread currentThread = Thread.currentThread();
    //    this.time = LocalTime.now();
        this.dateTime = LocalDateTime.now();
        //this.processName = ManagementFactory.getRuntimeMXBean().getName();
        this.threadId = currentThread.getId();
        //this.threadName = Objects.toString(currentThread.getName(), Long.toString(threadId));
        //
        this.subsystem = subsystem;
        this.messageType = messageType;
        this.message = message;

        this.throwable = throwable;
    }

    // endregion

    // region getters

    /**
     * Gets indent level.
     */
    /*public int getIndentLevel() {
        return indentLevel;
    }*/

    /**
     * Gets message type.
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * Gets message string.
     */
    public String getMessage() {
        return message;
    }

    public SubsystemLogger getSubsystem() {
        return subsystem;
    }

    /*public String getProcessName() {
        return processName;
    }*/

//    public LocalTime getTime() {
//        return time;
//    }

//    public LocalDateTime getDateTime() {
//        return dateTime;
//    }

    public long getThreadId() {
        return threadId;
    }

    /*public String getThreadName() {
        return threadName;
    }*/

    public Throwable getThrowable() {
        return throwable;
    }

    // endregion

    public String toString(String format){
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        formatter.format(format, dateTime.format(dataTimeFormatter), threadId, subsystem, messageType, message);
        builder.append(NEW_LINE_TEXT);

        if (throwable != null) {
            StringWriter trace = new StringWriter();
            throwable.printStackTrace(new PrintWriter(trace));
            builder.append(trace);
            builder.append(NEW_LINE_TEXT);
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return toString(DEFAULT_MESSAGE_FORMAT);
    }
}
