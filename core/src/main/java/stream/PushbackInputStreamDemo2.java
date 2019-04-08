package stream;

import java.io.*;

public class PushbackInputStreamDemo2 {
    public static void main(String[] args) {

        // declare a buffer and initialize its size:
        byte[] arrByte = new byte[1024];

        // create an array for our message
        byte[] byteArray = new byte[]{'H', 'e', 'l', 'l', 'o',};

        // create object of PushbackInputStream class for specified stream
        InputStream is = new ByteArrayInputStream(byteArray);
        PushbackInputStream pis = new PushbackInputStream(is, 10);

        try {
            // read from the buffer one character at a time
            for (int i = 0; i < byteArray.length; i++) {

                // read a char into our array
                arrByte[i] = (byte) pis.read();

                // display the read byte
                System.out.print((char) arrByte[i]);
            }

            // change line
            System.out.println();

            // create a new byte array to be unread
            byte[] b = {'W', 'o', 'r', 'l', 'd'};

            // unread the byte array
            pis.unread(b);

            // read again from the buffer one character at a time
            for (int i = 0; i < byteArray.length; i++) {

                // read a char into our array
                arrByte[i] = (byte) pis.read();

                // display the read byte
                System.out.print((char) arrByte[i]);
            }

            byte[] b1 = {'1', '2', '3', '4', '5'};
            pis.unread(b1);

            // read again from the buffer one character at a time
            for (int i = 0; i < byteArray.length; i++) {

                // read a char into our array
                arrByte[i] = (byte) pis.read();

                // display the read byte
                System.out.print((char) arrByte[i]);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

