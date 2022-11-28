package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class CartInfoResult extends SimpleResult{
    @SerializedName("result")
    CartInfoModel results;

    public CartInfoModel getResults() {
        return results;
    }

    public void setResults(CartInfoModel results) {
        this.results = results;
    }

    public  class CartInfoModel {
        @SerializedName("totalCategory")
        String totalCategory;
        @SerializedName("totalQuantity")
        String totalQuantity;
        @SerializedName("priceBeforeDiscount")
        String priceBeforeDiscount;
        @SerializedName("priceAfterDiscount")
        String priceAfterDiscount;

        public String getTotalCategory() {
            return totalCategory;
        }

        public void setTotalCategory(String totalCategory) {
            this.totalCategory = totalCategory;
        }

        public String getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(String totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public String getPriceBeforeDiscount() {
            return priceBeforeDiscount;
        }

        public void setPriceBeforeDiscount(String priceBeforeDiscount) {
            this.priceBeforeDiscount = priceBeforeDiscount;
        }

        public String getPriceAfterDiscount() {
            return priceAfterDiscount;
        }

        public void setPriceAfterDiscount(String priceAfterDiscount) {
            this.priceAfterDiscount = priceAfterDiscount;
        }
    }
}
