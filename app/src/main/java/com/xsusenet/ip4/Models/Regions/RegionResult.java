package com.xsusenet.ip4.Models.Regions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionResult {

    boolean checked;

//    @SerializedName("region_id")
    @SerializedName("id")
    @Expose
    private String regionId;
    @SerializedName("region_name")
    @Expose
    private String regionName;

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
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}