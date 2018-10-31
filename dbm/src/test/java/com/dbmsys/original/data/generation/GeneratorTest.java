package com.dbmsys.original.data.generation;

import com.dbmsys.csvapi.io.Writer;
import com.dbmsys.jsonapi.template.rules.Rule;
import java.io.IOException;
import java.util.ArrayList;
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
                        put("Counter", "% Free Space");
                        put("Instance", "");
                        put("Value", "");
                    }
                }
        );


        ruleFiltredByHeadByBody.setHeaderColumns(new ArrayList<Map<String, String>>() {
            {
                add(new HashMap<String, String>() {
                    {
                        put("Started", "");
                        put(CommonConstants.HeaderAttibute.PRINTED, "Started");
                        put(CommonConstants.HeaderAttibute.HEADER, "ValueStarted");
                    }
                });
                add(new HashMap<String, String>() {
                    {
                        put("Address", "vm-dbmsys-stage");
                        put("Category", "LogicalDisk");
                        put("Counter", "% Free Space");
                        put("Instance", "");
                        put("Value", "");
                        put(CommonConstants.HeaderAttibute.PRINTED, "Value");
                        put(CommonConstants.HeaderAttibute.HEADER, "Instance");
                    }
                });
            }
        });


        String path = "src\\main\\java\\com\\dbmsys\\data3";
        String [] types = {"gz"};
        List<List<String>> table = Generator.gerTable(ruleFiltredByHeadByBody, path, types);

        Writer writerCSV = new Writer();

        try {
            writerCSV.writeToFile(table);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


