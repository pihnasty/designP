package settings;

import java.util.EnumMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumSettingsTest {


    @Test
    public void getInstance() {
        EnumSettings galobal = EnumSettings.GLOBAL;
        Settings settingsActual = galobal.getInstance();

   //     EnumMap<EnumSettings,Settings> e = galobal.getEnumMap();


   //    Map maplist = galobal.map;

        EnumMap enumMap = galobal.enumMap ;

        assertEquals("GLOBAL", settingsActual.getName());

//        galobal = EnumSettings.PROJECT;
//        settingsActual = galobal.getInstance();
//
//        assertEquals("PROJECT", settingsActual.getName());



    }
}
