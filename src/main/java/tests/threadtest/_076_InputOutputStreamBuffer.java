package tests.threadtest;

import java.io.*;

/**
 * Created by Max on 14.01.2017.
 */
public class _076_InputOutputStreamBuffer {
    public static void main(String[] args) throws IOException {
        String fileFromName = "D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_072_M\\151_001.lcd";
        String fileToName = "D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_072_M\\151_001To5.lcd";
        InputStream in = null;
        OutputStream out = null;
        int k ;
         for (k=1; k<100000;  k=k*2) {
            try {
                in = new BufferedInputStream(
                        new FileInputStream(fileFromName),k);
                out = new BufferedOutputStream(
                        new FileOutputStream(fileToName),k);
                Long startTime = System.currentTimeMillis();
                System.out.println(startTime);
                //   copy(in, out,10000);
                copy(in, out);
                System.out.println("---" + (System.currentTimeMillis() - startTime));
            } catch (IOException e) {
                throw new IOException("Copy from " + e);
            } finally {
                closeInFile(in);
                closeOutFile(out);
            }
        }
    }

    private static void copyIntivity(InputStream in, OutputStream out) throws IOException {
        byte [] buff = new byte[64 * 1024];
        int count;
        while ( (count = in.read(buff)) !=-1 )
            out.write(buff,0,count);
    }
    private static void copy(InputStream in, OutputStream out, int bufferSize) throws IOException {
        byte [] buff = new byte[bufferSize];
        int count;
        while ( (count = in.read(buff)) !=-1 )
            out.write(buff,0,count);
    }
    private static void copy(InputStream in, OutputStream out) throws IOException {
        int oneByte;
        while ( (oneByte = in.read()) !=-1 )
            out.write(oneByte);
    }
    private static void closeInFile(InputStream inFile) {
        try {
            inFile.close();
        } catch (IOException e) {
            // NOP
        }
    }
    private static void closeOutFile(OutputStream outFile) {
        try {
            outFile.flush();
        } catch (IOException e) {   /*NOP*/   }
        try {
            outFile.close();
        } catch (IOException e) {   /*NOP*/   }
    }
}
