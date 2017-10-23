//      ONLY JAVA 8


//package tasks.t10727_Support_SSL_for_GreenPlum;
//
//import javax.net.ssl.SSLSocket;
//import javax.net.ssl.SSLSocketFactory;
//import java.io.*;
//import java.security.Security;
//
//public class HTTPSClient {
//    public static void main(String[] args) {
//
//
//        socketssl("gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com",3306);
//
//
//
//
//    }
//
//    private static  void socketssl(String host, int port ) {
//    //    int port = 3306;
//    //    String host = "gbmymssql.cjj06khxetlc.us-west-2.rds.amazonaws.com";
//
//        try{
//            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//
//            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
//
//            System.out.println("----------------------------"+socket.getSession());
//
//            Writer out = new OutputStreamWriter(socket.getOutputStream());
//            // https requires the full URL in the GET line
//            out.write("GETTP/1.0\\r\\\n");
//            out.write("\\r\\n");		out.flush();
//
//                    // read response
//                    BufferedReader in = new BufferedReader(  new InputStreamReader(socket.getInputStream()));
//            int c;
//            while ((c = in.read()) != -1) {
//                System.out.write(c);
//            }
//
//            out.close();
//            in.close();
//            socket.close();
//        }catch (IOException e) {        System.err.println(e);       }
//    }
//}
