package tests.httpTest;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pom on 20.03.2017.
 */
public class HttpClientUtilsP {
    public static String readREquest (InputStream inputStream) throws IOException {
        String httpHeader ="";
        while (!httpHeader.endsWith("\r\n\r\n")) {
            httpHeader += (char) inputStream.read();
        }
        return httpHeader;
    }
}
