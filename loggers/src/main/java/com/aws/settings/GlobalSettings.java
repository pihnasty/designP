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
        defaultValues.put(Keys.MESSAGE_LEVEL_PRINTER, MessageType.INFO);
    }
}
