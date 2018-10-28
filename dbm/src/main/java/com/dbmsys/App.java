package com.dbmsys;

import com.dbmsys.jsonapi.template.rules.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Rule ruleOnlyHeader = new Rule(new ArrayList<List<String>>() {
            {
                add(Arrays.asList("Started"));
                add(Arrays.asList("Address", "Category", "Instance", "Value"));
            }
        });
    }
}
