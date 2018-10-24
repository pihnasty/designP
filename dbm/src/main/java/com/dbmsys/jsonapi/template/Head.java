package com.dbmsys.jsonapi.template;

import java.util.Map;
import java.util.TreeMap;

public class Head {

    private Map<String,String> parametrs = new TreeMap();

    public void addParameter (String key, String value) {
            parametrs.put(key,value);
    }

}



