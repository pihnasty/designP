package tests.httpTest.simpleservertest;
import com.sun.net.httpserver.*;
import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleServerTest {

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());

        }
    }

    @Test
    public void testSimpleServer() throws IOException, InterruptedException {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 5);
        server.createContext("/test", new MyHandler());

        ExecutorService executor = Executors.newFixedThreadPool(5);
        server.setExecutor(executor);
        server.start();
        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
}
