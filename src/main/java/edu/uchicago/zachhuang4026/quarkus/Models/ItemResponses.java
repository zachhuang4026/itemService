package edu.uchicago.zachhuang4026.quarkus.Models;

import java.util.List;

public class ItemResponses {
    private String statueCode;
    private List<Object> objects;

    public ItemResponses(String statueCode, List<Object> objects) {
        this.statueCode = statueCode;
        this.objects = objects;
    }

    public ItemResponses() {
    }

    public String getStatueCode() {
        return statueCode;
    }

    public void setStatueCode(String statueCode) {
        this.statueCode = statueCode;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
