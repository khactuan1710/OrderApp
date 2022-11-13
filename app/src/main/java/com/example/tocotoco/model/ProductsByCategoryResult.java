package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsByCategoryResult {
    @SerializedName("result")
    List<ProductsByCategoryResultModel> results;

    public void setResults(List<ProductsByCategoryResultModel> results) {
        this.results = results;
    }

    public List<ProductsByCategoryResultModel> getResults() {
        return results;
    }

}
