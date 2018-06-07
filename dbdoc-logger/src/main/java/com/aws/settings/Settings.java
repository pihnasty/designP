package com.aws.settings;

import com.aws.Logger;
import com.aws.util.BooleanUtils;
import com.aws.util.StringParsers;
import java.util.Map;

/**
 * Interface for access to application settings.
 */
public interface Settings {
    /**
     * reset to default values
     */
    void resetToDefaultValues();

    /**
     * Method for putting value associated with this key to map entry
     *
     * @param key   name of key
     * @param value string associated object with this key
     */
    void set(String key, Object value);

    /**
     * Access method to value by key
     *
     * @param key name of key
     * @return value for this key or null if no value for this key
     */
    String get(String key);

    /**
     * Access method to value Object by key stored as Json
     *
     * @param key         name of key
     * @param valueObject class of return Object type
     * @return value Obkect for this key or null if no value for this key
     */
    Object getObject(String key, Class<?> valueObject);

    /**
     * Save object
     *
     * @param key name of key
     * @param t   Object to save
     * @param <T>
     */
    <T> void setObject(String key, T t);

    /**
     * Method returns value by key or default value if no value exist for this key
     *
     * @param key                key of object
     * @param defaultReturnValue default return value
     * @return value by key or default value if no value exist for this key
     */
    default String get(String key, String defaultReturnValue) {
        String v = get(key);
        return (v == null) ? defaultReturnValue : v;
    }

    default <T> T get(Class<T> type, String key) {
        return StringParsers.parse(type, get(key));
    }


    default <T> T get(Class<T> type, String key, T defaultValue) {
        String value = get(key);
        try {
            return StringParsers.parse(type, value, defaultValue);
        } catch (RuntimeException e) {
            Logger.GENERAL.writeError(e, "Unable to parse preference %s='%s'.", key, value);
            return defaultValue;
        }
    }

    // region typed property getters

    /**
     * Gets boolean property value.
     *
     * @param key property name to get boolean value for.
     * @return true if the property exists and equals to "true" , "t", "yes", "y" or "1" ignoring case. Otherwise returns false.
     */
    default boolean getBoolean(String key) {
        String rawValue = get(key);
        return BooleanUtils.getBoolean(rawValue, false);
    }

    /**
     * Gets boolean property value or default value.
     *
     * @param key property name to get boolean value for.
     * @return true if the property exists and equals to "true" , "t", "yes", "y" or "1" ignoring case. Otherwise returns false.
     */
    default boolean getBoolean(String key, boolean defaultValue) {
        String rawValue = get(key);
        return BooleanUtils.getBoolean(rawValue, defaultValue);
    }

    default boolean getDebugOption(String key) {
        return getBoolean(Keys.DEBUG_MODE) && getBoolean(key);
    }

    // endregion

    /**
     * Save settings method. It may be save to file and so on, depended from realization.
     *
     * @throws Exception in case unsuccessful save operation
     */
    void save() throws Exception;

    /**
     * Load settings method. It may be load from file and so on, depended from realization.
     *
     * @throws Exception
     */
    void load(boolean saveDefaultIfNotExist);

    void addListener(ChangeListener listener);

    void removeListener(ChangeListener listener);

    Map<String, String> getSettingsMap();

    String getDefaultValue(String key);

    enum SAVE_REPORT_TO_PDF_MODE {
        ALL,
        FIVE
    }


    interface ChangeListener {
        void onSettingChanged(String settingName, String oldValue, String newValue);
    }

    /**
     * Key names used in application for access to values
     */
    class Keys {
        public static final String DO_NOT_REMIND_FOR_UPDATES = "do_not_remind_for_updates";
        public static final String LICENSE_ACCEPTED_FOR_BUILD = "license_accepted_for_build";
        public static final String WHATSNEW_CONTENT_SERVER_URL = "whatsnew_content_server_url";
        public static final String WHATSNEW_LIST_SERVER_URL = "whatsnew_list_server_url";

