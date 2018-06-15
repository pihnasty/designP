package com.aws.settings;

import java.io.File;
import java.util.HashMap;

public class DefaultProjectSettings extends AbstractSettings {
    private static final DefaultProjectSettings instance = new DefaultProjectSettings();
    private final File settingsFile;

    static DefaultProjectSettings getInstance() {
        return instance;
    }

    private DefaultProjectSettings() {
        super("default_project_settings");
        settingsFile = AppPropertiesHolder.getDefaultApplicationPath().resolve("default_project_settings.xml").toFile();
        load(true);
    }

    @Override
    protected File getSettingsFile() {
        return settingsFile;
    }

    @Override
    protected void initDefaultValues() {
        defaultValues = new HashMap<>();
        defaultValues.put(Keys.DEFAULT_PROJECT_PATH, AppPropertiesHolder.getDefaultApplicationPath().resolve("Projects"));
    }

}
