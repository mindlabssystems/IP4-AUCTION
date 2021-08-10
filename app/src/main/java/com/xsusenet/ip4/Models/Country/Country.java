package com.xsusenet.ip4.Models.Country;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("countries_id")
    @Expose
    private String countriesId;
    @SerializedName("countries_name")
    @Expose
    private String countriesName;

    public String getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(String countriesId) {
        this.countriesId = countriesId;
    }

    public String getCountriesName() {
        return countriesName;
    }

    public void setCountriesName(String countriesName) {
        this.countriesName = countriesName;
    }
}
