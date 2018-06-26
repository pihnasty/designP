package tests.stream.write;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PrintWriterP extends AbstractWriterP {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriterP p = new PrintWriterP("%8.3f  ",';', "src\\main\\java\\tests\\stream\\write","t2.txt");
        List<Double> list1 = new ArrayList<Double>() {
            {add(1.1);add(2.1);add(3.0);add(4.0);add(5.0);add(6.0);add(7.0);add(8.0);add(9.0);add(10.0);}
        };
        List<Double> list2 = new ArrayList<Double>() {
            {add(10.2);add(20.1);add(30.0);add(40.0);add(50.0);add(60.0);add(70.0);add(80.0);add(90.0);add(100.0);}
        };

        p.setHeader("   list1  ","  list2  ");
        p.writeToFile(list1,list2);

    }

    public PrintWriterP(String stringFormat, char delimiter, String  path, String fileName) {
        super(stringFormat,delimiter,path,fileName);
    }

    @Override
    public void writeToFile(List<Double>... arg) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(getFile())) {

            for (String  columnHeader : getHeaderList()) {
                pw.print( columnHeader );
                if (columnHeader != getHeaderList().get(getHeaderList().size() - 1)) {
                    pw.print(getDelimiter());
                } else pw.println();
            }

            for (int iList=0; iList<getSizeList(arg); iList++) {
                for (int iArg=0; iArg<arg.length;iArg++) {
                    pw.printf( getColumnsFormat() , arg[iArg].get(iList) );
                    if (iArg<arg.length-1)  {
                        pw.print(getDelimiter());
                    } else  pw.println();
                }
            }
        }
    }
}