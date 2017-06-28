package testrecorddl;

import com.sun.jersey.spi.inject.Inject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Pihnastyi.O on 7/5/2016.
 */
public class Log {
    public static Logger logger;
    static {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy hh-mm-ss");
            Date date = new Date();
            boolean append = true;
            FileHandler fh = new FileHandler("src\\log\\recordDDL"+format.format(date)+".log", append);
            //fh.setFormatter(new XMLFormatter());
            fh.setFormatter(new SimpleFormatter());
            logger = Logger.getLogger("recordDDL");
            logger.addHandler(fh);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}


