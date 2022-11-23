package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class ProductSessionModel {
    @SerializedName("id")
    int id;
    @SerializedName("sessionId")
    int sessionId;
    @SerializedName("productId")
    int productId;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("productName")
    String productName;
    @SerializedName("description")
    String description;
    @SerializedName("total")
    int total;
    @SerializedName("price")
    String price;
    @SerializedName("productCategoryName")
    String productCategoryName;
    @SerializedName("size")
    String size;
    @SerializedName("discountId")
    int discountId;
    @SerializedName("priceAfterDiscount")
    String priceAfterDiscount;

}
