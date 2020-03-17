package com.example.jetpackdemo.database.repository

import android.content.Context
import com.example.jetpackdemo.database.AppDataBase
import com.example.jetpackdemo.database.dao.GardenPlantDao
import com.example.jetpackdemo.database.entity.GardenPlant

class GardenPlantRepository private constructor(private val gardenPlantDao: GardenPlantDao) {

    //insert gardenPlant into database
    suspend fun createGardenPlantByPlantId(plantId: String) {
        val gardenPlant = GardenPlant(plantId)
        gardenPlantDao.insertGardenPlant(gardenPlant)
    }

    //query all planted gardenPlants
    fun getGardenPlants() = gardenPlantDao.getPlantedGardenPlants()


    fun isPlanted(plantId: String) = gardenPlantDao.isPlanted(plantId = plantId)

    companion object {
        val TAG = GardenPlantRepository::class.java.simpleName

        @Volatile
        private var instance: GardenPlantRepository? = null

        fun getInstance(gardenPlantDao: GardenPlantDao): GardenPlantRepository =
            instance ?: synchronized(this) {
                instance ?: GardenPlantRepository(gardenPlantDao).also { instance = it }
            }
    }
}