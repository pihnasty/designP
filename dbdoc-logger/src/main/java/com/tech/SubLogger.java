package com.tech;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SubLogger {
    public static Logger logger;
    static {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            final String DEFAULT_MESSAGE_FORMAT = "%1$s [%2$4d] %3$11s %4$-7s %5$s";



            Date date = new Date();
            boolean append = true;
            FileHandler fh = new FileHandler("E:\\B\\lg\\"+format.format(date)+".log", append);
            //fh.setFormatter(new XMLFormatter());
            fh.setFormatter(new SimpleFormatter());
            logger = Logger.getLogger("recordDDL");
            logger.addHandler(fh);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SubLogger(String loader) {

    }
}
