package com.aws;


import com.aws.listeners.AdvancedFileListener;
import com.aws.listeners.ConsoleListener;
import com.aws.settings.Settings;
import com.aws.settings.SettingsProvider;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.testng.annotations.Test;

import static com.aws.settings.Settings.Keys.LOG_FOLDER_UI;


public class SubLoggerTest {

    private static MessageLevelFilter primaryLogFilter;
    private static AdvancedFileListener fileLogListenerInstance;

    @Test
    public void createA() {
//        LoggerEngine logger = AppInitializer.initLogger(Settings.Keys.LOG_FOLDER_UI, "ui");
        boolean initConsoleLogger = true;
        String logFolderSettingKey = LOG_FOLDER_UI;
        String logFileInfix = "ui";



        try ( LoggerEngine logger = LoggerEngine.getInstance() ) {
            logger.setPrimaryFilter(primaryLogFilter = new MessageLevelFilter());
            if (initConsoleLogger) {
                initConsoleLogListener(logger);
            }

            updateLogFilterSettings();
            initFileLogListener(logger, logFolderSettingKey, logFileInfix);
            SettingsProvider.getGlobalSettings().dumpSettings();

    //        logger.writeEnvironmentInfo();
    //        logger.writeMemoryInfo();
    //        logger.writeLibraryInfo();


            //  LogMessage message =  writeLog(Logger.GENERAL, MessageType.MANDATORY, "Log session finished.");

            //   logger.writeLog(DEBUG, "Hello");

            logger.writeLog(Logger.GENERAL, MessageType.ERROR, "Hello POM+");

            logger.writeLog(Logger.GENERAL, MessageType.INFO, "Hello POM+MessageType.INFO");

        }


    }


    private static void initConsoleLogListener(LoggerEngine loggerEngine) {
        final boolean COLORIZE_CONSOLE_OUTPUT = true;
        ConsoleListener listener = new ConsoleListener();
        listener.setColorizeOutput(COLORIZE_CONSOLE_OUTPUT);
        loggerEngine.addListener(listener);
    }


    private static void updateLogFilterSettings() {
        primaryLogFilter.setDefaultThresholdLevel(MessageType.TRACE);
        updateSubsystemLoggingLevel(Logger.GENERAL, "GENERAL");

    }

    private static void updateSubsystemLoggingLevel(SubsystemLogger subsystem, String settingsKey) {
        try {
            primaryLogFilter.setThresholdLevel(
                subsystem, MessageType.parse(SettingsProvider.getGlobalSettings().get(settingsKey)));
        } catch (Exception e) {
            Logger.GENERAL.writeError(e, "Failed to update logging filter " + settingsKey);
        }
    }
    private static void initFileLogListener(
        LoggerEngine loggerEngine, String logFolderSettingKey, String sessionName) {
        try {
            Settings globalSettings = SettingsProvider.getGlobalSettings();

            Path logFolderPath = Paths.get(globalSettings.get(logFolderSettingKey));
            long maxLogSize = globalSettings.get(Long.class, Settings.Keys.LOG_MAX_FILE_SIZE_MB) * 1024 * 1024;
            int maxLogParts = globalSettings.get(Integer.class, Settings.Keys.LOG_MAX_FILES_COUNT);

            fileLogListenerInstance =
                new AdvancedFileListener(logFolderPath, "aws", sessionName, maxLogSize, maxLogParts);
            //fileLogListenerInstance.setMessageFilter(primaryLogFilter);
            loggerEngine.addListener(fileLogListenerInstance);

            globalSettings.addListener((String propName, String oldValue, String newValue) -> {
                switch (propName) {
                    case Settings.Keys.LOG_MAX_FILE_SIZE_MB:
                        long newMaxSize = Long.parseLong(newValue) * 1024 * 1024;
                        fileLogListenerInstance.setMaxLogPartSize(newMaxSize);
                        break;

                    case Settings.Keys.LOG_MAX_FILES_COUNT:
                        int newMaxParts = Integer.parseInt(newValue);
                        fileLogListenerInstance.setMaxLogPartsCount(newMaxParts);
                        break;

                    case Settings.Keys.DEBUG_MODE:
                        updateLogFilterSettings();
                        break;

                    default:
                        if (propName.equals(logFolderSettingKey)) {
                            Path newLogFolderPath = Paths.get(newValue);
                            try {
                                loggerEngine.waitProcessed();
                                fileLogListenerInstance.setLogFolder(newLogFolderPath);
                            } catch ( IOException | InterruptedException e) {
                                Logger.GENERAL.writeError(e);
                            }
                        } else if (propName.startsWith(Settings.Keys.MESSAGE_LEVEL_PREFIX)) {
                            updateLogFilterSettings();
                            break;
                        }
                        break;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
