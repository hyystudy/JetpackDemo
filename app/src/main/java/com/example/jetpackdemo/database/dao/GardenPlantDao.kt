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

    @Query("SELECT EXISTS(SELECT 1 FROM garden_plants WHERE plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: String): LiveData<Boolean>


}