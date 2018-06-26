package tests.stream.write;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
        p.writeToFile(list1,list2);

    }


    public CsvWriterP(String stringFormat, char delimiter, String  path, String fileName) {
        super(stringFormat,delimiter,path,fileName);
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
}

