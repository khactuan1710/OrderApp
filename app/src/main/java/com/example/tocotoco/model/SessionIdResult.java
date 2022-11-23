package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class SessionIdResult extends SimpleResult{
    @SerializedName("result")
    int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
