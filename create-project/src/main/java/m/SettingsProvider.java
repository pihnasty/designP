package m;

import settings.EnumSettings;
import settings.Settings;

public class SettingsProvider {

    private final static Settings defaultSettings = DefaultSettings.getInstanceP();

    public static Settings getDefaultSettings() {
        return defaultSettings;
    }

    public static Settings getSettings(EnumSettings settingsType) {
        switch (settingsType) {
//            case GLOBAL:
//                return getGlobalSettings();
//            case CURRENT_PROJECT:
//                return getProjectSettings();
            case DEFAULT:
                return getDefaultSettings();
        }
        return null;
    }
}
