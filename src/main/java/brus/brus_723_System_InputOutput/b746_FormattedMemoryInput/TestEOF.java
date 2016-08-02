package brus.brus_723_System_InputOutput.b746_FormattedMemoryInput;

//: io/TestEOF.java
// Проверка достижения конца файла одновременно
// с чтением из него по байту.
import java.io.*;
public class TestEOF {
    public static void main(String[] args)
            throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("src\\main\\java\\brus\\brus_723_System_InputOutput\\b746_FormattedMemoryInput\\TestEOF.java")));
        while(in.available() != 0)
            System.out.print((char)in.readByte());
    }
} /* (Execute to see output) *///:~