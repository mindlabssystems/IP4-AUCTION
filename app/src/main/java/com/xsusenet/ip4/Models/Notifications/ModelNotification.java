package com.xsusenet.ip4.Models.Notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xsusenet.ip4.Models.Sales.Results;

import java.util.List;

public class ModelNotification {

    @SerializedName("results")
    @Expose
    private NotificationResults results;

    public NotificationResults getResults() {
        return results;
    }

    public void setResults(NotificationResults results) {
        this.results = results;
    }
   /* @SerializedName("notifications")
    @Expose
    private List<Notification> notifications = null;
    @SerializedName("isLastpage")
    @Expose
    private Integer isLastpage;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Integer getIsLastpage() {
        return isLastpage;
    }

    public void setIsLastpage(Integer isLastpage) {
        this.isLastpage = isLastpage;
    }*/

}
