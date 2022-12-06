package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class UserCurrentResult extends  SimpleResult{
    @SerializedName("result")
    UserCurrentModel results;

    public UserCurrentModel getResults() {
        return results;
    }

    public void setResults(UserCurrentModel results) {
        this.results = results;
    }

    public  class UserCurrentModel {
        @SerializedName("orderId")
        int orderId;
        @SerializedName("total")
        String total;
        @SerializedName("paymentId")
        int paymentId;
        @SerializedName("createAt")
        String createAt;
        @SerializedName("modifiedAt")
        String modifiedAt;
        @SerializedName("status")
        String status;
        @SerializedName("provider")
        String provider;
        @SerializedName("address")
        String address;
        @SerializedName("phoneNumber")
        String phoneNumber;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(int paymentId) {
            this.paymentId = paymentId;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getModifiedAt() {
            return modifiedAt;
        }

        public void setModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
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
    }
}
