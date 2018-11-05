package com.dbmsys.original.data.generation;

import com.dbmsys.csvapi.util.Manager;
import com.dbmsys.jsonapi.io.Reader;
import com.dbmsys.jsonapi.template.data.Body;
import com.dbmsys.jsonapi.template.data.DmsSysElement;
import com.dbmsys.jsonapi.template.rules.Rule;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Generator {

    private final String NOTNAME ="\nnot name";
    private final String NOTVALUE ="\nnot value";

    public List<DmsSysElement> getFiltredData (List<DmsSysElement> dmsSysElements, Rule rule) {


        List<DmsSysElement> filtredByHeadDmsSysElements = dmsSysElements.stream().filter(dmsSysElement -> {
            long filtredCount =
                    rule.getFilterHead().entrySet().stream().filter(
                            entry -> {
                                if (entry.getValue().isEmpty()) {
                                    return dmsSysElement.getHead().getParametrs().keySet().contains(entry.getKey());
                                } else {
                                    return dmsSysElement.getHead().getParametrs().entrySet().contains(entry);
                                }
                            }
                    ).count();

            return filtredCount == rule.getFilterHead().size();
        }).collect(toList());


        List<DmsSysElement> filtredByBodyDmsSysElements = filtredByHeadDmsSysElements.stream().map(
                dmsSysElement -> {
                    dmsSysElement.setBodies(

                            Arrays.asList(
                                    dmsSysElement.getBodies()
                            ).stream().filter(
                                    body -> {

                                        long filtredCount =
                                                rule.getFilterBody().entrySet().stream().filter(
                                                        entry -> {
                                                            if (entry.getValue().isEmpty()) {
                                                                return body.getParametrs().keySet().contains(entry.getKey());
                                                            } else {
                                                                return body.getParametrs().entrySet().contains(entry);
                                                            }
                                                        }
                                                ).count();
                                        return filtredCount == rule.getFilterBody().size();

                                    }
                            ).toArray(Body[]::new)

                    );
                    return dmsSysElement;
                }
        ).collect(toList());


        List<DmsSysElement> filtredNonEmptyBodiesDmsSysElements =
                filtredByBodyDmsSysElements.stream().filter(dmsSysElement -> dmsSysElement.getBodies().length!=0).collect(toList());

        return filtredNonEmptyBodiesDmsSysElements;
    }

    public List<String> getRow(List<DmsSysElement> dmsSysElements, Rule rule) {
        Set<String> headParameterNames = getHeadParameterNames(dmsSysElements);
        Set<String> bodyParameterNames = getBodyParametersNames(dmsSysElements);
        Set<String> parameterNames = getParameterNames(headParameterNames, bodyParameterNames);
        List<String> headerColumnString = new ArrayList<>();

        rule.getHeaderColumns().stream().forEach(
                colunmMap -> {
                    if (colunmMap.entrySet().size() == 3) {
                        OneColumnRowValue(headerColumnString, colunmMap, dmsSysElements);
                    } else {


                        List<StringBuilder> fullNameColumn = new ArrayList<>();
                        fullNameColumn.add(new StringBuilder());

                        colunmMap.entrySet().stream()
                                .filter(entry -> !colunmMap.get(CommonConstants.HeaderAttibute.HEADER).equalsIgnoreCase(entry.getKey()))
                                .filter(entry -> !CommonConstants.HeaderAttibute.PRINTED.equalsIgnoreCase(entry.getKey()))
                                .filter(entry -> !CommonConstants.HeaderAttibute.HEADER.equalsIgnoreCase(entry.getKey()))
                                .forEach(
                                        entryFiltered -> {
                                            if (parameterNames.contains(entryFiltered.getKey())) {
                                                if (entryFiltered.getValue().isEmpty()) {


                                                    boolean isHeadParameter = headParameterNames.contains(entryFiltered);

                                                    if (isHeadParameter) {


                                                        dmsSysElements.stream().filter(element -> element.getHead().getParametrs().containsKey(entryFiltered.getKey()))
                                                                .map(filtredElement -> filtredElement.getHead().getParametrs().values()).collect(toList());

                                                    } else {
                                                        dmsSysElements.stream().forEach(
                                                                element -> {
                                                                    boolean[] isCreited = new boolean[1];
                                                                    isCreited[0] = false;
                                                                    List<StringBuilder> fullNameColumnTemp = new ArrayList<>();
                                                                    Arrays.stream(element.getBodies()).forEach(
                                                                            body -> {
                                                                                String value = body.getParametrs().get(entryFiltered.getKey());
                                                                                if (Objects.nonNull(value) && !value.isEmpty()) {
                                                                                    if (!isCreited[0]) {
                                                                                        fullNameColumn.forEach(fullName -> fullNameColumnTemp.add(new StringBuilder(fullName.toString())));
                                                                                        isCreited[0] = true;
                                                                                        fullNameColumn.forEach(stringBuilder -> stringBuilder.append(value));
                                                                                    } else {
                                                                                        List<StringBuilder> fullNameColumnTemp2 = new ArrayList<>();
                                                                                        fullNameColumnTemp.forEach(fullName -> fullNameColumnTemp2.add(new StringBuilder(fullName.toString())));
                                                                                        fullNameColumnTemp2.forEach(stringBuilder -> stringBuilder.append(value));
                                                                                        fullNameColumn.addAll(fullNameColumnTemp2);
                                                                                    }
                                                                                }
                                                                            }
                                                                    );
                                                                }
                                                        );
                                                    }
                                                }
                                            }
                                        }
                                );

                        fullNameColumn.forEach(fullName -> headerColumnString.add(fullName.toString()));
                    }
                }
        );
        return headerColumnString;
    }

    public List<String> getHeaderColumns(List<DmsSysElement> dmsSysElements, Rule rule) {
        Set<String> headParameterNames = getHeadParameterNames(dmsSysElements);
        Set<String> bodyParameterNames = getBodyParametersNames(dmsSysElements);
        Set<String> parameterNames = getParameterNames(headParameterNames, bodyParameterNames);
        List<String> headerColumnString = new ArrayList<>();

        rule.getHeaderColumns().stream().forEach(
                colunmMap -> {
                    if (colunmMap.entrySet().size() == 3) {
                        OneColumnRowHeader(parameterNames, headerColumnString, colunmMap);
                    } else {
                        List<StringBuilder> fullNameColumn = new ArrayList<>();
                        fullNameColumn.add(new StringBuilder());
                        colunmMap.entrySet().stream()
                                .filter(entry -> !colunmMap.get(CommonConstants.HeaderAttibute.PRINTED).equalsIgnoreCase(entry.getKey()))
                                .filter(entry -> !CommonConstants.HeaderAttibute.PRINTED.equalsIgnoreCase(entry.getKey()))
                                .filter(entry -> !CommonConstants.HeaderAttibute.HEADER.equalsIgnoreCase(entry.getKey()))
                                .forEach(
                                        entryFiltered -> {
                                            if (parameterNames.contains(entryFiltered.getKey())) {
                                                if (entryFiltered.getValue().isEmpty()) {
                                                    boolean isHeadParameter = headParameterNames.contains(entryFiltered) ? true : false;
                                                    if (isHeadParameter) {
                                                        dmsSysElements.stream().filter(element -> element.getHead().getParametrs().containsKey(entryFiltered.getKey()))
                                                                .map(filtredElement -> filtredElement.getHead().getParametrs().values()).collect(toList());
                                                    } else {
                                                        dmsSysElements.stream().forEach(
                                                                element -> {
                                                                    boolean[] isCreited = new boolean[1];
                                                                    isCreited[0] = false;
                                                                    List<StringBuilder> fullNameColumnTemp = new ArrayList<>();
                                                                    Arrays.stream(element.getBodies()).forEach(
                                                                            body -> {
                                                                                String value = body.getParametrs().get(entryFiltered.getKey());
                                                                                if (Objects.nonNull(value) && !value.isEmpty()) {
                                                                                    if (!isCreited[0]) {
                                                                                        fullNameColumn.forEach(fullName -> fullNameColumnTemp.add(new StringBuilder(fullName.toString())));
                                                                                        isCreited[0] = true;
                                                                                        fullNameColumn.forEach(stringBuilder -> stringBuilder.append(value + "\n"));
                                                                                    } else {
                                                                                        List<StringBuilder> fullNameColumnTemp2 = new ArrayList<>();
                                                                                        fullNameColumnTemp.forEach(fullName -> fullNameColumnTemp2.add(new StringBuilder(fullName.toString())));
                                                                                        fullNameColumnTemp2.forEach(stringBuilder -> stringBuilder.append(value + "\n"));
                                                                                        fullNameColumn.addAll(fullNameColumnTemp2);
                                                                                    }
                                                                                }
                                                                            }
                                                                    );
                                                                }
                                                        );
                                                    }
                                                } else {
                                                    fullNameColumn.forEach(stringBuilder -> stringBuilder.append(entryFiltered.getValue() + "\n"));
                                                }
                                            } else {
                                                fullNameColumn.forEach(stringBuilder -> stringBuilder.append(entryFiltered.getKey() + NOTNAME));
                                            }
                                        }
                                );
                        fullNameColumn.forEach(fullName -> headerColumnString.add(fullName.toString()));
                    }
                }
        );
        return headerColumnString;
    }

    private void OneColumnRowHeader(Set<String> parameterNames, List<String> headerColumnString, Map<String, String> colunmMap) {
        colunmMap.keySet().stream()
                .filter(key -> !CommonConstants.HeaderAttibute.PRINTED.equalsIgnoreCase(key))
                .filter(key -> !CommonConstants.HeaderAttibute.HEADER.equalsIgnoreCase(key))
            .forEach(filteredfdKey -> {
                    if (parameterNames.contains(filteredfdKey)) {
                        headerColumnString.add(filteredfdKey);
                    } else {
                        headerColumnString.add(filteredfdKey + NOTNAME);
                    }
                }
            );
    }

    private void OneColumnRowValue(List<String> headerColumnString, Map<String, String> colunmMap, List<DmsSysElement> dmsSysElements) {
        Set<String> headParameterNames = getHeadParameterNames(dmsSysElements);
        Set<String> bodyParameterNames = getBodyParametersNames(dmsSysElements);
        Set<String> parameterNames = getParameterNames(headParameterNames, bodyParameterNames);

        colunmMap.keySet().stream()
                .filter(key -> !CommonConstants.HeaderAttibute.PRINTED.equalsIgnoreCase(key))
                .filter(key -> !CommonConstants.HeaderAttibute.HEADER.equalsIgnoreCase(key))
                .forEach(filteredfdKey -> {
                            if (parameterNames.contains(filteredfdKey)) {
                                boolean isHeadParameter = headParameterNames.contains(filteredfdKey);
                                if (isHeadParameter) {
                                    dmsSysElements.stream().filter(element -> element.getHead().getParametrs().containsKey(filteredfdKey))
                                            .forEach(filtredElement -> headerColumnString.add(filtredElement.getHead().getParametrs().get(filteredfdKey)));
                                }
                            } else {
                                headerColumnString.add(NOTVALUE);
                            }
                        }
                );
    }

    public List<String> getDmsSysElementsHeader(Rule ruleFiltredByHeadByBody, List<DmsSysElement> dmsSysElements, String additionalСolumnName) {
        List<DmsSysElement> filtredData =  getFiltredData(dmsSysElements, ruleFiltredByHeadByBody);
        List<String>  headerColumns =  getHeaderColumns(filtredData, ruleFiltredByHeadByBody);
        headerColumns.add(additionalСolumnName);
        return headerColumns;
    }

    public List<String> getDmsSysElementsRow(Rule ruleFiltredByHeadByBody, List<DmsSysElement> dmsSysElements, String additionalСolumnValue) {
        List<DmsSysElement> filtredData =  getFiltredData(dmsSysElements, ruleFiltredByHeadByBody);
        List<String>  row =  getRow(filtredData, ruleFiltredByHeadByBody);
        row.add(additionalСolumnValue);
        return row;
    }

    private Set<String> getBodyParametersNames(List<DmsSysElement> dmsSysElements) {
        Set<String> bodyParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                Arrays.asList(element.getBodies()).forEach(
                    elementBody -> bodyParameterNames.addAll(elementBody.getParametrs().keySet())
                );
            }
        );
        return bodyParameterNames;
    }

    private Set<String> getHeadParameterNames(List<DmsSysElement> dmsSysElements) {
        Set<String> headParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                headParameterNames.addAll(element.getHead().getParametrs().keySet());
            }
        );
        return headParameterNames;
    }

    private Set<String> getParameterNames(Set<String> headParameterNames, Set<String> bodyParameterNames) {
        Set<String> parameterNames = new HashSet<>();
        parameterNames.addAll(headParameterNames);
        parameterNames.addAll(bodyParameterNames);
        return parameterNames;
    }

    public static boolean compareHeaders(List<String> headerTemplate, List<String> header, String filename) {
        for (int i = 0; i < headerTemplate.size(); i++) {
            if (headerTemplate.get(i).equals(header.get(i))) {
            } else {
                System.out.println("Different data set: " + filename);
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> getTable(Rule ruleFiltredByHeadByBody, String path, String[] types) {
        Reader reader = new Reader();
        Generator generator = new Generator();
        List<String> fileNames = Manager.getFilesFrom(path, types);
        List<List<String>> table = new ArrayList<>();

        List<String> headerTemplate = new ArrayList<>();

        fileNames.forEach( fileName -> {
            System.out.println(fileName);
                List<DmsSysElement> dmsSysElements = reader.readFromGzFile(path, fileName);
                List<String> header = generator.getDmsSysElementsHeader(ruleFiltredByHeadByBody, dmsSysElements, "additional");
                List<String> row = generator.getDmsSysElementsRow(ruleFiltredByHeadByBody, dmsSysElements, fileName);
                if (headerTemplate.isEmpty()) {
                    headerTemplate.addAll(header) ;
                    table.add(header);
                    table.add(row);
                } else {
                    if(Generator.compareHeaders(headerTemplate, header,fileName)) {
                        table.add(row);
                    }
                }
            }
        );
        return table;
    }

    /**
     * The method gets the header with \n  and returns more rows , which are more level header
     * @param header header with \n
     * @return more level header
     */
    public static List<List<String>> generateFullHeader (List<String> header ) {

        List<List<String>> tempHeader =  header.stream().map(columnHeader ->
                    Arrays.asList(    columnHeader.split("\n"))
        ).collect(toList());

        List<List<String>> headerMoreRows = new ArrayList<>();
        int sizeRows = tempHeader.stream().map(columnHeader -> columnHeader.size() ).max(Integer::compareTo).get();


        for (int i=0; i< sizeRows; i++) {
            List<String> row = new ArrayList<>();

            for( List<String> column: tempHeader) {
                row.add( i<column.size() ? column.get(i) : "");
            }
            headerMoreRows.add(row);
        }
        return headerMoreRows;
    }

    /**
     * The method gets the header with \n  and returns more rows , which are one level header
     * @param header header with \n
     * @return more one header
     */
    public static List<List<String>> generateShortHeader (List<String> header ) {
        List<String> list = header.stream().map(column -> {
            String[] headerColumnSplit = column.split("\n");
            return headerColumnSplit[headerColumnSplit.length -1];
        }).collect(Collectors.toList());
        List<List<String>>  shortHeader = new ArrayList<>();
        shortHeader.add(list);
        return shortHeader;
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

    public static List<List<String>> getTableWithModifiedHeader(List<List<String>> table, CommonConstants.HeaderFormatAttibute headerFormatAttibute) {

        List<List<String>> modifiedHeader = Generator.generateFullHeader(table.get(0));

        switch (headerFormatAttibute) {
            case FULL: modifiedHeader = Generator.generateFullHeader(table.get(0));
                break;
            case SHORT: modifiedHeader = Generator.generateShortHeader(table.get(0));
                break;
            default:
        }

        List<List<String>> modifiedHeaderTable = new ArrayList<>();
        table.remove(0);
        modifiedHeaderTable.addAll(modifiedHeader);
        modifiedHeaderTable.addAll(table);
        return modifiedHeaderTable;
    }

    public static List<List<String>> getTableWithModifiedHeader(List<List<String>> table, CommonConstants.HeaderFormatAttibute headerFormatAttibute,
    List<String> formatHeader, List<String> formatBody ) {

        List<List<String>> modifiedHeader = Generator.generateFullHeader(table.get(0));

        switch (headerFormatAttibute) {
            case FULL: modifiedHeader = Generator.generateFullHeader(table.get(0));
                break;
            case SHORT: modifiedHeader = Generator.generateShortHeader(table.get(0));
                break;
            default:
        }

        List<List<String>> formatedTable = new ArrayList<>();
        table.remove(0);

        List<List<String>> formatModifiedHeader = new ArrayList<>();
        formatedTable(formatHeader, modifiedHeader, formatModifiedHeader,CommonConstants.PartTable.HEADER);

        List<List<String>> formatModifiedBody = new ArrayList<>();
        formatedTable(formatBody, table, formatModifiedBody, CommonConstants.PartTable.BODY);

        formatedTable.addAll(formatModifiedHeader);
        formatedTable.addAll(formatModifiedBody);
        return formatedTable;
    }

    private static void formatedTable(List<String> formatColumns, List<List<String>> sourseTable,
                                      List<List<String>> formatedTable,
                                      CommonConstants.PartTable partTable)  {
        for (List<String> row : sourseTable) {
            List<String> formatRow = new ArrayList<>();
            for(String column : row) {
                if (  row.get(0) == column) {
                    formatRow.add( String.format(  formatColumns.get(0),
                        partTable == CommonConstants.PartTable.HEADER ? column : Double.parseDouble(column)));
                }
                else {
                    if (  row.get(row.size()-1) == column) {
                        formatRow.add( String.format(  formatColumns.get(formatColumns.size()-1),
                                partTable == CommonConstants.PartTable.HEADER ? column : column));
                    } else {
                        formatRow.add( String.format(  formatColumns.get(1),
                                partTable == CommonConstants.PartTable.HEADER ? column : Double.parseDouble(column))
                                 );
                    }
                }
            }
            formatedTable.add(formatRow);
        }
    }

    public static void convertDateToString(List<List<String>> table, int numberColumn) {

        String startDate = table.get(1).get(numberColumn);

        for (int i=1; i<table.size(); i++) {
            Double interval = convertStringToIntervalTime(startDate, table.get(i).get(numberColumn), CommonConstants.IntervalKind.HOUR);
            table.get(i).set(0,interval.toString());
        }
    }


    public static double convertStringToIntervalTime(String startDate, String currentDate, CommonConstants.IntervalKind intervalKind) {
        ZonedDateTime zonedDateTimeStart = ZonedDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime zonedDateTimeCurrent = ZonedDateTime.parse(currentDate, DateTimeFormatter.ISO_DATE_TIME);
        Duration duration = Duration.between(zonedDateTimeStart, zonedDateTimeCurrent);
        double interval = 0;

        switch (intervalKind) {
            case HOUR:
                interval = (double) duration.toMinutes() / 60.0;
                break;
            default:
                interval = (double) duration.toHours();
        }
        return interval;
    }


}
