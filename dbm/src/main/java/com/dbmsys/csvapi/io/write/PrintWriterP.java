package com.dbmsys.csvapi.io.write;

import com.sun.javafx.binding.StringFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class PrintWriterP {
    public StringFormatter stringFormatter;
    public String  path;
    public String fileName;
    private String stringFormat = "%8.3f  : ";


    public PrintWriterP(String stringFormat, String  path, String fileName) {
        this.stringFormat = stringFormat;
        this.path = path;
        this.fileName = fileName;



    }

    public void writeToFile(List<Double>... arg) {
        Integer sizeArg = arg.length;
        Integer sizeList = 0;
        if (sizeArg!=0) {
            sizeList = arg[0].size();
        }
        File f = new File(path+"/"+fileName);
        try {
            PrintWriter pw = new PrintWriter(f);


            for (int iList=0; iList<sizeList; iList++) {
                for (int iArg=0; iArg<sizeArg;iArg++) {
                    pw.printf(stringFormat , arg[iArg].get(iList) );
                    if (iArg<sizeArg-1) pw.printf(";");
                }
                   pw.println();
            }
            pw.println();
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}


