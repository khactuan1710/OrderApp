package com.example.tocotoco.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateAccountResult {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("result")
    @Expose
    private Boolean result;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
