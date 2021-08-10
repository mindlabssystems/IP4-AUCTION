package com.xsusenet.ip4.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelReg {
    @SerializedName("result")
    @Expose
    private RegResult result;

    public RegResult getResult() {
        return result;
    }

    public void setResult(RegResult result) {
        this.result = result;
    }
}
