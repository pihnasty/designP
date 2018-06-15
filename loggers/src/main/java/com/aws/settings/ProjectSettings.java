package com.aws.settings;

import java.io.File;
import java.nio.file.FileSystems;
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
        defaultValues.put(Keys.EVENT_FILE_NAME, "");
        refreshDefaultValues();
    }

    private void refreshDefaultValues() {
        defaultValues.put(Keys.TREE_SETTINGS_VIEW, SettingsProvider.getGlobalSettings().get(Settings.Keys.TREE_SETTINGS_VIEW));
    }
}
