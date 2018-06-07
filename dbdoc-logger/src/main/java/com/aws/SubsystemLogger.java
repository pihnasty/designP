package com.aws;


import java.util.Objects;
import java.util.function.Supplier;

import static com.aws.MessageType.CRITICAL;
import static com.aws.MessageType.DEBUG;
import static com.aws.MessageType.ERROR;
import static com.aws.MessageType.INFO;
import static com.aws.MessageType.TRACE;
import static com.aws.MessageType.WARNING;

/**
 * Subsystem Logger to write logging messages in concrete subsystem
 *
 */
public final class SubsystemLogger {

    private final String name;
    private final LoggerEngine engine;

    public SubsystemLogger(String name) {
        this.name = Objects.requireNonNull(name);
        this.engine = LoggerEngine.getInstance();
    }

    public boolean accepts(MessageType level){
        return engine.accepts(this, level);
    }

    /**
     * Writes trace message into log.
     */
    public void writeTrace(CharSequence message) {
        writeLog(TRACE, message);
    }

    /**
     * Writes trace message into log.
     */
    public void writeTrace(String format, Object... args) {
        writeLog(TRACE, null, format, args);
    }

    /**
     * Writes debug info into log.
     */
    public void writeTrace(Supplier<String> lazyMessageSupplier) {
        writeLog(TRACE, lazyMessageSupplier);
    }

    /**
     * Writes debug info into log.
     */
    public void writeDebug(CharSequence message) {
        writeLog(DEBUG, message);
    }

    /**
     * Writes debug info into log.
     */
    public void writeDebug(String format, Object... args) {
        writeLog(DEBUG, null, format, args);
    }

    /**
     * Writes debug info into log.
     */
    public void writeDebug(Supplier<String> lazyMessageSupplier) {
        writeLog(DEBUG, lazyMessageSupplier);
    }


    /**
     * Writes common info into a log.
     */
    public void writeInfo(CharSequence message) {
        writeLog(INFO, message);
    }


    /**
     * Writes common info into a log.
     */
    public void writeInfo(String format, Object... args) {
        writeLog(INFO, null, format, args);
    }

    /**
     * Writes warning info into log.
     */
    public void writeWarning(CharSequence message) {
        writeLog(WARNING, message);
    }

    /**
     * Writes warning info into log.
     */
    public void writeWarning(String format, Object... args) {
        writeLog(WARNING, null, format, args);
    }

    /**
     * Writes error info into log.
     */
    public void writeError(CharSequence message) {
        writeLog(ERROR, message);
    }

    /**
     * Writes error info into log.
     */
    public void writeError(String format, Object... args) {
        writeLog(ERROR, null, format, args);
    }


     /**
     * Writes error info into log.
     */
    public void writeError(Throwable exception) {
        writeLog(ERROR, exception, exception.getLocalizedMessage(), null);
    }

    /**
     * Writes error info into log.
     */
    public void writeError(Throwable exception, String message) {
        writeLog(ERROR, exception, message, null);
    }

    /**
     * Writes error info into log.
     */
    public void writeError(Throwable exception, String format, Object... args) {
        writeLog(ERROR, exception, format, args);
    }


    /**
     * Writes critical error info into log.
     */
    public void writeCriticalError(String format, Object... args) {
        writeLog(CRITICAL, null, format, args);
    }


    /**
     * Writes critical error info into log.
     */
    public void writeCriticalError(Throwable exception) {
        writeLog(CRITICAL, exception, exception.getLocalizedMessage(), null);
    }

    /**
     * Writes critical error info into log.
     */
    public void writeCriticalError(Throwable exception, String format, Object... args) {
        writeLog(CRITICAL, exception, format, args);
    }

    /**
     * Writes formatted message into log.
     */
    public void write(MessageType type, CharSequence message) {
        writeLog(type, message);
    }

    /**
     * Writes formatted message into log.
     *
     * @param type   Message type.
     * @param format Message string format.
     * @param args   Message string arguments.
     */
    public void write(MessageType type, String format, Object... args) {
        writeLog(type, null, format, args);
    }

    /**
     * Writes formatted message into log.
     *
     * @param type   Message type.
     * @param format Message string format.
     * @param args   Message string arguments.
     */
    public void write(MessageType type, Throwable throwable, String format, Object... args) {
        writeLog(type, throwable, format, args);
    }

    // region core methods

    private void writeLog(MessageType type, Supplier<String> messageSupplier) {
        if(engine.accepts(this, type))
            engine.writeLog(this, type, messageSupplier.get());
    }
    private void writeLog(MessageType type, CharSequence messageObject) {
        engine.writeLog(this, type, messageObject);
    }

    private void writeLog(MessageType type, Throwable throwable, String format, Object[] args) {
        engine.writeLog(this, type, format, args, throwable);
    }

    // endregion

    public String getName() {
        return name;
    }

    public LoggingScope createScope(String name){
        return createScope(name, false);
    }

    public LoggingScope createScope(String name, boolean statisticsScope){
      //  if(LoggerEngine.disableScopes)
            return (LoggingScope) NonLoggingScope.instance;

      //  return new LoggingScopeImpl(this, INFO, name, statisticsScope);
    }

    @Override public String toString() {
        return name;
    }
}
