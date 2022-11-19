package edu.uchicago.zachhuang4026.quarkus.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DetailedDescription {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("articleBody")
    @Expose
    private String articleBody;
    @SerializedName("license")
    @Expose
    private String license;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
