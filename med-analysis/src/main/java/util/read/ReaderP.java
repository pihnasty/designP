package util.read;

import java.io.IOException;
import java.util.List;

public interface ReaderP {
    List<List<String>> readFromFile() throws IOException;
    String getFullPathToFile();
}
