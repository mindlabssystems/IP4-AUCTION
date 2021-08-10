package com.xsusenet.ip4.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelLogin {

    @SerializedName("result")
    @Expose
    private List<LoginResult> result = null;

    public List<LoginResult> getResult() {
        return result;
    }

    public void setResult(List<LoginResult> result) {
        this.result = result;
    }

}
