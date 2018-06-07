package com.aws.listeners;

import com.aws.LogMessage;
import com.aws.MessageType;
import com.aws.util.FileUtils;
import com.aws.util.SizeMeasuringFileOutputStream;
import com.aws.util.StringUtils;
import com.aws.util.ValidationUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * TODO class description
 */
public class AdvancedFileListener extends BaseListener implements AutoCloseable{
    private static final String LOG_NAME_FORMAT = "{0}-{1}-{2,date,yyyyMMdd-HH-mm-ss}{3,choice,1#|1<-{3}}.log";
    private static final String LOG_NAME_PATTERN = "(-\\w+)(-\\d+)+\\.log";
    private static final String LOG_ENCODING = "utf-8";

    private static final MessageFormat logNameFormat = new MessageFormat(LOG_NAME_FORMAT);

    private final String productName;
    private final String sessionName;
    private final Predicate<String> isLogFileName;

    // NOTE: these fields must be changed only in synchronized context
    private Path logFolderPath;
    private Date timestamp;
    private int logPartNo;
    private boolean flushed;
    private boolean closed;

    private AtomicLong maxLogPartSize = new AtomicLong(Long.MAX_VALUE);
    private AtomicInteger maxLogPartsCount = new AtomicInteger(Integer.MAX_VALUE);


    // NOTE: these fields must be changed only in synchronized context
    private SizeMeasuringFileOutputStream currentFileOutput;
    private Writer currentWriter;

    public AdvancedFileListener(
            Path logFolderPath, String productName, String sessionName,
            long maxLogPartSize, int maxLogPartsCount) throws IOException {
        this.productName = StringUtils.requireNonEmpty(productName);
        this.sessionName = StringUtils.requireNonEmpty(sessionName);
        this.isLogFileName = Pattern.compile(Pattern.quote(productName) + LOG_NAME_PATTERN).asPredicate();

        timestamp = new Date();

        setMaxLogPartSize(maxLogPartSize);
        setMaxLogPartsCount(maxLogPartsCount);
        setLogFolder(logFolderPath);
    }

    public synchronized void setLogFolder(Path logFolderPath) throws IOException{
        if(Objects.equals(this.logFolderPath, logFolderPath))
            return; // Nothing changed.

        FileUtils.checkAndCreateDir(logFolderPath);
        closeCurrentLogFile();
        this.logFolderPath = logFolderPath;
    }

    /**
     * Getter for property 'maxLogPartsCount'.
     *
     * @return Value for property 'maxLogPartsCount'.
     */
    public int getMaxLogPartsCount() {
        return maxLogPartsCount.get();
    }

    /**
     * Setter for property 'maxLogPartsCount'.
     *
     * @param maxLogPartsCount Value to set for property 'maxLogPartsCount'.
     */
    public void setMaxLogPartsCount(int maxLogPartsCount) {
        this.maxLogPartsCount.set(ValidationUtils.requirePositive(maxLogPartsCount));
    }

    /**
     * Getter for property 'maxLogPartSize'.
     *
     * @return Value for property 'maxLogPartSize'.
     */
    public long getMaxLogPartSize() {
        return maxLogPartSize.get();
    }

    /**
     * Setter for property 'maxLogPartSize'.
     *
     * @param maxLogPartSize Value to set for property 'maxLogPartSize'.
     */
    public void setMaxLogPartSize(long maxLogPartSize) {
        this.maxLogPartSize.set(ValidationUtils.requirePositive(maxLogPartSize));
    }

    @Override
    protected synchronized void writeImpl(LogMessage message) throws IOException {
        if(closed)
            return; // Won't continue writing after close() method is called.

        if (shouldMoveToNextLogPart())
            startNextLogPart();

        currentWriter.write(getMessageText(message));
        if(message.getMessageType().isAtLeast(MessageType.INFO)) {
            currentWriter.flush();
            flushed = true;
        }
        else flushed = false;
    }

    @Override public void idle() throws Exception {
        if(!flushed && currentWriter != null) {
            currentWriter.flush();
            flushed = true;
        }
    }

    private boolean shouldMoveToNextLogPart(){
        return currentFileOutput == null || currentFileOutput.getSize() >= maxLogPartSize.get();
    }

    private synchronized void startNextLogPart() throws IOException{
        closeCurrentLogFile();

        int tryCount = 10;
        do {
            logPartNo++;
            String logPartName = logNameFormat.format(new Object[]{productName, sessionName, timestamp, logPartNo});
            File logPartFile = logFolderPath.resolve(logPartName).toFile();
            try{
                createLogFile(logPartFile);
                break;
            }catch (FileNotFoundException e) {
                // check for timestamp collision
                    if(logPartFile.exists() && logPartNo == 1 && tryCount --> 0){
                        // try to move to use next possible timestamp
                        timestamp = new Date(timestamp.getTime() + 1000 /*millis*/);
                        logPartNo = 0;
                        continue;
                }
                else throw e;
            }
        } while(true);

        cleanOldLogParts();
    }

    private void cleanOldLogParts() {
        File[] files = logFolderPath.toFile().listFiles();
        if (files == null)
            return;
        Arrays.stream(files)
              // get only product related log files
              .filter(file -> file.isFile() && isLogFileName.test(file.getName()))
              // sort by last write time DESCENDING
              .sorted((a, b) -> Long.compare(b.lastModified(), a.lastModified()))
              .skip(maxLogPartsCount.get())
              .forEachOrdered(this::tryRemoveLogFile);
    }

    private void tryRemoveLogFile(File file){
        try{
            file.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createLogFile(File file) throws FileNotFoundException, UnsupportedEncodingException {
        currentFileOutput = new SizeMeasuringFileOutputStream(file);
        currentWriter = new BufferedWriter(new OutputStreamWriter(currentFileOutput, LOG_ENCODING));
    }

    private void closeCurrentLogFile() throws IOException {
        if(currentWriter != null){
            currentWriter.close();
            currentWriter = null;
        }
        if(currentFileOutput != null){
            currentFileOutput.close();
            currentFileOutput = null;
        }
    }


    @Override
    public synchronized void close() throws Exception {
        closeCurrentLogFile();
        closed = true;
    }
}
