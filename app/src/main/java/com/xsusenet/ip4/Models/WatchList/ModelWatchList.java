package com.xsusenet.ip4.Models.WatchList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xsusenet.ip4.Models.Sales.Results;

import java.util.List;

public class ModelWatchList {
    @SerializedName("results")
    @Expose
    private WatchResults results;

    public WatchResults getResults() {
        return results;
    }

    public void setResults(WatchResults results) {
        this.results = results;
    }
   /* @SerializedName("watchlists")
    @Expose
    private List<Result> watchlists = null;

    public List<Result> getWatchlists() {
        return watchlists;
    }

    public void setWatchlists(List<Result> watchlists) {
        this.watchlists = watchlists;
    }
*/
  /*  @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }*/
}
