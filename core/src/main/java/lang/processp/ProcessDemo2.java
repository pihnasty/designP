package lang.processp;

import java.util.concurrent.TimeUnit;

public class ProcessDemo2 {

    public static void main(String[] args) throws Exception {

        Runtime r = Runtime.getRuntime();
        Process p = r.exec("C:\\Users\\pihnastyi.o\\Desktop\\2017.01.14_New_Years_corporate_party");
        p.waitFor(10, TimeUnit.SECONDS);
        p.destroy();
    }
}
