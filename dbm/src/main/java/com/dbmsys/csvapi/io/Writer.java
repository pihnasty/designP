package com.dbmsys.csvapi.io;

import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.opencsv.CSVWriter.DEFAULT_QUOTE_CHARACTER;

public class Writer {
    public void writeToFile(List<List<String>> table) throws IOException {

        final String STRING_ARRAY_SAMPLE = "E:\\VerPOM\\designP\\dbm\\src\\main\\java\\com\\dbmsys\\data\\sample.csv";
        //using custom delimiter and quote character
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
        CSVWriter csvWriter = new CSVWriter(writer, ':', DEFAULT_QUOTE_CHARACTER);



        List<String[]> data = toStringArray(table);

       //data.forEach(item -> csvWriter .writeNext(item));
        csvWriter.writeAll(data);

        csvWriter.close();

        System.out.println(writer);
    }

    private static List<String[]> toStringArray(List<List<String>> table) {
        List<String[]> records = new ArrayList<>();

        List<String> list = table.get(0).stream().map(column -> {
                String[] header = column.split("\n");
                return header[header.length -1];
        }).collect(Collectors.toList());


        table.remove(0);
        table.add(0,list);

        table.forEach( row -> records.add(row.stream().toArray(String[]::new)));

        System.out.println();
        return records;
    }

}

//    @Override
//    public void writeLogToCsv(List<String[]> csvContent, String name, File csvFolder) throws IOException {
//        String fileName = name + new SimpleDateFormat("yyyymmdd-hh-mm-ss").format(new Date()) + ".csv";
//        CSVWriter writer = new CSVWriter(new FileWriter(Paths.get(csvFolder.toString(), fileName).toString()), CSV_SEPARATOR);
//
//        csvContent.forEach(item -> writer.writeNext(item));
//        writer.close();
//    }