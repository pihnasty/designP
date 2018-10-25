package com.dbmsys.jsonapi.io;

import org.junit.Test;

public class ReaderTest {
    String path = "src\\main\\java\\com\\dbmsys\\data";
    //String path = "C:\\Program Files\\DBBest\\DBMsys\\PowerShell\\out\\";
    String fileName = "Dbmsys.2018.10.19.092718501.json.gz";
    @Test
    public void readFromGzFileTest() {
        Reader reader = new Reader();
        reader.readFromGzFile(path, fileName);
    }

}
 //D:\A\designP\dbm\src\main\java\com\dbmsys\data\Dbmsys.2018.10.19.092718501.json.gz