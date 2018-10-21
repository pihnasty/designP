package com.dbmsys.template;

public class DmsSysTemplate {
    private String head;
    private Body body;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}

class Body {

    public Body(String category, String counter, String value, String status) {
        this.category = category;
        this.counter = counter;
        this.value = value;
        this.status = status;
    }

    private String category;
    private String counter;
    private String value;
    private String status;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


