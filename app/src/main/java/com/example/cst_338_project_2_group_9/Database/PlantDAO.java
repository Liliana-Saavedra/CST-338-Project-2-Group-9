package com.example.cst_338_project_2_group_9.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst_338_project_2_group_9.entities.Plant;

import java.util.List;

@Dao
public interface PlantDAO {
    @Insert
    void insertPlant(Plant plant);

    @Update
    void updatePlant(Plant plant);

    @Delete
    void deletePlant(Plant plant);

    @Query("SELECT * FROM plants WHERE plantId = :plantId")
    Plant getPlantById(int plantId);

    @Query("SELECT * FROM plants")
    List<Plant> getAllPlants();
}