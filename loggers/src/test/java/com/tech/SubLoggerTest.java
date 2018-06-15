package com.tech;

import java.util.logging.Logger;
import org.testng.annotations.Test;

public class SubLoggerTest {
    @Test
    public void createLog() {
        Logger subLogger = SubLogger.logger;
        subLogger.info("Hello");
        subLogger.info("Hello");
        subLogger.info("Hello");
        subLogger.info("Hello");
    }

}
