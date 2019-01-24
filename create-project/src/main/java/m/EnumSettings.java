package m;

import settings.Settings;

import java.io.File;
import java.util.Map;

public enum EnumSettings implements Settings {



//    GLOBAL("GLOBAL")
//    , PROJECT("PROJECT")
//    ,
    DEFAULT("DEFAULT");

    private Settings instance;
    private String name;
    private File fileSettings;


    EnumSettings(String name) {
        this.name = name;
        this.instance = this;
        map.put("1","1-a");
    }

    public Settings getInstance() {

        return instance;

    }

    public File getfileSettings() {
        return fileSettings;
    }

    public Map<String,String> getMap() {
        return map;
    }

    public String getName() {
        return name;
    }
}
