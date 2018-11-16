package com.dbmsys.original.data.generation;

import com.dbmsys.csvapi.io.write.CsvWriterP;
import com.dbmsys.jsonapi.template.rules.Rule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;


public class GeneratorTest {

    String path = "src\\main\\java\\com\\dbmsys\\data";
    //String path = "C:\\Program Files\\DBBest\\DBMsys\\PowerShell\\out\\";
    String fileName = "Dbmsys.2018.10.19.092718501.json.gz";

    @Test
    public void addRowsTest() {

        // Правила, по каким столбцам ищем в head и body. Выбираем те элементы, у которых заголокок сожжержит указаные значения. Если Значение в правиле "", то это означает, что значение может быть любое
        Rule ruleFiltredByHeadByBody = new Rule(
                new HashMap<String, String>() {
                    {
                        put("Address", "vm-dbmsys-stage");
                        put("Started", "");
                    }
                },
                new HashMap<String, String>() {
                    {
                        put("Category", "LogicalDisk");
                        put("Counter", "% Free Space");   //"% Free Space"  "Avg. Disk Bytes/Read"
                        put("Instance", "");
                        put("Value", "");
                    }
                }
        );

        // Правила, как мы разносим столбцы ищем в head и body. Это шаблон заголовка
        ruleFiltredByHeadByBody.setHeaderColumns(new ArrayList<Map<String, String>>() {
            {
                add(new HashMap<String, String>() {
                    {
                        put("Started", "");
                        put(CommonConstants.HeaderAttibute.PRINTED, "Started");
                        put(CommonConstants.HeaderAttibute.HEADER, "Value");
                    }
                });
                add(new HashMap<String, String>() {
                    {
                        put("Address", "vm-dbmsys-stage");
                        put("Category", "LogicalDisk");
                        put("Counter", "% Free Space");   //"% Free Space"
                        put("Instance", "");
                        put("Value", "");
                        put(CommonConstants.HeaderAttibute.PRINTED, "Value");
                        put(CommonConstants.HeaderAttibute.HEADER, "Instance");
                    }
                });
//                add(new HashMap<String, String>() {
//                    {
//                        put("Address", "vm-dbmsys-stage");
//                        put("Category", "LogicalDisk");
//                        put("Counter", "Avg. Disk Bytes/Read");   //"% Free Space"
//                        put("Instance", "");
//                        put("Value", "");
//                        put(CommonConstants.HeaderAttibute.PRINTED, "Value");
//                        put(CommonConstants.HeaderAttibute.HEADER, "Instance");
//                    }
//                });
            }
        });

        String path = //"src\\main\\java\\com\\dbmsys\\data2";
        //"E:\\DBMSYS\\out2018_11_05";
        "E:\\DBMSYS\\out2018_11_16";


        String [] types = {"gz"};
        List<List<String>> table = Generator.getTable(ruleFiltredByHeadByBody, path, types);

        Generator.convertDateToString(table,0);

        String stringFormatforColumn = " %20s ";
        String doubleFormatforColumn = " %20.3f ";

        List<String> stringFormatHeader = Arrays.asList(stringFormatforColumn,stringFormatforColumn,stringFormatforColumn);
        List<String> stringFormatBody = Arrays.asList  (doubleFormatforColumn,doubleFormatforColumn,stringFormatforColumn);

        List<List<String>> modifiedHeaderTable
                = Generator.getTableWithModifiedHeader(
                        table, CommonConstants.HeaderFormatAttibute.FULL,
                stringFormatHeader, stringFormatBody);

        CsvWriterP csvWriterP =  new CsvWriterP( "%8.3f  ", ';', path, "sampleAvg_2018_11_16.csv");
        csvWriterP.writeToFile(modifiedHeaderTable);

    }


}


