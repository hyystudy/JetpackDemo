package com.example.jetpackdemo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpackdemo.database.entity.GardenPlant
import com.example.jetpackdemo.database.entity.PlantAndGardenPlantings

@Dao
interface GardenPlantDao {

    @Insert
    suspend fun insertGardenPlant(gardenPlant: GardenPlant): Long

    @Query("SELECT * FROM plants WHERE id IN(SELECT  DISTINCT(plant_id) FROM garden_plants)")
    fun getPlantedGardenPlants(): LiveData<List<PlantAndGardenPlantings>>

}