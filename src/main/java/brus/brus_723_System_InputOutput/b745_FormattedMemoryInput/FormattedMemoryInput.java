package brus.brus_723_System_InputOutput.b745_FormattedMemoryInput;

//: io/FormattedMemoryInput.java

import brus.brus_723_System_InputOutput.b743_BufferedInputFile.BufferedInputFile;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class FormattedMemoryInput {
    public static void main(String[] args)
            throws IOException {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read(
                                    "src\\main\\java\\brus\\brus_723_System_InputOutput\\b745_FormattedMemoryInput\\FormattedMemoryInput.java").getBytes()));
            while(true)
                System.out.print((char)in.readByte());
        } catch(EOFException e) {
            System.err.println("End of stream");
        }
    }
} /* (Execute to see output) *///:~