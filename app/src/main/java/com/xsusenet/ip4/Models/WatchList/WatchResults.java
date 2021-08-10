package com.xsusenet.ip4.Models.WatchList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WatchResults {
    @SerializedName("all")
    @Expose
    private List<Result> all = null;

    public List<Result> getAll() {
        return all;
    }

    public void setAll(List<Result> all) {
        this.all = all;
    }
}
