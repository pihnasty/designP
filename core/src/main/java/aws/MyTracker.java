package aws;

import net.snowflake.client.jdbc.internal.joda.time.DateTime;

import java.util.Map;
import java.util.TreeMap;

public class MyTracker {
    DateTime dateTime = new DateTime();

    public static void main(String[] args) {

        DateTime dateTime1 = new DateTime();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DateTime dateTime = new DateTime();
        dateTime.compareTo(dateTime1);

        Map<String, String> map = new TreeMap();

    }
}
