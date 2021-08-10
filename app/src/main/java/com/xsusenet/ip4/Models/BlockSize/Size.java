package com.xsusenet.ip4.Models.BlockSize;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Size {
    boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("block_size")
    @Expose
    private String blockSize;
    @SerializedName("no_of_subnets")
    @Expose
    private Integer noOfSubnets;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(String blockSize) {
        this.blockSize = blockSize;
    }

    public Integer getNoOfSubnets() {
        return noOfSubnets;
    }

    public void setNoOfSubnets(Integer noOfSubnets) {
        this.noOfSubnets = noOfSubnets;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
