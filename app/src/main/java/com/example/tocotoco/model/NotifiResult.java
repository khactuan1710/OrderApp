package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class NotifiResult extends SimpleResult{
    @SerializedName("result")
    private NotifiModel result;
    public class NotifiModel {
        @SerializedName("id")
        int id;
        @SerializedName("title")
        String title;
        @SerializedName("message")
        String message;
        @SerializedName("type")
        String type;
        @SerializedName("image")
        String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public NotifiModel getResult() {
        return result;
    }

    public void setResult(NotifiModel result) {
        this.result = result;
    }
}
