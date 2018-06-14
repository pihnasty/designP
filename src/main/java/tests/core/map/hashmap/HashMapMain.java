package tests.core.map.hashmap;

import java.util.HashMap;

public class HashMapMain {
    public static void main(String[] args) {
        HashMapCommon hashMapCommon = new HashMapCommon();

        System.out.println(hashMapCommon.getAuthenticationType("  "));
    }

}

class HashMapCommon {
    public String getAuthenticationType(String authenticationType) {
        return new HashMap<String, String>() {
            {
                put("PASSWORD", "BY user_password");
                put("GLOBAL", "GLOBALLY");
                put("EXTERNAL", "EXTERNALLY");
            }
        }.get(authenticationType);
    }
}