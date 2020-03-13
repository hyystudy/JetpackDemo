package com.example.jetpackdemo.database.repository

import com.example.jetpackdemo.database.dao.PlantDao

/**
 * Repository module for handling data operations.
 * 仓库类是用来出来对应实体类的操作逻辑  此处为和roomDataBase打交道
 */
class PlantRepository private constructor(private val plantDao: PlantDao) {

    //rxjava2 模式
    fun getPlantList() = plantDao.getPlants()

    fun getPlantById(plantId: String) = plantDao.getPlantById(plantId)

    //liveData 模式
    fun getPlantList2() = plantDao.getPlantsLiveData()

    fun getPlantByIdLiveData(plantId: String) = plantDao.getPlantByIdLiveData(plantId)

    companion object {

        @Volatile private var instance: PlantRepository? = null

        fun getInstance(plantDao: PlantDao) = instance ?: synchronized(this) {
            instance ?: PlantRepository(plantDao).also { instance = it }
        }
    }
}