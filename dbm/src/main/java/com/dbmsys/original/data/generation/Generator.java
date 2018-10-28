package com.dbmsys.original.data.generation;

import com.dbmsys.jsonapi.template.data.DmsSysElement;
import com.dbmsys.jsonapi.template.rules.Rule;


import java.util.*;
import java.util.stream.Collectors;

public class Generator {

    private final String NOTNAME ="\nnot name";

    List<List<String>> addHeader (List<DmsSysElement> dmsSysElements, Rule rule) {

        List<String> headerNames = new ArrayList<>();


        Set<String> names = new HashSet<>();
        dmsSysElements.forEach(element -> {
                    names.addAll(element.getHead().getParametrs().keySet());
                    Arrays.asList(element.getBodies()).forEach(
                            elementBody -> names.addAll(elementBody.getParametrs().keySet())
                    );
                }
        );

        rule.getHeaderColumns().forEach(
                columnList -> {
                    if (columnList.size()==1) {
                        if (names.contains(columnList.get(0))) {
                            headerNames.add(columnList.get(0));
                        } else {
                            headerNames.add(columnList.get(0)+NOTNAME);
                        }
                    } else {
                        StringBuilder fullNameColumn = new StringBuilder();
                        columnList.forEach(
                                columnParameter -> {
                                    if (names.contains(columnParameter)) {
                                        fullNameColumn.append(columnParameter).append("\n");
                                    } else {
                                        fullNameColumn.append(columnParameter).append(NOTNAME);
                                    }
                                }
                        );
                        headerNames.add(fullNameColumn.toString());

                    }



                }



        );







        return null;
    }



    List<List<String>> addRows (List<DmsSysElement> dmsSysElements, Rule rule) {



        return null;
    }




}
