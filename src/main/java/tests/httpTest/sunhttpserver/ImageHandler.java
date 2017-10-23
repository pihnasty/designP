package tests.httpTest.sunhttpserver;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by pom on 21.03.2017.
 */
public class ImageHandler implements HttpHandler {
    public static final String FOLDER = "folder.img";
    public static final String FILE = "file.img";

    public static final String IMG_TEXT = "file:///C:/D/temp/text.png";
    public static final String IMG_FOLDER = "file:///C:/D/temp/folder.png";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestURI = httpExchange.getRequestURI().toString();
        System.out.println("requestURI="+requestURI);

        String fileName = IMG_TEXT;
        if(requestURI.endsWith(FOLDER)) {
            fileName = IMG_FOLDER;
        }

        httpExchange.sendResponseHeaders(200, new File(fileName).length());
        httpExchange.getRequestHeaders().put("Content-Type", Arrays.asList("image/png"));

        OutputStream out = httpExchange.getResponseBody();

        try {
            Files.copy(Paths.get(new URI(fileName)), out);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        out.flush();

       // httpExchange.close();



    }
}
