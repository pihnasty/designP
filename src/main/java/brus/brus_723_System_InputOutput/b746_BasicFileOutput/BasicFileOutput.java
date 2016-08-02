package brus.brus_723_System_InputOutput.b746_BasicFileOutput;

//: io/BasicFileOutput.java

import brus.brus_723_System_InputOutput.b743_BufferedInputFile.BufferedInputFile;

import java.io.*;

public class BasicFileOutput {
    static String file = "src\\main\\java\\brus\\brus_723_System_InputOutput\\b746_BasicFileOutput\\BasicFileOutput.out";
    public static void main(String[] args)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("src\\main\\java\\brus\\brus_723_System_InputOutput\\b746_BasicFileOutput\\BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null )
            out.println(lineCount++ + ": " + s);
        out.close();
        // Вывод содержимого файла
        System.out.println(BufferedInputFile.read(file));
    }
} /* (Execute to see output) *///:~