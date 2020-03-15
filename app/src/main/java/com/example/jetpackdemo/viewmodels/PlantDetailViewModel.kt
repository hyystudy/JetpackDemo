package com.example.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jetpackdemo.database.repository.PlantRepository

//ViewModel 用于UI更新数据  ViewModel的数据是由repository提供  repository的数据是从本地 或者网络拉去 或者缓存当中取
class PlantDetailViewModel(
    private val plantRepository: PlantRepository,
    private val plantId: String
) : ViewModel() {

    //get plant from database by plantId
    //rxjava
//    val plant = plantRepository.getPlantById(plantId)

    //rxJava
    val plant = plantRepository.getPlantByIdLiveData(plantId)

}