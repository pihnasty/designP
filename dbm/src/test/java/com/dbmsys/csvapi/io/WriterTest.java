package com.dbmsys.csvapi.io;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class WriterTest {


    @Test
    public void readFromGzFileTest() throws IOException {
        Writer w = new Writer();
        w.writeToFile((List<List<String>>) new Object());
    }
}


