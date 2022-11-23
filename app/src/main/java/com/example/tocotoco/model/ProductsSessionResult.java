package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsSessionResult extends SimpleResult{
    @SerializedName("result")
    List<ProductSessionModel> results;

    public List<ProductSessionModel> getResults() {
        return results;
    }

    public void setResults(List<ProductSessionModel> results) {
        this.results = results;
    }
}
