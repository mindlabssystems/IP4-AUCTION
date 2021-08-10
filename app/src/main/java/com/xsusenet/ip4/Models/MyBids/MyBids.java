package com.xsusenet.ip4.Models.MyBids;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xsusenet.ip4.Models.Sales.Results;

public class MyBids {
    @SerializedName("results")
    @Expose
    private Bids results;

    public Bids getResults() {
        return results;
    }

    public void setResults(Bids results) {
        this.results = results;
    }
}