        public static final String LOG_FOLDER_UI = "log_folder";
        public static final String LOG_FOLDER_CONSOLE = "console_log_folder";
        public static final String LOG_FOLDER_EXTRACTORS = "log_folder_extractors";
        public static final String LOG_FOLDER_EXTRACTORS_ASK_BEFORE_LOAD = "log_folder_extractors_ask_before_load";


        public static final String LOG_MAX_FILE_SIZE_MB = "log.max_file_size_mb";
        public static final String LOG_MAX_FILES_COUNT = "log.max_files_count";

        public static final String MESSAGE_LEVEL_PREFIX = "message_level.";
        public static final String MESSAGE_LEVEL_GENERAL = MESSAGE_LEVEL_PREFIX + "GENERAL";
        public static final String MESSAGE_LEVEL_LOADER = MESSAGE_LEVEL_PREFIX + "LOADER";
        public static final String MESSAGE_LEVEL_PARSER = MESSAGE_LEVEL_PREFIX + "PARSER";
        public static final String MESSAGE_LEVEL_PRINTER = MESSAGE_LEVEL_PREFIX + "PRINTER";
        public static final String MESSAGE_LEVEL_RESOLVER = MESSAGE_LEVEL_PREFIX + "RESOLVER";
        public static final String MESSAGE_LEVEL_TELEMETRY = MESSAGE_LEVEL_PREFIX + "TELEMETRY";
        public static final String MESSAGE_LEVEL_TRANSFORMER = MESSAGE_LEVEL_PREFIX + "TRANSFORMER";
        public static final String MESSAGE_LEVEL_TYPEMAPPING = MESSAGE_LEVEL_PREFIX + "TYPEMAPPING";
        public static final String MESSAGE_LEVEL_UI = MESSAGE_LEVEL_PREFIX + "UI";
        public static final String MESSAGE_LEVEL_CONTROLLER = MESSAGE_LEVEL_PREFIX + "CONTROLLER";
        public static final String MESSAGE_LEVEL_COMPARE_SCHEMA = MESSAGE_LEVEL_PREFIX + "COMPARE_SCHEMA";

