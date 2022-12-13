package com.example.tocotoco.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderItemDetail {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("result")
    @Expose
    private List<OrderItemDetailResult> result = null;

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

    public List<OrderItemDetailResult> getResult() {
        return result;
    }

    public void setResult(List<OrderItemDetailResult> result) {
        this.result = result;
    }

}
