package com.example.tocotoco.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TokenDeviceDAO {
    @Insert
    void insertToken(TokenDevice tokenDevice);
    @Query("SELECT * FROM tokendevice")
    List<TokenDevice> getListToken();
}
