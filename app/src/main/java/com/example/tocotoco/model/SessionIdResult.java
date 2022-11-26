package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

public class SessionIdResult extends SimpleResult{
    @SerializedName("result")
    private SessionId result;

    public class SessionId{
        @SerializedName("id")
        int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public SessionId getResult() {
        return result;
    }

    public void setResult(SessionId result) {
        this.result = result;
    }
}
