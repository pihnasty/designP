package settings;

import java.util.EnumMap;

/**
 * Interface for access to application settings.
 */
public interface Settings {

    Settings getInstance();
    String getName();
   EnumMap<EnumSettings, Settings> enumMap   = new EnumMap<>(EnumSettings.class);

    //Map<EnumSettings, Settings> map   = new ConcurrentHashMap<>();

    /**
     * reset to default values
     */
//    void resetToDefaultValues();
//
//    /**
//     * Method for putting value associated with this key to map entry
//     *
//     * @param key   name of key
//     * @param value string associated object with this key
//     */
//    void set(String key, Object value);
//
//    /**
//     * Access method to value by key
//     *
//     * @param key name of key
//     * @return value for this key or null if no value for this key
//     */
//    String get(String key);
//
//    /**
//     * Access method to value Object by key stored as Json
//     *
//     * @param key         name of key
//     * @param valueObject class of return Object type
//     * @return value Obkect for this key or null if no value for this key
//     */
//    Object getObject(String key, Class<?> valueObject);
//
//    /**
//     * Save object
//     *
//     * @param key name of key
//     * @param t   Object to save
//     * @param <T>
//     */
//    <T> void setObject(String key, T t);
//
//
//    default String get(String key, String defaultReturnValue) {
//        String v = get(key);
//        return (v == null) ? defaultReturnValue : v;
//    }
//
//    default <T> T get(Class<T> type, String key) {
//        return StringParsers.parse(type, get(key));
//    }
//
//
//    default <T> T get(Class<T> type, String key, T defaultValue) {
//        String value = get(key);
//        try {
//            return StringParsers.parse(type, value, defaultValue);
//        } catch (RuntimeException e) {
//            Logger.GENERAL.writeError(e, "Unable to parse preference %s='%s'.", key, value);
//            return defaultValue;
//        }
//    }
//
//
//    default boolean getBoolean(String key) {
//        String rawValue = get(key);
//        return BooleanUtils.getBoolean(rawValue, false);
//    }
//
//    /**
//     * Gets boolean property value or default value.
//     *
//     * @param key property name to get boolean value for.
//     * @return true if the property exists and equals to "true" , "t", "yes", "y" or "1" ignoring case. Otherwise returns false.
//     */
//    default boolean getBoolean(String key, boolean defaultValue) {
//        String rawValue = get(key);
//        return BooleanUtils.getBoolean(rawValue, defaultValue);
//    }
//
//    default boolean getDebugOption(String key) {
//        return getBoolean(Keys.DEBUG_MODE) && getBoolean(key);
//    }
//
//    void save() throws Exception;
//
//    void load(boolean saveDefaultIfNotExist);
//
//    void addListener(ChangeListener listener);
//
//    void removeListener(ChangeListener listener);
//
//    Map<String, String> getSettingsMap();
//
//    String getDefaultValue(String key);
//
//
//    interface ChangeListener {
//        void onSettingChanged(String settingName, String oldValue, String newValue);
//    }
//
//    /**
//     * Key names used in application for access to values
//     */
//    class Keys {
//        public static final String MESSAGE_LEVEL_PREFIX = "message_level.";
//        public static final String MESSAGE_LEVEL_GENERAL = MESSAGE_LEVEL_PREFIX + "GENERAL";
//        public static final String MESSAGE_LEVEL_LOADER = MESSAGE_LEVEL_PREFIX + "LOADER";
//        public static final String MESSAGE_LEVEL_PRINTER = MESSAGE_LEVEL_PREFIX + "PRINTER";
//
//        public static final String DEBUG_MODE = "debug_mode";
//
//        public static final String DEFAULT_PROJECT_PATH = "project.default_project_path";
//        public static final String PROJECT_FOLDER = "project_folder";
//
//        public static final String EVENT_FILE_NAME = "event.file.name";
//        public static final String TREE_SETTINGS_VIEW = "tree_settings_for_show";
//
//        public static final String LOG_FOLDER_UI = "log_folder";
//
//        public static final String LOG_MAX_FILE_SIZE_MB = "log.max_file_size_mb";
//        public static final String LOG_MAX_FILES_COUNT = "log.max_files_count";
//    }
}
