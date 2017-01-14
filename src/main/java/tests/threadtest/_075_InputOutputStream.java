package tests.threadtest;

import java.io.*;
import java.util.stream.Stream;

/**
 * Created by Max on 14.01.2017.
 */
public class _075_InputOutputStream {
    public static void main(String[] args) throws IOException {
        String fileFromName = "D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_072_M\\151_001.lcd";
        String fileToName = "D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_072_M\\151_001To.lcd";
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(fileFromName);
            out = new FileOutputStream(fileToName);
            copy(in, out);
        } catch (IOException e) {
            throw new IOException("Copy from "+e);
        }
        finally {
            closeInFile(in);
            closeOutFile(out);

        }

    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte [] buff = new byte[64 * 1024];
        int count;
        while ( (count = in.read(buff)) !=-1 )
            out.write(buff,0,count);
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