        public static final String DEFAULT_PROJECT_PATH = "project.default_project_path";
        public static final String INSTALL_DIR = "install_dir";
        public static final String APP_FOLDER = "app_folder";
        public static final String PROJECT_FOLDER = "project_folder";
        public static final String DEBUG_MODE = "debug_mode";
        public static final String DEBUG_DISPLAY_ALL_PROPERTIES = "debug.display_all_properties";
        public static final String ORACLE_DRIVER_FILE = "oracle_driver_file";
        public static final String MSSQL_DRIVER_FILE = "mssql_driver_file";
        public static final String MYSQL_DRIVER_FILE = "mysql_driver_file";
        public static final String POSTGRESQL_DRIVER_FILE = "postgresql_driver_file";
        public static final String NETEZZA_DRIVER_FILE = "netezza_driver_file";
        public static final String GREENPLUM_DRIVER_FILE = "greenplum_driver_file";
        public static final String TERADATA_FIRST_DRIVER_FILE = "teradata_first_driver_file";
        public static final String TERADATA_SECOND_DRIVER_FILE = "teradata_second_driver_file";
        public static final String REDSHIFT_DRIVER_FILE = "redshift_driver_file";
        public static final String VERTICA_DRIVER_FILE = "vertica_driver_file";
        public static final String MSSQLDW_DRIVER_FILE = "mssqldw_driver_file";
        public static final String DB2LUW_DRIVER_FILE = "db2luw_driver_file";
        public static final String SHOW_SEVERITY_LEVEL_IN_SQL = "show_severity_level_in_sql";
        public static final String PARSER_CLEANUP_MODE = "parser.cleanup_mode";
        public static final String REPORTING_MODE = "reporting.mode";
        public static final String EXT_PACK_ALERT = "ext_pack_version_alert";
        public static final String SHOW_HIDE_SCHEMA_ALERT = "show_hide_schema_alert";
        public static final String HIDE_SCHEMA_ON_GLOBAL_LEVEL = "hide_schema_on_global_level";
        public static final String STRATEGY = "strategy";
        public static final String CONVERTING_SETTINGS = "converting_settings";
        public static final String TO_CHAR_FUNCTION_ORACLE = "to_char_function_oracle";
        public static final String TO_NUMBER = "to_number";
        public static final String TO_TIME_ZONE = "to_time_zone";
        public static final String ALTER_SEQUENCE_RESTART = "alert_sequence_restart";
        public static final String UNIQUE_INDEX_GENERATION = "unique_index_generation";
        public static final String TO_DATE_FUNCTION_ORACLE = "to_date_function_oracle";
        public static final String GENERATE_ROW_ID = "generate_row_id";
        public static final String CONVERT_WITHOUT_STATISTIC_REMINDER = "convert_without_statistic_reminder";
        public static final String PROJECT_ENVIRONMENT = "project_environment";
        public static final String SORTKEY_COLUMNS_LIMIT = "sortkey_columns_limit";
        public static final String SKEWED_MAX_VALUE = "skewed_max_value";
        public static final String CASE_SENSITIVITY_NAMES = "case_sensitivity_names";
        public static final String AVOID_CASTING_TO_LOWER_CASE = "avoid_casting_to_lower_case";
        public static final String DATA_MIGRATION_SETTINGS = "data_migration_settings";
        public static final String MIGRATION_STRATEGY_TYPE = "migration_strategy_type";
        public static final String MIGRATION_STRATEGY_DO_NOT_REMIND_ME = "migration_strategy_do_not_remind_me";
        public static final String SAVE_SQL_MODE = "save.sql.mode";
        public static final String SECURITY_CERTIFICATE = "security.certificate";
        public static final String STAT_QUERY_HISTORY_TOP = "stat.query.history.top";
        public static final String EVENT_FILE_NAME = "event.file.name";
        public static final String USER_SELECTION_SETTINGS = "user.selection.settings";
        public static final String EXT_PACK_SPATIAL = "ext_pack_spatial";
        public static final String EXT_PACK_VERSION_REPLACE = "ext_pack_version_replace";
        public static final String TREE_SETTINGS_VIEW = "tree_settings_for_show";
        public static final String DATETIME_FORMATS_EQUAL = "datetime_formats_equal";
        public static final String NUMBER_FORMATS_EQUAL = "number_formats_equal";
        public static final String LEAD_LAG_FUNCTIONS_EQUAL = "lead_lag_functions_equal";
        public static final String MATERIALIZED_VIEW_CONVERT = "materialized_view_convert";
        public static final String STORE_CONNECTION_PASSWORDS = "store_password";
        public static final String FROM_DATE = "from_date";
        public static final String COMPRESSION_FORMAT = "compression_format";
        public static final String SORTING_STRATEGY = "sorting_strategy";
        public static final String LOB_FOLDER = "lob_folder";
        public static final String EXTRACT_LOBS = "extract_lobs";
        public static final String COPY_UTF = "copy_utf";
        public static final String COPY_UTF_SYMBOL = "copy_utf_symbol";
        public static final String DELIMETER_EXTRACTOR = "delimeter_extractor";
        public static final String USE_BLANK_AS_NULL = "use_blank_as_null";
        public static final String USE_EMPTY_AS_NULL = "use_empty_as_null";
        public static final String TRUNCATE_COLUMNS = "truncate_columns";
        public static final String AUTOMATIC_COMPRESSION = "automatic_compression";
        public static final String MAX_ERROR_COUNT = "max_error_count";
        public static final String CHECK_FILE_BEFORE_LOAD = "check_file_before_load";
        public static final String AUTOMATIC_STATISTIC_REFRESH = "automatic_statistic_refresh";

        public static final String SQL_CACHE_LARGE_VALUE_THRESHOLD = "sql_cache.large_value_threshold";
        public static final String SQL_CACHE_MRU_SIZE = "sql_cache.mru_size";

        //sql scripts
        public static final String USE_CUSTOM_EXTENSIONS = "use_custom_extensions";
        public static final String EXTENSIONS_LIST = "extensions_list";

        public static final String SHOW_ALL_FILES = "show-all-files";
        public static final String SHOWED_FILES_COUNT = "showed-files-count";
    }
}
