package com.aws;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Contains string formatting methods for various specific values.
 */
public final class FormatUtils {
    public static final long  NANOS_PER_MS = 1000_000;
    public static final long  MILLIS_PER_SECOND = 1000;
    public static final long  SECONDS_PER_MINUTE = 60;
    public static final long  MINUTES_PER_HOUR = 60;

    public static final double BYTES_IN_KB = 1024.0;
    public static final double BYTES_IN_MB = BYTES_IN_KB * 1024;
    public static final double BYTES_IN_GB = BYTES_IN_MB * 1024;
    public static final double BYTES_IN_TB = BYTES_IN_GB * 1024;
    public static final DecimalFormat MEM_SIZE_FORMAT = new DecimalFormat("#.##");

    public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String millisToString(long millis){
        long seconds = millis / MILLIS_PER_SECOND;
        long minutes = seconds / SECONDS_PER_MINUTE;
        long hours = minutes / MINUTES_PER_HOUR;

        millis %= MILLIS_PER_SECOND;
        seconds %= SECONDS_PER_MINUTE;
        minutes %= MINUTES_PER_HOUR;

        return String.format("%d:%02d:%02d.%03d sec", hours, minutes, seconds, millis);
    }

    public static String nanosToString(long nanos){
        /*if(nanos < NANOS_PER_MS)
            return nanos + " ns";*/

        return millisToString(nanos / NANOS_PER_MS);
    }

    public static String memoryToString(long bytes) {
        if (bytes >= BYTES_IN_TB) return MEM_SIZE_FORMAT.format(bytes / BYTES_IN_TB) + " TB";
        else if (bytes >= BYTES_IN_GB) return MEM_SIZE_FORMAT.format(bytes / BYTES_IN_GB) + " GB";
        else if (bytes >= BYTES_IN_MB) return MEM_SIZE_FORMAT.format(bytes / BYTES_IN_MB) + " MB";
        else if (bytes >= BYTES_IN_KB) return MEM_SIZE_FORMAT.format(bytes / BYTES_IN_KB) + " KB";
        else return bytes + " B";
    }

    public static String memoryDiffToString(long bytes){
        long uBytes = bytes;
        char sign = '+';
        if(uBytes < 0) {
            uBytes = -uBytes;
            sign = '-';
        }
        return sign + memoryToString(uBytes);
    }

    public static String formatTimestamp(Date timestamp){
        return timestamp == null ? "" : TIMESTAMP_FORMAT.format(timestamp);
    }

    public static Date parseTimestamp(String timestamp) throws ParseException {
        return StringUtils.isNullOrEmpty(timestamp) ? null : TIMESTAMP_FORMAT.parse(timestamp);
    }
}
