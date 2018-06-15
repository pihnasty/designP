package com.aws;

import java.io.Closeable;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiPredicate;

import static com.aws.FormatUtils.memoryToString;


/**
 * Main entry point of log system logic.
 */
public final class LoggerEngine implements Closeable {

    // MAGIC
    // RR: this logging breaks some testing functionality at least in sct-parser-batch
    // (which is based on previous and current logs comparison), so added possibility
    // to switch it off
    public static boolean disableScopes = false;

    private final static int LOGGER_FINALIZATION_TIMEOUT_MILLIS = 5*60*1000/*5 min*/;

    private final ConcurrentLinkedQueue<LogListener> listeners = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<LogMessage> messageBuffer = new ConcurrentLinkedQueue<>();

    private Thread thread;
    private ManualResetEvent processLog;
    private ManualResetEvent emptyLog;
    private AtomicBoolean exitProcess = new AtomicBoolean(false);

    private static final Object instanceSync = new Object();
    private static volatile LoggerEngine logger;

    private BiPredicate<SubsystemLogger, MessageType> primaryFilter;
    private final ThreadLocal<MultiTimeCounterSet> statistics = ThreadLocal.withInitial(MultiTimeCounterSet::new);

    /**
     * Constructs a Logger instance and starts working thread for
     * sending messages.
     */
    private LoggerEngine() {
        processLog = new ManualResetEvent(false);
        emptyLog = new ManualResetEvent(true);
        thread = new Thread(this::run, "Logger thread");
        thread.setDaemon(true);
        thread.start();
    }

    public BiPredicate<SubsystemLogger, MessageType> getPrimaryFilter() {
        return primaryFilter;
    }

    public void setPrimaryFilter(BiPredicate<SubsystemLogger, MessageType> primaryFilter) {
        this.primaryFilter = primaryFilter;
    }

    public boolean accepts(SubsystemLogger subsystem, MessageType type){
        return primaryFilter == null || primaryFilter.test(subsystem, type);
    }

    // region listeners management

    /**
     * Returns all LogListeners registered in system.
     */
    public Collection<LogListener> getListeners() {
        return listeners;
    }

    /**
     * Registers LogListener in the system.
     */
    public void addListener(LogListener listener) {
        listeners.add(listener);
    }

    /**
     * Unregisters registered LogListener.
     */
    public void removeListener(LogListener listener) {
        listeners.remove(listener);
    }

    // endregion


    // region writeLog

    /**
     * Writes LogMessage object into log.
     */
    private void writeLog(LogMessage message) {
        if(thread == null){
            // fallback scenario (messages must not be lost even if the logger wasn't initialized properly)
            (message.getMessageType().isAtLeast(MessageType.ERROR) ? System.err : System.out).println(message);
            return;
        }
        messageBuffer.add(message);
        processLog.set();
    }

    /**
     * Writes formatted message into log.
     * @param subsystem Subsystem
     * @param type   Message type.
     * @param format Message string format.
     * @param args   Message string arguments.
     */
    protected void writeLog(
        SubsystemLogger subsystem, MessageType type, String format, Object[] args, Throwable throwable) {
        if(!accepts(subsystem, type))
            return;

        try {
            LogMessage message = new LogMessage(subsystem, type, format, args, throwable);
            writeLog(message);
        } catch (Exception exc) {
            exc.printStackTrace();
            writeLog(new LogMessage(Logger.GENERAL, MessageType.ERROR, "Failed to log message", null, exc));
        }
    }

    protected void writeLog(SubsystemLogger subsystem, MessageType type, CharSequence messageObject){
        if(!accepts(subsystem, type))
            return;

        try {
            LogMessage message = new LogMessage(subsystem, type, messageObject.toString(), null, null);
            writeLog(message);
        }catch (Exception exc){
            exc.printStackTrace();
            writeLog(new LogMessage(Logger.GENERAL, MessageType.ERROR, "Failed to log message", null, exc));
        }
    }

    // endregion

    /**
     * Gets the global instance. Realized singleton pattern.
     *
     * @return Global object instance.
     */
    public static LoggerEngine getInstance() {
        if (logger == null) {
            synchronized (instanceSync) {
                if (logger == null) {
                    logger = new com.aws.LoggerEngine();
                }
            }
        }
        return logger;
    }

    /**
     * Releases the global Logger instance.
     */
    public static void clearInstance() {
        if (logger != null) {
            synchronized (instanceSync) {
                if (logger != null){
                    logger.close();
                    logger = null;
                }
            }
        }
    }

    private void run() {
        try {
            while (!exitProcess.get()) {
                processLog.waitOne();
                processLog.reset();
                emptyLog.reset();

                LogMessage message;
                while ((message = messageBuffer.poll()) != null) {
                    for (LogListener listener : listeners) {
                        try {
                            listener.write(message);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
                onEmptyQueue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onEmptyQueue();
    }

    private void onEmptyQueue(){
        for (LogListener listener : listeners) {
            try {
                listener.idle();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        emptyLog.set();
    }

    /**
     * Implements AutoClosable and Closeable interfaces.
     * Stops and releases working thread and non removed LogListeners.
     */
    @Override
    public void close() {
        if (exitProcess.get())
            return;



        try {
            writeLog(Logger.GENERAL, MessageType.MANDATORY, "Log session finished.");

            exitProcess.set(true);
            processLog.set();
            thread.join(LOGGER_FINALIZATION_TIMEOUT_MILLIS);

            LogListener listener;
            while ((listener = listeners.poll()) != null) {
                if (listener instanceof AutoCloseable) {
                    ((AutoCloseable) listener).close();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Wait until all messages are not sent.
     *
     * @throws InterruptedException
     */
    public void waitProcessed() throws InterruptedException {
        emptyLog.waitOne();
    }


    // region statistics

    public MultiTimeCounterSet getStatistics(){
        return statistics.get();
    }

    //endregion

    // region diagnostic messages

    public void writeEnvironmentInfo() {
        StringBuilder builder = new StringBuilder();

        builder.append(System.getProperty("os.name"));
        builder.append(" ").append(System.getProperty("os.version"));
        builder.append(" ").append(System.getProperty("os.arch"));
        builder.append(" ").append("Java version:");
        builder.append(" ").append(System.getProperty("java.version"));

        writeLog(Logger.GENERAL, MessageType.MANDATORY, builder);
    }

    public void writeMemoryInfo() {
        StringBuilder builder = new StringBuilder();
        long max = Runtime.getRuntime().maxMemory();
        long free = Runtime.getRuntime().freeMemory();
        long total = Runtime.getRuntime().totalMemory();
        long used = total - free;
        builder.append("Used memory: ").append(memoryToString(used)).
                append(", ").
                append("Free memory: ").append(memoryToString(free)).
                append(", ").
                append("Total memory: ").append(memoryToString(total)).
                append(", ").
                append("Maximum memory: ").append(memoryToString(max));
        writeLog(Logger.GENERAL, MessageType.MANDATORY, builder);
    }



    public void writeLibraryInfo() {
        StringBuilder builder = new StringBuilder();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)classLoader).getURLs();
        builder.append("Library list: \n");
        for (URL url : urls) {
            String path = url.getPath();
            if (path.contains(".jar")) {
                builder.append("    ").append(path.substring(path.lastIndexOf("/") + 1)).append("\n");
            }
        }
        writeLog(Logger.GENERAL, MessageType.MANDATORY, builder);
    }

    // endregion
}
