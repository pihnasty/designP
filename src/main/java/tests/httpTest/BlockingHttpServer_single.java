package tests.httpTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pihnastyi.O on 3/20/2017.
 */
public class BlockingHttpServer_single {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(82,256);

        ExecutorService executor = new ThreadPoolExecutor(0,100,60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());

        while (true) {
            System.out.println(0);
            Socket socket = serverSocket.accept();



            executor.submit(new BlockingHttpHandler(socket));  // если пул потоков
            //  или   new BlockingHttpHandler(socket).call();  для одного потока


            System.out.println(1);
            System.out.println(socket.getLocalPort());

        //   serverSocket.close();
        }

//        Socket socket = serverSocket.accept();
//        System.out.println(socket.getLocalPort());
        //serverSocket.close();

//  в командной строке (cmd)
//  $netstat -ano
//  $taskkill -pid 10860 /f

//  #http://localhost:80


    }
}
