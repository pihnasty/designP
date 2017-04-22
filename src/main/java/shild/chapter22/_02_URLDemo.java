package shild.chapter22;

//listing 3
// Demonstrate URL.
        import java.net.*;
class _02_URLDemo {
    public static void main(String args[]) throws MalformedURLException {
        URL hp = new URL("https://e.mail.ru/messages/inbox");

        System.out.println("Protocol: " + hp.getProtocol());
        System.out.println("Port: " + hp.getPort());
        System.out.println("Host: " + hp.getHost());
        System.out.println("File: " + hp.getFile());
        System.out.println("Ext:" + hp.toExternalForm());
    }
}
