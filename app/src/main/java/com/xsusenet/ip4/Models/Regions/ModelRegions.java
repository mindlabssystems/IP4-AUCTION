package com.xsusenet.ip4.Models.Regions;

import android.graphics.Region;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelRegions {
    @SerializedName("regions")
    @Expose
    private List<RegionResult> regions = null;

    public List<RegionResult> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionResult> regions) {
        this.regions = regions;
    }
}
