package com.aws.settings;

import java.io.File;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ProjectSettings extends AbstractSettings {
    private static final String SAVED_SETTINGS_FILENAME = "project_settings.xml";

    private static final ProjectSettings instance = new ProjectSettings();


    /**
     * Static initializer
     *
     * @return the object this class
     */
    public static ProjectSettings getInstance() {
        return instance;
    }

    public ProjectSettings() {
        super("project_settings");
        initDefaultValues();
        resetToDefaultValues();
    }

    @Override
    protected File getSettingsFile() {
        String projectFolder = get(Keys.PROJECT_FOLDER);
        if (projectFolder == null)
            return null;
        return FileSystems.getDefault().getPath(projectFolder, SAVED_SETTINGS_FILENAME).toFile();
    }

    //TODO remove override (if clear settings than Keys.PROJECT_FOLDER will be null and settings couldn't be loaded)
    @Override
    public void resetToDefaultValues() {
        refreshDefaultValues();
        for (Map.Entry<String, Object> entry : getDefaultValues().entrySet()) {
            setValueInternal(entry.getKey(), entry.getValue());
        }
    }

    @Override
    protected void initDefaultValues() {
        //SIC! DO NOT clear, just overwrite! //super.resetToDefaultValues();
        defaultValues = new HashMap<>();
        defaultValues.put(Settings.Keys.SHOW_SEVERITY_LEVEL_IN_SQL, SeverityType.CRITICAL);
        defaultValues.put(Settings.Keys.GENERATE_ROW_ID, false);
        defaultValues.put(Keys.DATETIME_FORMATS_EQUAL, false);
        defaultValues.put(Keys.NUMBER_FORMATS_EQUAL, false);
        defaultValues.put(Keys.LEAD_LAG_FUNCTIONS_EQUAL, false);
        defaultValues.put(Keys.CONVERT_WITHOUT_STATISTIC_REMINDER, false);

        defaultValues.put(Keys.CONVERTING_SETTINGS, false);
        defaultValues.put(Keys.TO_CHAR_FUNCTION_ORACLE, false);
        defaultValues.put(Keys.TO_NUMBER, false);
        defaultValues.put(Keys.TO_TIME_ZONE, false);
        defaultValues.put(Keys.ALTER_SEQUENCE_RESTART, false);
        defaultValues.put(Keys.UNIQUE_INDEX_GENERATION, true);
        defaultValues.put(Keys.TO_DATE_FUNCTION_ORACLE, false);

        defaultValues.put(Keys.MIGRATION_STRATEGY_DO_NOT_REMIND_ME, false);


        defaultValues.put(Keys.STAT_QUERY_HISTORY_TOP, 50);
        defaultValues.put(Keys.EVENT_FILE_NAME, "");


        defaultValues.put(Settings.Keys.FROM_DATE, LocalDate.of(1900, 1, 1));
        defaultValues.put(Settings.Keys.LOB_FOLDER, "");
        defaultValues.put(Settings.Keys.COPY_UTF, true);
        defaultValues.put(Settings.Keys.EXTRACT_LOBS, true);
        defaultValues.put(Settings.Keys.COPY_UTF_SYMBOL, "?");
        defaultValues.put(Keys.DELIMETER_EXTRACTOR, "|");
        defaultValues.put(Settings.Keys.USE_BLANK_AS_NULL, true);
        defaultValues.put(Settings.Keys.USE_EMPTY_AS_NULL, true);
        defaultValues.put(Settings.Keys.TRUNCATE_COLUMNS, false);
        defaultValues.put(Settings.Keys.AUTOMATIC_COMPRESSION, false);
        defaultValues.put(Settings.Keys.MAX_ERROR_COUNT, "0");
        defaultValues.put(Settings.Keys.CHECK_FILE_BEFORE_LOAD, true);
        defaultValues.put(Settings.Keys.AUTOMATIC_STATISTIC_REFRESH, false);


        //sql script
        defaultValues.put(Keys.USE_CUSTOM_EXTENSIONS, false);
        defaultValues.put(Keys.EXTENSIONS_LIST, "sql");


        refreshDefaultValues();
    }

    private void refreshDefaultValues() {
        defaultValues.put(Keys.TREE_SETTINGS_VIEW, SettingsProvider.getGlobalSettings().get(Settings.Keys.TREE_SETTINGS_VIEW));
    }
}
