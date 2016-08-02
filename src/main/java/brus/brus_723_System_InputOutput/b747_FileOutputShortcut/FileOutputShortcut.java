package brus.brus_723_System_InputOutput.b747_FileOutputShortcut;

//: io/FileOutputShortcut.java

import brus.brus_723_System_InputOutput.b743_BufferedInputFile.BufferedInputFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
public class FileOutputShortcut {
    static String file = "src\\main\\java\\brus\\brus_723_System_InputOutput\\b747_FileOutputShortcut\\FileOutputShortcut.out";
    public static void main(String[] args)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("src\\main\\java\\brus\\brus_723_System_InputOutput\\b747_FileOutputShortcut\\FileOutputShortcut.java")));
        // Сокращенная запись:
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null )
            out.println(lineCount++ + ": " + s);
        out.close();
        // Вывод содержимого файла:
        System.out.println(BufferedInputFile.read(file));
    }
} /* (Execute to see output) *///:~