package tests.opencsvTest;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class opencsvTest02 {
    static public void main (String [] args ) throws IOException {
        readOpencsv();
    }

    private static void readOpencsv() throws IOException {
        String fileName = "E:\\VerPOM\\designP\\src\\main\\java\\tests\\opencsvTest\\column-stats-tera(2).csv";
     //   String fileName = "E:\\VerPOM\\designP\\src\\main\\java\\tests\\opencsvTest\\EmptyFile.csv"
     //   String fileName = "E:\\VerPOM\\designP\\src\\main\\java\\tests\\opencsvTest\\EmptyFile2withoutBOM.csv";


        String structureLine = "";
        CSVReader reader = new CSVReader(new FileReader(fileName), ';');
        String [] line = reader.readNext();

        if (line[0].contains("\uFEFF")) {
            System.out.println("There is BOM!-1");
        }

        if(line!= null && line[0].contains("\uFEFF") ) line [0] = line[0].substring(1);



        for(int i=0; i<line.length; i++ ) structureLine = structureLine + (0<i ? ";":"") + "\""+ line[i]+ "\"";
        System.out.println("structureLine="+structureLine);


       if (structureLine.contains("\uFEFF")) {
           System.out.println("There is BOM!-2");
       }

        String s = "\"database_name\";\"table_name\";\"column_name\";\"cardinality\";\"current_ts\"";

        if(structureLine.equals(s))  {
            System.out.println("structureLine.equals(s) = YES");
        }         else {
            System.out.println("structureLine.equals(s) = NO");
        }


//        String [] nextLine;
//
//        while ((nextLine = reader.readNext()) != null) {
//            System.out.println(nextLine[0]);
//            System.out.println("---------------------->"+ nextLine[1]+"---------------------->"+ nextLine[2]);
//        }


    }
}