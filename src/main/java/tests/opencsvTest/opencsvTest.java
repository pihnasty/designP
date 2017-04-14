package tests.opencsvTest;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class opencsvTest {
    static public void main (String [] args ) throws IOException {
        readOpencsv();
    }

    private static void readOpencsv() throws IOException {
        String fileName = "E:\\VerPOM\\designP\\src\\main\\java\\tests\\opencsvTest\\column-stats-tera(2).csv";

         //       "C:\\Users\\pihnastyi.o\\AWS Schema Conversion Tool\\Projects\\load-query-history-stat-by-schema\\redshift\\query-stats-rs000_new1.txt";

         //       "C:\\Users\\pihnastyi.o\\AWS Schema Conversion Tool\\Projects\\load-query-history-stat-by-schema\\redshift\\"+"test.txt"; //"query-stats-rs000_2";

        String structureLine = "";
        CSVReader reader = new CSVReader(new FileReader(fileName), ';');
        String [] line = reader.readNext();

        for(int i=0; i<line.length; i++ ) structureLine = structureLine + (0<i ? ";":"") + "\""+ line[i]+ "\"";
        System.out.println("structureLine="+structureLine);


       if (structureLine.contains("\uFEFF")) {
           System.out.println("There is BOM!-1");

           char s_1 = 0xFEFF;
           char s_2 = '0';
 //          structureLine.replace(s_1, s_2);
 //          structureLine.replace("0","");
           System.out.println("s_2="+s_2);
       }

        if (structureLine.contains("\"\uFEFFdatabase_name\"")) {
            System.out.println("There is BOM!-2");
           structureLine=structureLine.substring(0,1)+structureLine.substring(2);



            System.out.println("structureLine="+structureLine);
        }

        String s = "\"database_name\";\"table_name\";\"column_name\";\"cardinality\";\"current_ts\"";

        if(structureLine.equals(s))  {
            System.out.println("structureLine.equals(s) = YES");
        }         else {
            System.out.println("structureLine.equals(s) = NO");
        }


//        String [] nextLine;
////        List myEntries = reader.readAll();
////        System.out.println(myEntries);
//
//        while ((nextLine = reader.readNext()) != null) {
//            // nextLine[] is an array of values from the line
////            System.out.println(nextLine[0] + nextLine[1] + "etc...");
//            System.out.println(nextLine[0]);
//            System.out.println("---------------------->"+ nextLine[1]+"---------------------->"+ nextLine[2]);
//        }


    }
}


class SymbolText {
    public static void main(String[] args) {
        char s_1 = 0x0041;
        String s_2 = "\u0041";

        char BOMSymbol =0xFEFF;

        String s = "\"\uFEFFdatabase_name\"";

        s=s.substring(0,1)+s.substring(2);
        System.out.println(s);

    }
}