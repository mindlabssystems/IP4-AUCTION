package com.xsusenet.ip4.Models.Detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResult {
    @SerializedName("list")
    @Expose
    private DetailList list;

    public DetailList getList() {
        return list;
    }

    public void setList(DetailList list) {
        this.list = list;
    }
}
