package tests.stream.write;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface WriterP {
    void writeToFile(List<Double>... arg) throws IOException;
    String getFullPathToFile();
}
