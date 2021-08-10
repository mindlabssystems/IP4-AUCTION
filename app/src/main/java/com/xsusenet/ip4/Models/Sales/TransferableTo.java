package com.xsusenet.ip4.Models.Sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransferableTo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("list_id")
    @Expose
    private String listId;
    @SerializedName("region_id")
    @Expose
    private String regionId;
    @SerializedName("region_name")
    @Expose
    private String regionName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
