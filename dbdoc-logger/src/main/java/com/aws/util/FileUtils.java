package com.aws.util;

import com.aws.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class FileUtils extends org.apache.commons.io.FileUtils{

    public static final String FS=System.getProperty("file.separator");

    public static ObservableList<String> getKewords(InputStream is) {
        ObservableList<String> result = FXCollections.observableArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.add(line.trim());
            }
        } catch (IOException e) {
            Logger.GENERAL.writeError(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                Logger.GENERAL.writeError(e);
            }
        }
        return result;
    }

    public static void writeAllText(String fileName, String text) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName, "utf8")) {
            out.print(text);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String readAllText(String fileName) {
        try {
            Path path = FileSystems.getDefault().getPath(fileName);
            byte[] bytes = Files.readAllBytes(path);
            if (bytes.length >= 3 && bytes[0] == (byte)0xEF && bytes[1] == (byte)0xBB && bytes[2] == (byte)0xBF) // UTF8 BOM present
                return new String(bytes, 3, bytes.length - 3, StandardCharsets.UTF_8);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Create directory if this path is correct and this folder is not exist
     * @param dirPath folder path
     * @throws IOException  if an I/O error occurs
     */
    public static void checkAndCreateDir(Path dirPath) throws IOException {
        if (Files.notExists(dirPath)) {
           Files.createDirectories(dirPath);
        }
    }

    /**
     * Create file if path is correct and this file is not exist
     * @param filePath file path
     * @throws IOException if an I/O error occurs
     */
    public static void checkAndCreateFile(String filePath) throws IOException {
        checkAndCreateFile(Paths.get(filePath));
    }

    /**
     * Create file if path is correct and this file is not exist
     * @param filePath file path
     * @throws IOException if an I/O error occurs
     */
    public static void checkAndCreateFile(Path filePath) throws IOException {
        if(Files.notExists(filePath)) {
            if(Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.createFile(filePath);
        }
    }

    /**
     * Ensures that the specified file exists.
     * @param path path to check existence of.
     * @throws FileNotFoundException if the file with specified path does not exist.
     */
    public static void ensureFileExists(String path) throws FileNotFoundException {
        Path filePath = Paths.get(path).toAbsolutePath();
        if(!Files.exists(filePath))
            throw new FileNotFoundException("Path does not exist: " + filePath);
    }

    /**
     *
     * @param winPath win path
     * @return default file system path
     */
    public static String getPath(String winPath) {
        OSType osType = OSType.getOS_Type();
        if(osType.equals(OSType.Linux)) {
            //TODO calculate file system root
            return getPath(winPath, "/");
        }
        return winPath;
    }

    /**
     *
     * @param winPath win path
     * @param root root for relative path in default file system
     * @return default file system path
     */
    private static String getPath(String winPath, String root){
        String [] winParts = winPath.split("\\\\");
        String[] parts = new String[winParts.length - 1];
        System.arraycopy(winParts, 1, parts, 0, parts.length);
        return Paths.get(root, parts).toString();
    }

    /**
     * Finds file by string mask
     * @param folder
     * @param fileName
     * @return
     */
    public static File findFileByName(String folder, String fileName) {
        File vfolder = new File(folder);
        if (!vfolder.exists() || !vfolder.isDirectory()) {
            return null;
        }
        Path pathTns = Paths.get(folder);
        File[] resultFiles = null;
        if (pathTns != null && Files.exists(pathTns) && Files.isDirectory(pathTns)) {
            resultFiles = new File(folder).listFiles((dir, name) ->
                    name.equalsIgnoreCase(fileName));
        }
        return resultFiles != null && resultFiles.length != 0 ? resultFiles[0] : null;
    }

    public static void removeDirectoryRecursive(Path path) throws IOException {
        Files.walk(path, FileVisitOption.FOLLOW_LINKS)
             .sorted(Comparator.reverseOrder())
             .map(Path::toFile)
             .forEach(File::delete);
    }

    public static Writer createTextWriter(File file) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        return new BufferedWriter(osw);
    }

    public static Writer createTextWriter(String file) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        return new BufferedWriter(osw);
    }

}
