package com.dbmsys.jsonapi.template.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rule {

    Map<String,String> filterHead = new HashMap<>();
    Map<String,String> filterBody = new HashMap<>();



    List<Map<String,String>> headerColumns = new ArrayList<>();
    List<String> whereByHeaderValues = new ArrayList<>();
    List<String> whereByBodyValues = new ArrayList<>();
    List<String> groupingByHeaderValues = new ArrayList<>();
    List<String> groupingByBodyValues = new ArrayList<>();

    public Rule(Map<String, String> filterHead, Map<String, String> filterBody) {
        this.filterHead = filterHead;
        this.filterBody = filterBody;
    }

    public Rule(List<Map<String, String>> headerColumns) {
        this.headerColumns = headerColumns;
    }

    public Map<String, String> getFilterHead() {
        return filterHead;
    }

    public void setFilterHead(Map<String, String> filterHead) {
        this.filterHead = filterHead;
    }

    public Map<String, String> getFilterBody() {
        return filterBody;
    }

    public void setFilterBody(Map<String, String> filterBody) {
        this.filterBody = filterBody;
    }

    public List<Map<String, String>> getHeaderColumns() {
        return headerColumns;
    }

    public void setHeaderColumns(List<Map<String, String>> headerColumns) {
        this.headerColumns = headerColumns;
    }

    public List<String> getWhereByHeaderValues() {
        return whereByHeaderValues;
    }

    public void setWhereByHeaderValues(List<String> whereByHeaderValues) {
        this.whereByHeaderValues = whereByHeaderValues;
    }

    public List<String> getWhereByBodyValues() {
        return whereByBodyValues;
    }

    public void setWhereByBodyValues(List<String> whereByBodyValues) {
        this.whereByBodyValues = whereByBodyValues;
    }

    public List<String> getGroupingByHeaderValues() {
        return groupingByHeaderValues;
    }

    public void setGroupingByHeaderValues(List<String> groupingByHeaderValues) {
        this.groupingByHeaderValues = groupingByHeaderValues;
    }

    public List<String> getGroupingByBodyValues() {
        return groupingByBodyValues;
    }

    public void setGroupingByBodyValues(List<String> groupingByBodyValues) {
        this.groupingByBodyValues = groupingByBodyValues;
    }
}

