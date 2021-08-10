package com.xsusenet.ip4.Models.MyBids;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bids {
    @SerializedName("all")
    @Expose
    private List<Bid> all = null;

    public List<Bid> getAll() {
        return all;
    }

    public void setAll(List<Bid> all) {
        this.all = all;
    }
}
