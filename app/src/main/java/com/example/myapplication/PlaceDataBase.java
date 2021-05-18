package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Place.class}, version = 1)
abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();
}
