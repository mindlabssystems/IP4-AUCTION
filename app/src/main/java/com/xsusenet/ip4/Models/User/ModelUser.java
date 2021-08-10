package com.xsusenet.ip4.Models.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelUser {
    @SerializedName("success")
    @Expose
    private User success;

    public User getSuccess() {
        return success;
    }

    public void setSuccess(User success) {
        this.success = success;
    }
}
