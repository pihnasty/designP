package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReaderP {
    Reader reader;

    public static void main(String[] args) {
        ReaderP readerP =      new ReaderP();
        readerP.bufferedReaderTest();

    }

    private void bufferedReaderTest() {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader ( System.in)
        );
        System.out.println("W");
        System.out.write('A');
        System.out.println("W");

    }
}
