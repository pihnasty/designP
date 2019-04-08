package io;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class readWriteCsv {
    public static void main(String[] args) throws IOException {
        //Build reader instance
        //Read data.csv
        //Default seperator is comma
        //Default quote character is double quote
        //Start reading from line number 2 (line numbers start from zero)
//        CSVReader reader = new CSVReader(new FileReader("E:\\DBMSYS\\out2019_01_28\\sample_out2019_01_28.csv"), ';' , CSVWriter.NO_QUOTE_CHARACTER, 0);
//        CSVWriter writer = new CSVWriter(new FileWriter("E:\\DBMSYS\\out2019_01_28\\sample_out2019_01_28_change.csv"),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.RFC4180_LINE_END);

        CSVReader reader = new CSVReader(new FileReader("E:\\DBMSYS\\out2019_02_26\\sample_out2019_02_26.csv"), ';' , CSVWriter.NO_QUOTE_CHARACTER, CSVReader.DEFAULT_SKIP_LINES);
        CSVWriter writer = new CSVWriter(new FileWriter("E:\\DBMSYS\\out2019_02_26\\sample_out2019_02_26_change.csv"),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.RFC4180_LINE_END);



        //Read CSV line by line and use the string array as you want
        String[] nextLine;

        String[] writeLine =  new String [7];

        Integer i = -3;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here

                writeLine[0]=String.format(" %5s",i.toString());
                writeLine[1]=nextLine[0];
                writeLine[2]=nextLine[1];
                writeLine[3]=nextLine[2];
                writeLine[4]=nextLine[3];
                writeLine[5]=nextLine[4];
                writeLine[6]=nextLine[5];

                i++;
                System.out.println(Arrays.toString(nextLine));
                writer.writeNext(writeLine);
            }
        }

        reader.close();
        writer.close();
    }
}
