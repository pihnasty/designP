package util.write;

import java.io.IOException;
import java.util.List;

public interface WriterP {
    void writeToFile(List<Double>... arg) throws IOException;
    String getFullPathToFile();

}
