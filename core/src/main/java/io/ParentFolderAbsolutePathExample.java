package io;

import java.io.File;

public class ParentFolderAbsolutePathExample {

    private static final String FILENAME = "e:\\abc!\\myDocument.txt";


    public static void main(String[] args) {
        final File file = new File(FILENAME);
        System.out.println("Полный путь к файлу: " + file.getAbsolutePath());
        if (file.exists()) {
            final File parentFolder = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator)));
            System.out.println("Полный путь к родительскому каталогу: " + parentFolder.getAbsolutePath());
        } else {
            System.out.println("Файл не существует.");
        }
    }
}
