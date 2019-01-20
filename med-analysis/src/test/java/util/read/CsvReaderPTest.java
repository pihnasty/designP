package util.read;

import jdk.nashorn.internal.runtime.ListAdapter;
import math.MathP;
import org.junit.Test;
import util.write.CsvWriterP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import math.MathP.Counter;

public class CsvReaderPTest {

    @Test
    public void readFromFile() throws IOException {
        CsvReaderP csvReaderP = new CsvReaderP("%8.3f ", ';',"C:\\E\\A\\D02\\tweb\\med-analysis\\src\\main\\java\\data", "t2_2000.csv");
        csvReaderP.readFromFile();
    }

    @Test
    public void readFromFileWriteToFileUpdate() throws IOException {

        Counter counter = MathP.getCounter(1);



        List<String> format =Arrays.asList(
//              ----------------/----------------/----------------/----------------/----------------/----------------/----------------/----------------/----------------/----------------/
//                 0                 1                   2                  3               4               5                   6               7               8                   9
                 " %4s "        ," %5s "        ," %3s "          ," %6s "         ," %17s "        ," %19s "        ," %15s "        ," %12s "        ," %24s "        ," %27s "
                ," %18s "       ," %17s "       ," %7s "          ," %7s "         ," %7s "         ," %7s "         ," %7s "         ," %7s "         ," %5s "         ," %5s "
                ," %4s "        ," %3s "        ," %3s "          ," %3s "         ," %4s "         ," %4s "         ," %4s "         ," %4s "         ," %4s "         ," %4s "
                ," %4s "        ," %2s "        ," %3s "          ," %4s "         ," %3s "         ," %6s "         ," %5s "         ," %9s "         ," %9s "         ," %7s "
                ," %9s "        ," %3s "        ," %5s "          ," %8s "         ," %5s "         ," %6s "         ," %7s "         ," %6s "         ," %8s "         ," %6s "
                ," %9s "        ," %9s "        ," %7s "          ," %7s "         ," %8s "         ," %7s "         ," %5s "         ," %10s "        ," %15s "        ," %7s "
                ," %11s "       ," %8s "        ," %9s "          ," %10s "        ," %9s "         ," %4s "         ," %7s "         ," %4s "         ," %5s "         ," %5s "
                ," %11s "       ," %8s "        ," %7s "          ," %8s "         ," %5s "         ," %7s "         ," %6s "         ," %10s "        ," %6s "         ," %8s "
                ," %6s "        ," %5s "        ," %4s "          ," %9s "         ," %6s "         ," %5s "         ," %4s "         ," %9s "         ," %5s "         ," %3s "
                ," %4s "        ," %3s "        ," %4s "          ," %4s "         ," %5s "         ," %4s "         ," %5s "         ," %5s "         ," %5s "         ," %9s "
                ," %4s "        ," %5s "        ," %10s "         ," %10s "        ," %14s "        ," %14s "        ," %14s "        ," %14s "        ," %5s "         ," %5s "
                ," %5s "        ," %5s "        ," %5s "          ," %5s "         ," %6s "         ," %6s "         ," %6s "         ," %6s "         ," %6s "         ," %6s "
                ," %4s "        ," %4s "        ," %5s "          ," %17s "        ," %17s "        ," %5s "         ," %8s "         ," %10s "        ," %12s "        ," %9s "
                ," %9s "        ," %6s "        ," %6s "
        );






        List<String> header = Arrays.asList(
//              ----------------/----------------/----------------/----------------/----------------/----------------/----------------/----------------/----------------/----------------/
//                 0                 1                   2                  3               4               5                   6               7               8                   9
                "  NN "        ," group "       ," age "          ," gender"       ,"SEVERE PERSISTENT"
                                                                                                   ,"MODERATE PERSISTENT"
                                                                                                                     ,"MILD PERSISTENT","INTERMITTENT" ,"Secondary cardiomyopathy"
                                                                                                                                                                        ,"Defiection of nasal septum"
                ,"Atopic dermatitis"
                               ,"Allergic rhinitis"
                                               ," MedHist "      ," 23.1 BA "     ," 23.1 AR "    ," 23.2 BA "      ," 23.2 AR "     ," 23.4 BA "     ," E "            ," Hb "
                ," Lk "        ," E% "         ," B% "           ," P% "          ," C% "         ," Ln% "          ," M% "          ," SOE "         , " Cm "          ," Pm "
                ," Wm "        ," Rm "         ," Lm "           ," ERm "         ," Slm "        ,"Loboda"         ,"Birch"         ,"Sagebrush"     ,"Sunflower"      ,"Timothy"
                ,"Cocksfoot"   ,"Rye"          ,"Pyrei"          ,"Ryegrass"      ,"Alder"        ,"Fescue"         ,"Walnut"        ,"Linden"        ,"Plantain"       ,"Nettle"
                ,"Dandelion"   ,"Cyclohena"    ,"Foxtail"        ,"Ragweed"       ,"Chestnut"     ,"Cypress"        ,"Maple"         ,"House dust "   ,"Feather pillows","Daphnia"
                ,"Rabbit wool" ,"Wool cat"     ,"Dog's fur"      ,"Sheep wool"    ,"Egg white"    ,"carp"           ,"pollock"       ,"Milk"          ,"Lemon"          ,"Apple"
                ,"Raspberries" ,"Buriak"       ,"Cabbage"        ,"Beef"          ,"Pork"         ,"chicken"        ,"Banana"        ,"Watermelon"    ,"Orange"         ,"Mandarin"
                ,"Grapes"      ,"Cocoa"        ,"Soya"           ,"Black tea"     ,"tomato"       ,"Beans"          ,"Rice"          ,"Buckwheat"     ,"Wheat"          ,"Oat"
                ,"Corn"        ,"Rye"          ,"Lg E"           ,"JEL"           ,"FJEL"         ,"POS"            ,"MOS25"         ,"MOS50"         ,"MOS75"          ,"SOS 25-75"
                ,"OFB1"        ,"TIFFO"        ,"MBL"            ,"leucocyte"     ,"Neutrophils %","Neutrophils T"  ,"Lymphocytes %" ,"Lymphocytes T" ,"SD3 %"          ,"SD3 T"
                ,"SD4 %"       ,"SD4 T"        ,"SD8 %"          ,"SD8 T"         ,"SD16 %"       ,"SD16 T"         ,"SD22 %"        ,"SD22 T"        ,"SD25 %"         ,"SD25 T"
                ,"LgA"         ,"LgM"          ,"LgG"            ,"PhagocytosisWithL"
                                                                                  ,"Phagocytic number"
                                                                                                  ,"СН 50"          ,"ZIK 3.5%"      ,"NST Spont%"    ,"IAN Spont Un"   ,"NST Stim%"
                ,"IAN Stim%"  ,"LCP Un"       ,"TLSP"
        );

        //        List<String> format = new ArrayList<>();
//        header.forEach(f -> format.add(" %18s " ));   format.set(4," %115s ");  format.set(5," %180s ");


        List<String> headerWithFormat = new ArrayList<>();
        header.forEach(e->  headerWithFormat.add(String.format(format.get(counter.get()), e.trim())));

        List<List<String>> tableWithHeader = new ArrayList<>();

        tableWithHeader.add(headerWithFormat);

        Integer stringNumber = 230;
        CsvReaderP csvReaderP = new CsvReaderP("%8.3f ", ';',"C:\\E\\A\\D02\\tweb\\med-analysis\\src\\main\\java\\data", "t2_20"+stringNumber+".csv");


        List<List<String>> table = csvReaderP.readFromFile();
       table.remove(0);

        List<List<String>> tableWithFormat = new ArrayList<>();

        List<String> sum = new ArrayList<>();



        table.forEach(row -> {



          int i=92;    //    row.set(i,row.get(i).trim().replace(""," "));
                      //         column.set(i,column.get(i).trim().isEmpty()?"-":column.get(i));

         //    for(int i=1; i<2;i++)
        //         row.remove(i);

        //    int i2=20;

            String sNew ="0";
            String sOld ="Allergic rhinitis";
            String sOld2 ="тяжелое персистирующее течение";
            String sOld3="средне-тяжелое персистирующее течение";
            String sOld4="частично";


          //     row.set(i,(                        row.get(i).trim().contains(sOld) || row.get(i).trim().contains(sOld2) || row.get(i).trim().contains(sOld3)| row.get(i).trim().contains(sOld4))?sNew :row.get(i));

     //        row.set(i2,(                    row.get(i2).trim().replace(sOld ,"").replace(sOld2 ,"").replace(sOld3 ,"").replace(sOld4 ,"")              ));
                 // summa[0] += Double.parseDouble(row.get(i).replace(",","."));
//            System.out.println(row.get(i)+"     "+summa[0]);

      //      if("".equalsIgnoreCase(row.get(i).trim()) )  row.set(i,"0");
//           if("Норма".equalsIgnoreCase(row.get(117).trim()) )  row.set(117,"0");
//            if("Обструкт.".equalsIgnoreCase(row.get(117).trim()) )  row.set(117,"1");
//
//             row.add(13,row.get(13));
//            row.remove(10);






            Counter counterTable = new Counter(1);
            List<String> columnWithFormat = new ArrayList<>();
            tableWithFormat.add(columnWithFormat);
            row.forEach(
                    cell ->  {
                        int counterCell = counterTable.get();
                        if(counterCell < format.size()) {
                            columnWithFormat.add(String.format(format.get(counterCell), cell.trim()));
                        } else {
                            columnWithFormat.add(cell.trim());
                        }
                    }
            );
        });


        tableWithHeader.addAll(tableWithFormat );

        for (int iColumn = 0; iColumn < format.size(); iColumn++) {
            Double sumCell = 0.0;
            for (int iRow = 0; iRow < tableWithFormat.size(); iRow++) {
                if (tableWithFormat.get(iRow).get(iColumn).trim().isEmpty()
                        || tableWithFormat.get(iRow).get(iColumn).trim().contentEquals("-")) {
                } else {
                    try {
                        Double cell = Double.parseDouble(tableWithFormat.get(iRow).get(iColumn).replace(",","."));
                        sumCell = sumCell + cell;
                    } catch (NumberFormatException e) {

                    }
                }
            }
           sum.add(String.format(" %3.0f ",sumCell  ));

        }

        List<String> sumD = new ArrayList<>();
        Counter counterSum = MathP.getCounter(1);
        sum.forEach(e->
                sumD.add(String.format(format.get(counterSum.get()), e.trim()))
        );

        // Check by summation of data transformation
//         controlExcel(headerWithFormat, tableWithHeader, sumD);

        for (int i =0; i<148; i++)
        System.out.println((i*i+i+1) +"  i="+ i);

        CsvWriterP csvWriterP = new CsvWriterP("%8.3f ", ';',"C:\\E\\A\\D02\\tweb\\med-analysis\\src\\main\\java\\data", "t2_20"+(stringNumber+1)+".csv");
        csvWriterP.writeToFile(  tableWithHeader);


    }

    /**
     * Check by summation of data transformation
     * @param headerWithFormat
     * @param tableWithHeader
     * @param sumD
     */
    private void controlExcel(List<String> headerWithFormat, List<List<String>> tableWithHeader, List<String> sumD) {
        tableWithHeader.add(headerWithFormat);
        tableWithHeader.add(sumD);
    }


    @Test
    public void readFromFileWriteToFile() throws IOException {

        CsvReaderP csvReaderP = new CsvReaderP("%8.3f ", ';',"C:\\E\\A\\D02\\tweb\\med-analysis\\src\\main\\java\\data", "sample_out2018_12_18.csv");

        List<List<String>> table = csvReaderP.readFromFile();

        List<Integer> integerList = new ArrayList<>();
        integerList.add(-3);

        table.forEach(e -> { e.add(integerList.get(0).toString());   integerList.set(0,integerList.get(0) + 1); }    );


        System.out.println();

        CsvWriterP csvWriterP = new CsvWriterP("%8.3f ", ';',"C:\\E\\A\\D02\\tweb\\med-analysis\\src\\main\\java\\data", "sample_out2018_12_18_New.csv");

        csvWriterP.writeToFile( table );

    }


}
