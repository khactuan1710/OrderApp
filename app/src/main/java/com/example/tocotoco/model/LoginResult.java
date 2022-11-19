package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class LoginResult extends SimpleResult{
    @SerializedName("result")
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
