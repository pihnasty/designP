package com.dbmsys.csvapi.io;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    public void writeToFile(String path, String fileName) {
        StringWriter writer = new StringWriter();

        //using custom delimiter and quote character
        CSVWriter csvWriter = new CSVWriter(writer, '#', '\'');

        List<Employee> emps = null;
        try {
            emps = OpenCSVParseToBeanExample.parseCSVWithHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> data = toStringArray(emps);

        csvWriter.writeAll(data);

    //    csvWriter.close();

        System.out.println(writer);
    }

    private static List<String[]> toStringArray(List<Employee> emps) {
        List<String[]> records = new ArrayList<String[]>();

        // adding header record
        records.add(new String[] { "ID", "Name", "Age", "Country" });

//        Iterator<Employee> it = emps.iterator();
//        while (it.hasNext()) {
//            Employee emp = it.next();
//            records.add(new String[] { emp.getId(), emp.getName(), emp.getAge(), emp.getCountry() });
//        }
        return records;
    }

}

class OpenCSVParseToBeanExample {

    public static void main(String[] args) throws IOException {

        CSVReader reader = new CSVReader(new FileReader("emps.csv"), ',');

        ColumnPositionMappingStrategy<Employee> beanStrategy = new ColumnPositionMappingStrategy<Employee>();
        beanStrategy.setType(Employee.class);
        beanStrategy.setColumnMapping(new String[] {"id","name","age","country"});

        CsvToBean<Employee> csvToBean = new CsvToBean<Employee>();

        List<Employee> emps = csvToBean.parse(beanStrategy, reader);

        System.out.println(emps);

    }

    public static List<Employee> parseCSVWithHeader() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("emps1.csv"), ',');

        HeaderColumnNameMappingStrategy<Employee> beanStrategy = new HeaderColumnNameMappingStrategy<Employee>();
        beanStrategy.setType(Employee.class);

        CsvToBean<Employee> csvToBean = new CsvToBean<Employee>();
        List<Employee> emps = csvToBean.parse(beanStrategy, reader);

        System.out.println(emps);
        reader.close();

        return emps;
    }
}

class Employee {

    private String id;
    private String name;
    private String age;
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{" + id + "::" + name + "::" + age + "::" + country + "}";
    }
}