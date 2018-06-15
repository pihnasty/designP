package com.aws.util;

public class LoggerUtils {
    private static long MEMORY_CHECK_PERIOD_MILLIS = 2000;
    private static long REMAIN_MEMORY_PERCENT = 20;
    private static long MEMORY_EVENT_GENERATION_PERIOD_MILLIS = 60000;

    public static void setMemoryCheckPeriodMillis(long value){
        MEMORY_CHECK_PERIOD_MILLIS=value;
    }

    public static void setRemainMemoryPercent(long value){
        REMAIN_MEMORY_PERCENT=value;
    }

    public static void setMemoryEventGenerationPeriodMillis(long value){
        MEMORY_EVENT_GENERATION_PERIOD_MILLIS=value;
    }
    public static long getMemoryCheckPeriodMillis(){
        return MEMORY_CHECK_PERIOD_MILLIS;
    }

    public static long getRemainMemoryPercent(){
        return REMAIN_MEMORY_PERCENT;
    }

    public static long getMemoryEventGenerationPeriodMillis(){
        return MEMORY_EVENT_GENERATION_PERIOD_MILLIS;
    }
}
