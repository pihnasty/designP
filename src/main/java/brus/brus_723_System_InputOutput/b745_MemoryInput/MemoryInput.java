package brus.brus_723_System_InputOutput.b745_MemoryInput;
//: io/MemoryInput.java

import brus.brus_723_System_InputOutput.b743_BufferedInputFile.BufferedInputFile;

import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
    public static void main(String[] args)
            throws IOException {
        StringReader in = new StringReader(
                BufferedInputFile.read("src\\main\\java\\brus\\brus_723_System_InputOutput\\b745_MemoryInput\\MemoryInput.java"));
        int c;
        while((c = in.read()) != -1)
            System.out.print((char)c);
    }
} /* (Execute to see output) *///:~
