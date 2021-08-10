package com.xsusenet.ip4.Models.Purchases;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("all")
    @Expose
    private List<All> all = null;
    @SerializedName("isLastpage")
    @Expose
    private Integer isLastpage;

    public List<All> getAll() {
        return all;
    }

    public void setAll(List<All> all) {
        this.all = all;
    }

    public Integer getIsLastpage() {
        return isLastpage;
    }

    public void setIsLastpage(Integer isLastpage) {
        this.isLastpage = isLastpage;
    }

}