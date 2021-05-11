package com.example.tuan8_2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.tuan8_2.User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract com.example.tuan8_2.UserDao userDao();
}
