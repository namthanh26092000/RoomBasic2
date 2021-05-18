package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM Place")
    List<Place> getAll();

    @Query("SELECT * FROM Place WHERE ID IN (:placeIds)")
    List<Place> loadAllByIds(int[] placeIds);

    @Query("SELECT * FROM Place WHERE place_Name LIKE :name LIMIT 1")
    Place findByName(String name);

    @Insert
    void insertPlace(Place place);

    @Delete
    void delete(Place place);

    @Query("UPDATE Place SET place_Name = :name WHERE ID = :id")
    void updatePlace(String name, int id);
}
