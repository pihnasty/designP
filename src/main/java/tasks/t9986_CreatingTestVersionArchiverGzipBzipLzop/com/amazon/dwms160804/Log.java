package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160804;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void print(String s) {
        System.out.print(String.format("%s ThreadID:%4d => ",dateFormat.format(new Date()), Thread.currentThread().getId()));
        System.out.println(s);

    }

    static void newLine() {
        System.out.println();

    }
}
