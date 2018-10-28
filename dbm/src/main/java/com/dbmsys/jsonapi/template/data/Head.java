package com.dbmsys.jsonapi.template.data;

import java.util.Map;
import java.util.TreeMap;

public class Head {

    private Map<String,String> parametrs = new TreeMap();

    public void addParameter (String key, String value) {
            parametrs.put(key,value);
    }

    public Map<String, String> getParametrs() {
        return parametrs;
    }
}



