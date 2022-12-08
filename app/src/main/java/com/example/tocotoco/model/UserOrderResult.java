package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserOrderResult extends SimpleResult{
    @SerializedName("result")
    List<UserOrderModel> results;

    public List<UserOrderModel> getResults() {
        return results;
    }


    public class  UserOrderModel {
        @SerializedName("id")
        int id;
        @SerializedName("total")
        String total;
        @SerializedName("createat")
        String createat;
        @SerializedName("status")
        String status;
        @SerializedName("provider")
        String provider;
        @SerializedName("address")
        String address;
        @SerializedName("phoneNumber")
        String phoneNumber;
        @SerializedName("totalProduct")
        String totalProduct;

        @SerializedName("displayImage")
        String displayImage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getCreateat() {
            return createat;
        }

        public void setCreateat(String createat) {
            this.createat = createat;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getTotalProduct() {
            return totalProduct;
        }

        public void setTotalProduct(String totalProduct) {
            this.totalProduct = totalProduct;
        }

        public String getDisplayImage() {
            return displayImage;
        }

        public void setDisplayImage(String displayImage) {
            this.displayImage = displayImage;
        }
    }
}
