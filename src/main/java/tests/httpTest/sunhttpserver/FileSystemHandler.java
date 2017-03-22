package tests.httpTest.sunhttpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.sun.org.apache.xml.internal.serialize.Method.HTML;

/**
 * Created by pom on 21.03.2017.
 */
public class FileSystemHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String requestURI = httpExchange.getRequestURI().toString().replace("%20"," ");
        File file = new File("c:"+ requestURI.substring(3));
        byte[] data= null;
        String contextType;
        if(file.isDirectory()) {
    //        data = renderFolder(file);
            contextType = HTML;
        }
        else {
      //      data = renderPage(file);
            contextType = HTML;
        }
        httpExchange.sendResponseHeaders(200,data.length);
        httpExchange.getRequestHeaders().put("Content-Type", Arrays.asList("image/png"));
    }
}
