package com.dbmsys.csvapi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

public class Manager {
    public static List<String> getFilesFrom(String path,  String[] types) {
        Path p1 = Paths.get(path);
        if (Files.notExists(p1)) {
            try {
                throw new IOException("The specified directory does not exist");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<File> files = (List<File>) FileUtils.listFiles(new File(path), types , true);
        return files.stream().map(file -> file.getName()).sorted().collect(Collectors.toList());
    }
}
