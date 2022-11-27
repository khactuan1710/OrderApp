package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoResult extends SimpleResult{
    @SerializedName("result")
    UserInfoModel results;

    public UserInfoModel getResults() {
        return results;
    }

    public void setResults(UserInfoModel results) {
        this.results = results;
    }

    public  class UserInfoModel {
        @SerializedName("id")
        int id;
        @SerializedName("username")
        String username;
        @SerializedName("name")
        String name;
        @SerializedName("email")
        String email;
        @SerializedName("phonenumber")
        String phonenumber;
        @SerializedName("createat")
        String createat;
        @SerializedName("address")
        String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getCreateat() {
            return createat;
        }

        public void setCreateat(String createat) {
            this.createat = createat;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
