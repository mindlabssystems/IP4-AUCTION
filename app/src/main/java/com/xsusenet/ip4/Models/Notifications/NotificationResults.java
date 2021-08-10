package com.xsusenet.ip4.Models.Notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationResults {
    @SerializedName("all")
    @Expose
    private List<Notification> all = null;
    @SerializedName("isLastpage")
    @Expose
    private Integer isLastpage;

    public List<Notification> getAll() {
        return all;
    }

    public void setAll(List<Notification> all) {
        this.all = all;
    }

    public Integer getIsLastpage() {
        return isLastpage;
    }

    public void setIsLastpage(Integer isLastpage) {
        this.isLastpage = isLastpage;
    }
}
