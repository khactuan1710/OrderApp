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

    @SerializedName("displayImage")
    String displayImage;

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(String priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }
}
