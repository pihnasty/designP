package com.dbmsys;

import com.dbmsys.util.Reader;
import org.junit.Test;

public class ReaderTest {
    String path = "src\\main\\java\\com\\dbmsys\\data\\";
    //String path = "C:\\Program Files\\DBBest\\DBMsys\\PowerShell\\out\\";
    String fileName = "Dbmsys.2018.10.19.092718501.json.gz";
    @Test
    public void readGzFilesTest() {
        Reader reader = new Reader();
        reader.readGzFiles(path, fileName);
    }

}
 //D:\A\designP\dbm\src\main\java\com\dbmsys\data\Dbmsys.2018.10.19.092718501.json.gz