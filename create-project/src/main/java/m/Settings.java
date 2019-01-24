package m;

import java.util.HashMap;
import java.util.Map;

public interface Settings {

    Map<String, String> map = new HashMap<>();

    String getName();

    settings.Settings getInstance();

    Map<String, String> getMap();

}

