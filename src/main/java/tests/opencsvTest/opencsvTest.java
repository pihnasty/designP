package tests.opencsvTest;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class opencsvTest {
    static public void main (String [] args ) throws IOException {
        readOpencsv();
    }

    private static void readOpencsv() throws IOException {
        String fileName ="C:\\Users\\pihnastyi.o\\AWS Schema Conversion Tool\\Projects\\load-query-history-stat-by-schema\\redshift\\query-stats-rs000_new1.txt";
         //       "C:\\Users\\pihnastyi.o\\AWS Schema Conversion Tool\\Projects\\load-query-history-stat-by-schema\\redshift\\"+"test.txt"; //"query-stats-rs000_2";

        String structureLine = "";
        CSVReader reader = new CSVReader(new FileReader(fileName), ';');
        String [] line = reader.readNext();
        for(int i=0; i<line.length; i++ ) structureLine = structureLine + (0<i ? ";":"") + "\""+ line[i]+ "\"";
        System.out.println("structureLine="+structureLine);

        String [] nextLine;
//        List myEntries = reader.readAll();
//        System.out.println(myEntries);

        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
//            System.out.println(nextLine[0] + nextLine[1] + "etc...");
            System.out.println(nextLine[0]);
            System.out.println("---------------------->"+ nextLine[1]+"---------------------->"+ nextLine[2]);
        }


    }
}
