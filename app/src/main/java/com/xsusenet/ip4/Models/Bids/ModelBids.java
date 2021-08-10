package com.xsusenet.ip4.Models.Bids;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelBids {

    @SerializedName("bids")
    @Expose
    private List<Bid> bids = null;

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

  /*  @SerializedName("result")
    @Expose
    private List<Bid> result = null;

    public List<Bid> getResult() {
        return result;
    }

    public void setResult(List<Bid> result) {
        this.result = result;
    }*/
}
