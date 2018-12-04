package com.dbmsys.csvapi.io.write;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
//
public abstract class AbstractWriterP implements WriterP {
    private String  path;
    private String fileName;
    private String columnsFormat = "%8.3f  ";
    private char delimiter;
    private List<String> headerList = new ArrayList<>();
    private Locale localeCurrent;

    public AbstractWriterP(String columnsFormat, char delimiter, String  path, String fileName) {
        this.columnsFormat = columnsFormat;
        this.path = path;
        this.fileName = fileName;
        this.delimiter = delimiter;
        this.localeCurrent = Locale.getDefault();
    }

    @Override
    public String getFullPathToFile() {
        return path+"/"+fileName;
    }

    public String getColumnsFormat() {
        return columnsFormat;
    }

    public void setColumnsFormat(String columnsFormat) {
        this.columnsFormat = columnsFormat;
    }

    public int getSizeList(List<Double>... arg) {
        return arg.length!=0? arg[0].size() : 0;
    }

    public File getFile() {
        return  new File(getFullPathToFile());
    }

    public char getDelimiter() {
        return delimiter;
    }

    public void setHeader (String ...arg) {
        headerList.addAll(Arrays.asList(arg));
    }

    public List<String> getHeaderList() {
        return headerList;
    }

    public Locale getLocaleCurrent() {
        return localeCurrent;
    }
}
