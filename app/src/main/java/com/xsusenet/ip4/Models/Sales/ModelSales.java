package com.xsusenet.ip4.Models.Sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelSales {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
  /*  @SerializedName("all")
    @Expose
    private List<SalesResult> all = null;
    @SerializedName("isLastpage")
    @Expose
    private Integer isLastpage;

    public List<SalesResult> getAll() {
        return all;
    }

    public void setAll(List<SalesResult> all) {
        this.all = all;
    }

    public Integer getIsLastpage() {
        return isLastpage;
    }

    public void setIsLastpage(Integer isLastpage) {
        this.isLastpage = isLastpage;
    }*/
}
