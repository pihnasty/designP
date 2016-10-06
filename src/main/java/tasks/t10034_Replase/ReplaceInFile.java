package tasks.t10034_Replase;

import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;

public class ReplaceInFile {
    public static void main(String[] args) throws IOException {
        String fileName = "E:/VerPOM/designP/src/main/java/tasks/t10034_Replase/file.txt";
        String search = "31415";
        String replace = "число ПИ";
        Charset charset = StandardCharsets.UTF_8;
        Path path = Paths.get(fileName);
        Files.write(path, new String(Files.readAllBytes(path), charset).replace(search, replace).getBytes(charset));
    }
}