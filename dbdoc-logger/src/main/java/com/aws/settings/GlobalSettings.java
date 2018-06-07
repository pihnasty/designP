package com.aws.settings;

import com.aws.MessageType;
import java.io.File;
import java.util.HashMap;

public class GlobalSettings extends AbstractSettings {
    private static final String GLOBAL_SETTINGS_FILE_NAME = "settings.xml";
    private static final GlobalSettings instance = new GlobalSettings();
    private final File savedSettingsFile;


    private GlobalSettings() {
        super("global_settings");
        savedSettingsFile = AppPropertiesHolder.getDefaultApplicationPath().resolve(GLOBAL_SETTINGS_FILE_NAME).toFile();
        load(true);
    }

    static GlobalSettings getInstance() {
        return instance;
    }

    @Override
    protected File getSettingsFile() {
        return savedSettingsFile;
    }

    @Override
    protected void initDefaultValues() {
   //     final String USER_HOME = System.getProperty("user.home");
        final String USER_HOME = "ProjectDir";
        defaultValues = new HashMap<>();
        defaultValues.put(Keys.MESSAGE_LEVEL_GENERAL, MessageType.INFO);
        defaultValues.put(Keys.MESSAGE_LEVEL_LOADER, MessageType.INFO);
        defaultValues.put(Keys.MESSAGE_LEVEL_PARSER, MessageType.INFO);


        defaultValues.put(Keys.WHATSNEW_CONTENT_SERVER_URL, "https://{0}.cloudfront.net/{1}/{2}/whatsNew.html");
        defaultValues.put(Keys.WHATSNEW_LIST_SERVER_URL, "https://{0}.cloudfront.net/?versionNumber={1}&buildNumber={2}");
        defaultValues.put(Keys.LOG_FOLDER_UI, AppPropertiesHolder.getDefaultApplicationPath().resolve("Log"));
        defaultValues.put(Keys.LOG_FOLDER_CONSOLE, AppPropertiesHolder.getDefaultApplicationPath().resolve("Log"));
        defaultValues.put(Keys.LOG_FOLDER_EXTRACTORS, AppPropertiesHolder.getDefaultApplicationPath().resolve("Extractor Log"));
        defaultValues.put(Keys.LOG_FOLDER_EXTRACTORS_ASK_BEFORE_LOAD, false);
        defaultValues.put(Keys.STORE_CONNECTION_PASSWORDS, false);
        defaultValues.put(Keys.LOG_MAX_FILE_SIZE_MB, 100);
        defaultValues.put(Keys.LOG_MAX_FILES_COUNT, 20);
        defaultValues.put(Keys.DEFAULT_PROJECT_PATH, AppPropertiesHolder.getDefaultApplicationPath().resolve("Projects"));
        defaultValues.put(Keys.APP_FOLDER, AppPropertiesHolder.DEFAULT_APPLICATION_FOLDER);
        defaultValues.put(Keys.INSTALL_DIR, USER_HOME);
        defaultValues.put(Keys.PROJECT_FOLDER, "Projects");
        defaultValues.put(Keys.DEBUG_MODE, false);
        defaultValues.put(Keys.ORACLE_DRIVER_FILE, "");
        defaultValues.put(Keys.MSSQL_DRIVER_FILE, "");
        defaultValues.put(Keys.MYSQL_DRIVER_FILE, "");
        defaultValues.put(Keys.POSTGRESQL_DRIVER_FILE, "");
        defaultValues.put(Keys.GREENPLUM_DRIVER_FILE, "");
        defaultValues.put(Keys.TERADATA_FIRST_DRIVER_FILE, "");
        defaultValues.put(Keys.TERADATA_SECOND_DRIVER_FILE, "");
        defaultValues.put(Keys.REDSHIFT_DRIVER_FILE, "");
        defaultValues.put(Keys.NETEZZA_DRIVER_FILE, "");
        defaultValues.put(Keys.GREENPLUM_DRIVER_FILE, "");
        defaultValues.put(Keys.VERTICA_DRIVER_FILE, "");
        defaultValues.put(Keys.DB2LUW_DRIVER_FILE, "");
        defaultValues.put(Keys.MSSQLDW_DRIVER_FILE, "");
        defaultValues.put(Keys.CONVERT_WITHOUT_STATISTIC_REMINDER, false);
        defaultValues.put(Keys.DATA_MIGRATION_SETTINGS, "");
        defaultValues.put(Keys.SECURITY_CERTIFICATE, "");
        defaultValues.put(Keys.EXT_PACK_ALERT, true);
        defaultValues.put(Keys.SHOW_HIDE_SCHEMA_ALERT, true);
        defaultValues.put(Keys.SQL_CACHE_LARGE_VALUE_THRESHOLD, 1024);
        defaultValues.put(Keys.SQL_CACHE_MRU_SIZE, 10);
        defaultValues.put(Keys.SHOW_ALL_FILES, Boolean.FALSE.toString());
        defaultValues.put(Keys.SHOWED_FILES_COUNT, "5");
    }
}
