package com.example.tocotoco.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {TokenDevice.class}, version = 1)
public abstract  class TokenDeviceDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "tokenDevice.db";
    private static TokenDeviceDatabase instance;
    public static synchronized TokenDeviceDatabase getInstance(Context context) {
        if( instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TokenDeviceDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract TokenDeviceDAO tokenDeviceDAO();

}
