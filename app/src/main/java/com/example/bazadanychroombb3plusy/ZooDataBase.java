package com.example.bazadanychroombb3plusy;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Zwierze.class}, version = 1)
public abstract class ZooDataBase extends RoomDatabase {
    public abstract ZwierzeDAO getZwierzeDao();
}
