package com.dbmsys.csvapi.io.converter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ConverterP {
    public double convertStringToData() {
        String date = "2018-10-23T18:47:05.0584024+03:00";
        ZonedDateTime result = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);

        String date2 = "2018-10-23T22:49:05.0584024+03:00";
        ZonedDateTime result2 = ZonedDateTime.parse(date2, DateTimeFormatter.ISO_DATE_TIME);

        Duration duration = Duration.between(result, result2);
        System.out.println("ISO-8601: " + duration);
        System.out.println("Hours: " + duration.toHours());
        System.out.println("Hours: " + (double)duration.toMinutes()/60/24);



        return 2.0;

    }
}
