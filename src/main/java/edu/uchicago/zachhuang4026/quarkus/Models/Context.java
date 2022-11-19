package edu.uchicago.zachhuang4026.quarkus.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Context {
    @SerializedName("resultScore")
    @Expose
    private String resultScore;
    @SerializedName("EntitySearchResult")
    @Expose
    private String entitySearchResult;
    @SerializedName("kg")
    @Expose
    private String kg;
    @SerializedName("detailedDescription")
    @Expose
    private String detailedDescription;
    @SerializedName("@vocab")
    @Expose
    private String vocab;
    @SerializedName("goog")
    @Expose
    private String goog;

    public String getResultScore() {
        return resultScore;
    }

    public void setResultScore(String resultScore) {
        this.resultScore = resultScore;
    }

    public String getEntitySearchResult() {
        return entitySearchResult;
    }

    public void setEntitySearchResult(String entitySearchResult) {
        this.entitySearchResult = entitySearchResult;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    public String getGoog() {
        return goog;
    }

    public void setGoog(String goog) {
        this.goog = goog;
    }
}
