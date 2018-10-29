package com.dbmsys.original.data.generation;

import com.dbmsys.jsonapi.template.data.Body;
import com.dbmsys.jsonapi.template.data.DmsSysElement;
import com.dbmsys.jsonapi.template.rules.Rule;


import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ArrayUtils.toArray;

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
        }).collect(Collectors.toList());


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
        ).collect(Collectors.toList());


        List<DmsSysElement> filtredNonEmptyBodiesDmsSysElements =
                filtredByBodyDmsSysElements.stream().filter(dmsSysElement -> dmsSysElement.getBodies().length!=0).collect(Collectors.toList());

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

        rule.getHeaderColumns().stream().map(
                colunm -> {
                    if(colunm.entrySet().size()==2) {

                        colunm.keySet().stream().filter(key -> CommonConstants.HeaderAttibute.PRINTED.equalsIgnoreCase( key)).map(key -> )

                    }



                }
        ).collect(Collectors.toList());



        return null;
    }

}
