package com.dbmsys.csvapi.util;

import org.junit.Test;

public class ManagerTest {

    String path = "src\\main\\java\\com\\dbmsys\\data2";
    //String path = "C:\\Program Files\\DBBest\\DBMsys\\PowerShell\\out\\";
    String fileName = "Dbmsys.2018.10.19.092718501.json.gz";
    String [] types = {"gz"};

    @Test
    public void readFromGzFileTest() {
        Manager.getFilesFrom(path, types);
        System.out.println();
    }

}
