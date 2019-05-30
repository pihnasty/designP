package jdbcTest.path;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PahthP {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String path = "%20";
        path = URLDecoder.decode(path, "utf-8");
        path = new File(path).getPath();
        System.out.println(path); // prints: c:\foo bar\baz.jpg
    }
}
