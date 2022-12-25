package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class ProductsByCategoryResultModel {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("productName")
    String productName;
    @SerializedName("description")
    String description;
    @SerializedName("productDescription")
    String productDescription;
    @SerializedName("categoryid")
    int categoryid;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("price")
    String price;
    @SerializedName("priceAfterDiscount")
    String priceAfterDiscount;
    @SerializedName("discountid")
    int discountid;
    @SerializedName("displayImage")
    String displayimage;
    @SerializedName("size")
    String size;
    @SerializedName("active")
    boolean active;

    public String getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPriceAfterDiscount(String priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDiscountid() {
        return discountid;
    }

    public void setDiscountid(int discountid) {
        this.discountid = discountid;
    }

    public String getDisplayimage() {
        return displayimage;
    }

    public void setDisplayimage(String displayimage) {
        this.displayimage = displayimage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
