package com.xsusenet.ip4.Models.Detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xsusenet.ip4.Models.Sales.Results;

public class ModelDetail {
    @SerializedName("results")
    @Expose
    private DetailResult results;

    public DetailResult getResults() {
        return results;
    }

    public void setResults(DetailResult results) {
        this.results = results;
    }


   /* @SerializedName("lists")
    @Expose
    private java.util.List<DetailList> lists = null;

    public java.util.List<DetailList> getLists() {
        return lists;
    }

    public void setLists(java.util.List<DetailList> lists) {
        this.lists = lists;
    }*/
}
