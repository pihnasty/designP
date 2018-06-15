package settings;

import java.io.File;

public enum EnumSettings implements Settings {

    GLOBAL("GLOBAL"), PROJECT("PROJECT"), DEFAULT("DEFAULT");


    private Settings instance;
    private String name;
    private File savedSettingsFile;




    EnumSettings(String name) {
        this.name = name;
        this.instance = this;
        enumMap.put(this,instance);
  //       map.put(this,instance);
    }

    public Settings getInstance() {

        return instance;

    }

    public File getSettingsFile() {
        return savedSettingsFile;
    }

//    public EnumMap<EnumSettings, Settings> getEnumMap() {
//        return enumMap;
//    }

    public String getName() {
        return name;
    }
}
