package edu.uchicago.zachhuang4026.quarkus.Models;


public class ItemResponse {
    private String statueCode;
    private Object object;

    public ItemResponse(String statueCode, Object object) {
        this.statueCode = statueCode;
        this.object = object;
    }

    public ItemResponse() {
    }

    public String getStatueCode() {
        return statueCode;
    }

    public void setStatueCode(String statueCode) {
        this.statueCode = statueCode;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
