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

    public List<List<String>> addRows (List<DmsSysElement> dmsSysElements, Rule rule) {


//        List<DmsSysElement> filtredByHeadDmsSysElements = dmsSysElements.stream().filter(dmsSysElement -> {
//            long filtredCount =
//                    rule.getFilterHead().entrySet().stream().filter(
//                            entry -> {
//                                if (entry.getValue().isEmpty()) {
//                                    return dmsSysElement.getHead().getParametrs().keySet().contains(entry.getKey());
//                                } else {
//                                    return dmsSysElement.getHead().getParametrs().entrySet().contains(entry);
//                                }
//                            }
//                    ).count();
//
//            return filtredCount == rule.getFilterHead().size();
//        }).collect(Collectors.toList());
//
//
//        List<DmsSysElement> filtredByBodyDmsSysElements = filtredByHeadDmsSysElements.stream().map(
//                dmsSysElement -> {
//                    dmsSysElement.setBodies(
//
//                            Arrays.asList(
//                                    dmsSysElement.getBodies()
//                            ).stream().filter(
//                                    body -> {
//
//                                        long filtredCount =
//                                                rule.getFilterBody().entrySet().stream().filter(
//                                                        entry -> {
//                                                            if (entry.getValue().isEmpty()) {
//                                                                return body.getParametrs().keySet().contains(entry.getKey());
//                                                            } else {
//                                                                return body.getParametrs().entrySet().contains(entry);
//                                                            }
//                                                        }
//                                                ).count();
//                                        return filtredCount == rule.getFilterBody().size();
//
//                                    }
//                            ).toArray(Body[]::new)
//
//                    );
//                    return dmsSysElement;
//                }
//        ).collect(Collectors.toList());
//
//
//        List<DmsSysElement> filtredNonEmptyBodiesDmsSysElements =
//                filtredByBodyDmsSysElements.stream().filter(dmsSysElement -> dmsSysElement.getBodies().length!=0).collect(Collectors.toList());
//
//


        return null;
    }

    public List<String> getHeaderColumns (List<DmsSysElement> dmsSysElements, Rule rule) {


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
                if (colunmMap.entrySet().size() == 2) {
                    OneColumnRowYtader(parameterNames, headerColumnString, colunmMap);
                } else {


                    StringBuilder fullNameColumn = new StringBuilder();

                    colunmMap.entrySet().stream().filter(entry
                        -> !CommonConstants.HeaderAttibute.PRINTED.equalsIgnoreCase(entry.getKey())).forEach(
                        entryFiltered -> {
                            if (parameterNames.contains(entryFiltered.getKey())) {
                                if (entryFiltered.getValue().isEmpty()) {


                                    boolean isHeadParameter = headParameterNames.contains(entryFiltered) ? true : false;

                                    if(isHeadParameter) {


                                        dmsSysElements.stream().filter(  element -> element.getHead().getParametrs().containsKey(entryFiltered.getKey()))
                                            .map(filtredElement -> filtredElement.getHead().getParametrs().values()).collect(toList());

                                    } else {


                                        dmsSysElements.stream().forEach(
                                            element ->{

                                                Arrays.stream(element.getBodies()).forEach(
                                                    body -> {
                                                        String value = body.getParametrs().get(entryFiltered.getKey());
                                                        if (Objects.nonNull(value)&&! value.isEmpty()) {


                                                            fullNameColumn.append(+"\n");

                                                        }



                                                    }
                                                );
                                            }
                                        );
                                    }
                                } else {
                                    fullNameColumn.append(entryFiltered.getValue() + "\n");
                                }
                            } else {
                                fullNameColumn.append(entryFiltered.getKey() + NOTNAME);
                            }
                        }
                    );
                    headerColumnString.add(fullNameColumn.toString());

                }
            }
        );



        return null;
    }

    private void OneColumnRowYtader(Set<String> parameterNames, List<String> headerColumnString, Map<String, String> colunmMap) {
        colunmMap.keySet().stream().filter(key -> !CommonConstants.HeaderAttibute.PRINTED.equalsIgnoreCase(key))
            .forEach(filteredfdKey -> {
                    if (parameterNames.contains(filteredfdKey)) {
                        headerColumnString.add(filteredfdKey);
                    } else {
                        headerColumnString.add(filteredfdKey + NOTNAME);
                    }
                }
            );
    }

    private String getFullColumnName(Map<String,String> headerColumn, List<DmsSysElement> dmsSysElements) {
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

        Map<String,String> headerColumnTemp = new HashMap<>(headerColumn);
        headerColumnTemp.remove(CommonConstants.HeaderAttibute.PRINTED);

        StringBuilder columnFullName = new StringBuilder();

        if(headerColumnTemp.size()==1) {
            headerColumn.keySet().forEach(key -> columnFullName.append(key.toString()));
            return columnFullName.toString();
        }




        headerColumnTemp.entrySet().forEach(

            entry -> {















            }

        );





//        dmsSysElements.stream().filter(
//            dmsSysElement -> {
//
//
//
//
//
//                return true;
//            }
//
//
//        ).collect(Collectors.toList());





        return "";
    }

}
