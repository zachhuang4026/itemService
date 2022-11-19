package edu.uchicago.zachhuang4026.quarkus.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemListElement {
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("resultScore")
    @Expose
    private Double resultScore;
    @SerializedName("result")
    @Expose
    private Result result;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getResultScore() {
        return resultScore;
    }

    public void setResultScore(Double resultScore) {
        this.resultScore = resultScore;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
