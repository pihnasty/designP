package m;

import settings.EnumSettings;
import settings.Settings;

public class DefaultSettings extends AbstractSettings {


    public static Settings getInstanceP() {
        return EnumSettings.DEFAULT;
    }
    public String getName() {
        return EnumSettings.DEFAULT.getName();
    }
//    public Map<String, String> getMap() {
//        return EnumSettings.DEFAULT.getMap();
//    }
}
