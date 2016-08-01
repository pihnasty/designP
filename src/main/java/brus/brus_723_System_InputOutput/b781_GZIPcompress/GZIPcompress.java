package brus.brus_723_System_InputOutput.b781_GZIPcompress;


import java.util.zip.*;
import java.io.*;

public class GZIPcompress {
    public static void main(String[] args)            throws IOException {
        System.out.println(args[0]);
        if(args.length == 0) {
            System.out.println(
                    "Usage: \nGZIPcompress file\n" +
                            "\tUses GZIP compression to compress " +
                            "the file to test.gz");
            System.exit(1);
        }

        String path = "src\\main\\java\\brus\\brus_723_System_InputOutput\\b781_GZIPcompress\\";

        BufferedReader in = new BufferedReader( new FileReader(path+args[0]));    // args[0])
        BufferedOutputStream out = new BufferedOutputStream( new GZIPOutputStream(new FileOutputStream(path+"test.gz")));
        System.out.println("Writing file");
        int c;
        while((c = in.read()) != -1)
            out.write(c);
        in.close();
        out.close();
        System.out.println("Reading file");



        BufferedReader in2 = new BufferedReader( new InputStreamReader(new GZIPInputStream( new FileInputStream(path+"test.gz"))));
        String s;
        while((s = in2.readLine()) != null)
            System.out.println(s);
    }
}
