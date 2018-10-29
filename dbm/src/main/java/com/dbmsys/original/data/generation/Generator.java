package com.dbmsys.original.data.generation;

import com.dbmsys.jsonapi.template.data.Body;
import com.dbmsys.jsonapi.template.data.DmsSysElement;
import com.dbmsys.jsonapi.template.rules.Rule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
        Set<String> headParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                    headParameterNames.addAll(element.getHead().getParametrs().keySet());
                }
        );

        Set<String> bodyParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                    Arrays.asList(element.getBodies()).forEach(
                            elementBody -> bodyParameterNames.addAll(elementBody.getParametrs().keySet())
                    );
                }
        );

        Set<String> parameterNames = new HashSet<>();
        parameterNames.addAll(headParameterNames);
        parameterNames.addAll(bodyParameterNames);


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
        Set<String> headParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                    headParameterNames.addAll(element.getHead().getParametrs().keySet());
                }
        );

        Set<String> bodyParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                    Arrays.asList(element.getBodies()).forEach(
                            elementBody -> bodyParameterNames.addAll(elementBody.getParametrs().keySet())
                    );
                }
        );

        Set<String> parameterNames = new HashSet<>();
        parameterNames.addAll(headParameterNames);
        parameterNames.addAll(bodyParameterNames);


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
        Set<String> headParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                    headParameterNames.addAll(element.getHead().getParametrs().keySet());
                }
        );

        Set<String> bodyParameterNames = new HashSet<>();
        dmsSysElements.forEach(element -> {
                    Arrays.asList(element.getBodies()).forEach(
                            elementBody -> bodyParameterNames.addAll(elementBody.getParametrs().keySet())
                    );
                }
        );

        Set<String> parameterNames = new HashSet<>();
        parameterNames.addAll(headParameterNames);
        parameterNames.addAll(bodyParameterNames);


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
}
