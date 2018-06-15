package com.aws.util;

/**
 * Operating system type.
 */
public enum OSType {
    MacOS("mac", "darwin"),
    Windows("win"),
    Linux("nux"),
    Other("generic");

    private static OSType detectedOS;

    private final String[] keys;

    private OSType(String... keys) {
        this.keys = keys;
    }

    private boolean match(String osKey) {
        for (String key : keys) {
            if (osKey.contains(key))
                return true;
        }
        return false;
    }

    public static OSType getOS_Type() {
        if (detectedOS == null)
            detectedOS = getOperatingSystemType(System.getProperty("os.name", Other.keys[0]).toLowerCase());
        return detectedOS;
    }

    private static OSType getOperatingSystemType(String osKey) {
        for (OSType osType : values()) {
            if (osType.match(osKey))
                return osType;
        }
        return Other;
    }
}
