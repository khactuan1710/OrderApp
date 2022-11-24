package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResult extends SimpleResult{
    @SerializedName("result")
    ProductsByCategoryResultModel results;

    public ProductsByCategoryResultModel getResults() {
        return results;
    }

    public void setResults(ProductsByCategoryResultModel results) {
        this.results = results;
    }
}
