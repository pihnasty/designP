package tests.httpTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Pihnastyi.O on 3/20/2017.
 */
public class BlockingHttpServer_single {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80,256);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getLocalPort());
            serverSocket.close();
        }

//        Socket socket = serverSocket.accept();
//        System.out.println(socket.getLocalPort());
        //serverSocket.close();

//  в командной строке (cmd)
//  $netstat -ano
//  $taskkill -pid 10860 /f


    }
}
