package com.xsusenet.ip4.Models.BlockSize;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelBlockSize {
    @SerializedName("block_sizes")
    @Expose
    private List<Size> size = null;

    public List<Size> getSize() {
        return size;
    }

    public void setSize(List<Size> size) {
        this.size = size;
    }
}
