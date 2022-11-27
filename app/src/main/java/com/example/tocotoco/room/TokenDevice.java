package com.example.tocotoco.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TokenDevice {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "tokenDevice")
    String tokenDevice;

    public TokenDevice(String tokenDevice) {
        this.tokenDevice = tokenDevice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTokenDevice() {
        return tokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        this.tokenDevice = tokenDevice;
    }
}
