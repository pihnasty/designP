package brus.brus_723_System_InputOutput.b782_ZipCompress;
//: io/ZipCompress.java
// Использование формата ZIP для сжатия любого
// количества файлов, указанных в командной строке.
// {Параметры. ZipCompress java}
import java.util.zip.*;
import java.io.*;
import java.util.*;

public class ZipCompress {
    public static void main(String[] args)
            throws IOException {
        String path = "src\\main\\java\\brus\\brus_723_System_InputOutput\\b782_ZipCompress\\";

        FileOutputStream f = new FileOutputStream(path + "test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        // No corresponding getComment(), though.
        BufferedWriter bw = new BufferedWriter(    new OutputStreamWriter(out));
        for(String arg : args) {
            arg = path + arg;
            print("Writing file " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while((c = in.read()) != -1)
                bw.write("IIIIII");
            in.close();
            out.flush();
        }
        out.close();





        // Checksum valid only after the file has been closed!
        print("Checksum: " + csum.getChecksum().getValue());
        // Now extract the files:
        print("Reading file");
        FileInputStream fi = new FileInputStream(path+"test.zip");
        CheckedInputStream csumi =  new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while((ze = in2.getNextEntry()) != null) {
            print("Reading file " + ze);
            int x;
            while((x = bis.read()) != -1)
                System.out.write(x);
        }
        if(args.length == 1)
            print("Checksum: " + csumi.getChecksum().getValue());
        bis.close();
        // Alternative way to open and read Zip files:
        ZipFile zf = new ZipFile(path+"test.zip");
        Enumeration e = zf.entries();
        while(e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry)e.nextElement();
            print("File: " + ze2);
            // ... and extract the data as before
        }
    /* if(args.length == 1) */
    }
    public static void print(String s) {
        System.out.println(s);
    }
}



//    BufferedWriter bw = new BufferedWriter(
//            new OutputStreamWriter(
//                    new BufferedOutputStream (
//                            new ZipOutputStream(
//
//                                    new CheckedOutputStream(new FileOutputStream(filepath.toFile()), new Adler32())
//
//
//
//
//                            )
//                    ),
//                    "UTF-8"
//            )
//    );

//    public void readZIP () throws FileNotFoundException {
//        try {
//            BufferedReader rw = new BufferedReader(
//                    new InputStreamReader(
//                            new ZipInputStream(new FileInputStream(filepath.toFile())),
//                            "UTF-8"
//                    )
//            );
//            String s = "";
//            while ( ( s = rw.readLine()) !=null  ) System.out.println(s);
//        } catch (IOException e) {   e.printStackTrace();    }
//
//    }