package tests.httpTest.sunhttpserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import static com.sun.net.httpserver.spi.HttpServerProvider.provider;

/**
 * Created by pom on 21.03.2017.
 */
public class SunHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = provider().createHttpsServer(new InetSocketAddress(80),256);
        server.createContext("/fs/",new FileSystemHandler());
        server.createContext("/img/",new ImageHandler());

    //    server.removeContext("/img/");
        server.setExecutor(Executors.newCachedThreadPool());

        server.start();
    }
}
