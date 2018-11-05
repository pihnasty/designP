package com.dbmsys.csvapi.io;

import java.io.IOException;
import java.util.List;

import com.dbmsys.csvapi.io.write.Writer;
import org.junit.Test;

public class WriterTest {

    Writer w = new Writer();

    @Test
    public void readFromGzFileTest() throws IOException {
        w.writeToFile((List<List<String>>) new Object());
    }

    @Test
    public void convertStringToDataTest() throws IOException {

        w.convertStringToData();

    }
}


