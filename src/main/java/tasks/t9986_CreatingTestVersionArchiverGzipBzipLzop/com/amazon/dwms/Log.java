package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Log {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateFormat logFileSuffixFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static PrintStream out = System.out;



    static void write(String format, Object... args) {
        out.format("%s ThreadID:%4d => ",dateFormat.format(new Date()), Thread.currentThread().getId()).format(format, args).println();

    }

    static void write(Throwable e) {
        out.format("%s ThreadID:%4d => ",dateFormat.format(new Date()), Thread.currentThread().getId()).format("%s",e.getMessage()).println();
        e.printStackTrace(out);

    }


    static void newLine() {
        out.println();

    }

    static void init(Path folder) {
        try {
            Path filepath = folder.resolve("log_"+logFileSuffixFormat.format(new Date())+".log");
            out = new PrintStream(filepath.toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void close() {
        if (out != System.out)
            out.close();
    }

}
