package tests.threadtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Max on 13.01.2017.
 */
public class _072_InputStream {
    public static void main(String [] args) throws IOException {
        //streamInRead();
        streamInReadBuffer();
    }

    private static void streamInRead() throws IOException {
        InputStream inFile = null;
        try {
            String path = "D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_072_M\\151_001.lcd";
            inFile = new FileInputStream(path);
            int oneByte;
            while((oneByte= inFile.read() )!= -1) {
                System.out.print(oneByte);

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        finally {
            inFile.close();
        }
    }
    private static void streamInReadBuffer()  {
        String path = "D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_072_M\\151_001.lcd";


        InputStream inFile = null;
        try {
            inFile = readInputStream(path, inFile);
        } catch (IOException ignore) {
            ignore.printStackTrace();
        } finally {
            closeFile(inFile);
        }
    }

    private static InputStream readInputStream(String path, InputStream inFile) throws IOException {
        inFile = new FileInputStream(path);
        int count;
        byte[] buff = new byte[300];
        while ((count = inFile.read(buff)) !=-1) {
            System.out.println(new String(buff, 0, count, "UTF8"));
        }
        return inFile;
    }

    private static void closeFile(InputStream inFile) {
        try {
            inFile.close();
        } catch (IOException e) {
           // e.printStackTrace();
        }
    }
}
