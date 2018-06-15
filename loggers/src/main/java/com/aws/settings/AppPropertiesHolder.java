package com.aws.settings;

import com.aws.Logger;
import com.aws.util.LoggerUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppPropertiesHolder {
    public static final String DEFAULT_APPLICATION_FOLDER = "AWS Schema Conversion Tool";
    public static final String AWS_APP_CONFIGURATION_FOLDER = ".aws";
    private static final Path DEFAULT_APPLICATION_PATH = Paths.get(System.getProperty("user.home"), DEFAULT_APPLICATION_FOLDER);
    private static final String SECURITY_FOLDER = "Security";
    private static final Path DEFAULT_SECURITY_PATH = Paths.get(DEFAULT_APPLICATION_PATH.toString(), SECURITY_FOLDER);

    private static String APPLICATION_VERSION = "1.0";
    private static String SUPPORTED_BUILD_VERSION = "1.0.200";
    private static String version = "1.0";
    private static long build = 1404;
    private static String specificationName = "AWS Schema Conversion Tool ";

    private static final String DEFAULT_PROPERTIES = "default.properties";
    private static String mixedSEEDKeyWord;

    static {
        Properties defaultProperties = new Properties();
        try {
            defaultProperties.load(com.amazon.sct.common.AppPropertiesHolder.class.getResourceAsStream(DEFAULT_PROPERTIES));
            LoggerUtils.setMemoryCheckPeriodMillis(Long.parseLong(defaultProperties.getProperty("memory_check_period")));
            LoggerUtils.setRemainMemoryPercent(Long.parseLong(defaultProperties.getProperty("remain_memory_percent")));
            LoggerUtils.setMemoryEventGenerationPeriodMillis(Long.parseLong(defaultProperties.getProperty("memory_event_generation_period")));
            APPLICATION_VERSION = defaultProperties.getProperty("version");
            SUPPORTED_BUILD_VERSION = defaultProperties.getProperty("supported_version");
        } catch (IOException e) {
            Logger.GENERAL.writeDebug("Failed to initialize AppPropertiesHolder due to " + e);
        }
    }



    public static String getApplicationVersion() {
        return getVersion() + "." + getBuild();
    }

    public static String getSupportedBuildVersion() {
        return SUPPORTED_BUILD_VERSION;
    }

    /**
     * Getter for property 'applicationName'.
     *
     * @return Value for property 'applicationName'.
     */
    public static Path getDefaultApplicationPath() {
        return DEFAULT_APPLICATION_PATH;
    }

    public static String getSpecificationName() {
        return specificationName;
    }

    public static void setSpecificationName(String specificationName) {
        AppPropertiesHolder.specificationName = specificationName;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        AppPropertiesHolder.version = version;
    }

    public static long getBuild() {
        return build;
    }

    public static void setBuild(long build) {
        AppPropertiesHolder.build = build;
    }

    public static String getMixedSEEDKeyWord() {
        return mixedSEEDKeyWord;
    }
}
