package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResult extends SimpleResult{
    @SerializedName("result")
    List<CategoriesResultModel> result;

    public List<CategoriesResultModel> getResult() {
        return result;
    }

    public void setResult(List<CategoriesResultModel> result) {
        this.result = result;
    }

    public class  CategoriesResultModel {
        @SerializedName("id")
        int id;
        @SerializedName("name")
        String name;
        @SerializedName("description")
        String description;
        @SerializedName("displayImage")
        String displayImage;
        @SerializedName("createAt")
        String createAt;
        @SerializedName("modifiedAt")
        String modifiedAt;

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

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisplayImage() {
            return displayImage;
        }

        public void setDisplayImage(String displayImage) {
            this.displayImage = displayImage;
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
    }
}
