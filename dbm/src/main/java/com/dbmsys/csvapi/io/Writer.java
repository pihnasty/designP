package com.dbmsys.csvapi.io;

import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.opencsv.CSVWriter.NO_QUOTE_CHARACTER;

public class Writer {
    public void writeToFile(List<List<String>> table) throws IOException {

        final String STRING_ARRAY_SAMPLE = "E:\\VerPOM\\designP\\dbm\\src\\main\\java\\com\\dbmsys\\data\\sample.csv";
        //using custom delimiter and quote character
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
        CSVWriter csvWriter = new CSVWriter(writer, ';', NO_QUOTE_CHARACTER);



        List<String[]> data = toStringArray(table);

       //data.forEach(item -> csvWriter .writeNext(item));
        csvWriter.writeAll(data);

        csvWriter.close();

        System.out.println(writer);
    }

    private static List<String[]> toStringArray(List<List<String>> table) {
        List<String[]> records = new ArrayList<>();

        List<String> list = table.get(0).stream().map(column -> {
                String[] header = column.split("\n");
                return header[header.length -1];
        }).collect(Collectors.toList());


        table.remove(0);
        table.add(0,list);

        table.forEach( row -> records.add(row.stream().toArray(String[]::new)));

        System.out.println();
        return records;
    }

    public LocalDate convertStringToData() {
//        System.out.printf("now (from Instant): %s%n",
//            LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
//        OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
//        OffsetDateTime lastThursday =
//            offsetDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
//        System.out.printf("The last Thursday in July 2013 is the %sth.%n",
//            lastThursday.getDayOfMonth());
//
//        String input = "";
//        try {
//            DateTimeFormatter formatter =
//                DateTimeFormatter.ofPattern("MMM d yyyy");
//            LocalDate date = LocalDate.parse(input, formatter);
//            System.out.printf("%s%n", date);
//        }
//        catch (DateTimeParseException exc) {
//            System.out.printf("%s is not parsable!%n", input);
//            throw exc;      // Rethrow the exception.
//        }

//        Instant t1=null, t2=null;
//        Duration.between (t1, t2) .toHours();

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 5);

        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
            " months, and " + p.getDays() +
            " days old. (" + p2 + " days total)");


//                String input = "2018-02-16T23:28:01.101324Z";
//        try {
//            DateTimeFormatter formatter =
//                DateTimeFormatter.ofPattern("YYYY-MM-DDTHH:MM:SS.SSSSSSSSSZ");
//            LocalDate date = LocalDate.parse(input, formatter);
//            System.out.printf("%s%n", date);
//        }
//        catch (DateTimeParseException exc) {
//            System.out.printf("%s is not parsable!%n", input);
//            throw exc;      // Rethrow the exception.
//        }

        String date = "2018-10-23T18:47:05.0584024+03:00";
        ZonedDateTime result = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);

        String date2 = "2018-10-23T22:49:05.0584024+03:00";
        ZonedDateTime result2 = ZonedDateTime.parse(date2, DateTimeFormatter.ISO_DATE_TIME);

        result.getHour();

        System.out.println("ZonedDateTime : " + result);

        System.out.println("TimeZone : " + result.getZone());

        LocalDate localDate = result.toLocalDate();

        System.out.println("LocalDate : " + localDate);

        Duration d1  = Duration.ofDays(2);


//        ZonedDateTime now = ZonedDateTime.now();
//        ZonedDateTime oldDate = now.minusDays(1).minusMinutes(10);
        Duration duration = Duration.between(result, result2);
        System.out.println("ISO-8601: " + duration);
        System.out.println("Hours: " + duration.toHours());
        System.out.println("Hours: " + (double)duration.toMinutes()/60/24);



        return today;

    }

}

//2018-10-23T16:46:44.1251737+03

//    @Override
//    public void writeLogToCsv(List<String[]> csvContent, String name, File csvFolder) throws IOException {
//        String fileName = name + new SimpleDateFormat("yyyymmdd-hh-mm-ss").format(new Date()) + ".csv";
//        CSVWriter writer = new CSVWriter(new FileWriter(Paths.get(csvFolder.toString(), fileName).toString()), CSV_SEPARATOR);
//
//        csvContent.forEach(item -> writer.writeNext(item));
//        writer.close();
//    }