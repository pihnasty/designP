package tests.core.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerTest {
    public static void main(String[] args) {
       loggerExample();
     //   MyClass myClass = new MyClass();
     //   myClass.myMethod();
    }

    private static void loggerExample() {
        Logger logger = Logger.getLogger("com.mycompany.myprog");
        if  (System.getProperty("java.util.logging.config.class")  == null
            && System.getProperty("java.util.logging.config.file")  == null)
        {
            try
            {
                SimpleFormatter formatter = new SimpleFormatter();



                final int LOG_ROTATION_COUNT = 10;
                Handler handler =
//                    new FileHandler("src\\main\\java\\tests\\core\\logger\\myapp2.log"); //,  0,  LOG_ROTATION_COUNT);
                    new FileHandler("src\\main\\java\\tests\\core\\logger\\myapp2.log",  0,  LOG_ROTATION_COUNT);

                Handler consoleHandler = new ConsoleHandler();
                Level level = Level.INFO;

                logger.setLevel(level);
                logger.setUseParentHandlers(false) ;



                consoleHandler.setLevel(level);
                handler.setLevel(level);



                handler.setFormatter(formatter);

                logger.addHandler(handler);
                logger.addHandler(consoleHandler);

                logger.throwing("Class" ,"This is impotant", new NullPointerException());

                logger.fine("File open dialog canceled");
           //     logger.log(Level.ALL,  "Can’t create log file handler");
           //     Logger.getGlobal( ) . info("File->O pen  menu  item  se le cte d ");

                logger.log(Level.FINE, "HelloPOM");
                logger.log(Level.FINE,  "_______________________________________");
                logger.log(Level.SEVERE,  "____________SEVERE___________________________");

                logger.log(Level.INFO,  "____________INFO___________________________");



              logger.entering("className",  "methodName",100);

          //      logger.warning("logger.warning");
          //      logger.fine("logger.fine");

           //     throw  new NullPointerException();

            }
            catch  (IOException e)
            {
                logger.log(Level.SEVERE,  "Can’t create log file handler",  e);
            }
        }
    }



}


class MyClass {
    public void myMethod() {
        Logger logger = Logger.getLogger("com.mycompany.MyClass");

        // This method should be used when an exception is encounted
        try {
            // Test with an exception
            throw new IOException();
        } catch (Throwable e){
            // Log the exception
          //  logger.log(Level.SEVERE, "Uncaught exception", e);
            // When a method is throwing an exception, this method should be used
            Exception ex = new IllegalStateException();
            logger.throwing(this.getClass().getName(), "myMethod", ex);
        }

    }
}