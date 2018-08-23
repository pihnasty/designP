package io;

import java.io.PrintWriter;

public class PrintWriterP {
    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        printWriter.println("Hello");

        PrintWriter printWriterFalse = new PrintWriter(System.out,true);
        printWriterFalse.println("Hello2");
    }
}
