package com.example.jetpackdemo.database.repository

import com.example.jetpackdemo.database.dao.GardenPlantDao
import com.example.jetpackdemo.database.entity.GardenPlant

class GardenPlantRepository constructor(private val gardenPlantDao: GardenPlantDao){

    //insert gardenPlant into database
    suspend fun createGardenPlantByPlantId(plantId: String) {
        val gardenPlant = GardenPlant(plantId)
        gardenPlantDao.insertGardenPlant(gardenPlant)
    }

    //query all planted gardenPlants
    fun getGardenPlants() = gardenPlantDao.getPlantedGardenPlants()
}