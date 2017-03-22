package tests.httpTest;




import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by pom on 20.03.2017.
 */
public class BlockingHttpHandler implements Callable<Void> {
    private final Socket socket;
    public BlockingHttpHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Void call() throws Exception {
        String httpRequest = HttpClientUtilsP.readREquest(socket.getInputStream());
        System.out.println("---------------------------" +socket.getInputStream().toString());
        System.out.println(httpRequest);

        return null;
    }
}
