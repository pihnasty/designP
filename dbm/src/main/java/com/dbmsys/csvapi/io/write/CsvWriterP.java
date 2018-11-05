package com.dbmsys.csvapi.io.write;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.opencsv.CSVWriter.NO_QUOTE_CHARACTER;

/**
 * http://zetcode.com/articles/opencsv/
 */
public class CsvWriterP extends AbstractWriterP {
    static public void main (String [] args ) throws IOException {

        CsvWriterP p = new CsvWriterP("%8.3f  ",';',"src\\main\\java\\tests\\stream\\write","c2.csv");
        List<Double> list1 = new ArrayList<Double>() {
            {add(1.1);add(2.1);add(3.0);add(4.0);add(5.0);add(6.0);add(7.0);add(8.0);add(9.0);add(10.0);}
        };
        List<Double> list2 = new ArrayList<Double>() {
            {add(10.2);add(20.1);add(30.0);add(40.0);add(50.0);add(60.0);add(70.0);add(80.0);add(90.0);add(100.0);}
        };

        p.setHeader("   ksi    ","  gm      ");
        Locale.setDefault(new Locale("en", "US"));
        p.writeToFile(list1,list2);
        Locale.setDefault(new Locale("ru", "RU"));

    }


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

    @Override
    public void writeToFile(List<Double>... arg) throws IOException {
        Writer w = new FileWriter(getFile());
        try (CSVWriter csv = new CSVWriter(w, getDelimiter(), CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.RFC4180_LINE_END)) {
            csv.writeNext(getHeaderList().toArray(new String[] {}));

            for (int iList = 0; iList < getSizeList(arg); iList++) {
                List<String> row = new ArrayList<>();
                for (int iArg = 0; iArg < arg.length; iArg++) {
                    row.add(
                        String.format(getColumnsFormat(), arg[iArg].get(iList))
                    );
                }
                csv.writeNext(row.toArray(new String[] {}));
            }
        }
    }

    private static List<String[]> toStringArray(List<List<String>> table) {
        List<String[]> records = new ArrayList<>();

        table.forEach( row -> records.add(row.stream().toArray(String[]::new)));

        System.out.println();
        return records;
    }


}

