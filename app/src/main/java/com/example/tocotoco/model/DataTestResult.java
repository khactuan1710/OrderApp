package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class DataTestResult extends SimpleResult{
    @SerializedName("provinceId")
    int provinceId;

    @SerializedName("user_type")
    String user_type;

    @SerializedName("complainerPhone")
    String complainerPhone;


    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getComplainerPhone() {
        return complainerPhone;
    }

    public void setComplainerPhone(String complainerPhone) {
        this.complainerPhone = complainerPhone;
    }
}
