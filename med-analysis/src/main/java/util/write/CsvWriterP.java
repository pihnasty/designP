package util.write;

import com.opencsv.CSVWriter;
import util.AbstractCsvP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * http://zetcode.com/articles/opencsv/
 */
public class CsvWriterP extends AbstractCsvP {

    public CsvWriterP(String stringFormat, char delimiter, String  path, String fileName) {
        super(stringFormat,delimiter,path,fileName);
    }


    public void writeToFile(List<List<String>> table)  {
        BufferedWriter writer = null;

        try {
            writer = Files.newBufferedWriter(Paths.get( getFullPathToFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVWriter csvWriter = new CSVWriter(writer, getDelimiter(), CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.RFC4180_LINE_END)) {
            List<String[]> data = toStringArray(table);
            csvWriter.writeAll(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> toStringArray(List<List<String>> table) {
        List<String[]> records = new ArrayList<>();

        table.forEach( row -> records.add(row.stream().toArray(String[]::new)));

        System.out.println();
        return records;
    }

    @Override
    public List<List<String>> readFromFile() throws IOException {
        return null;
    }
}

