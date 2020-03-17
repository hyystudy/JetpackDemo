package com.example.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackdemo.database.repository.GardenPlantRepository
import com.example.jetpackdemo.database.repository.PlantRepository
import kotlinx.coroutines.launch

//ViewModel 用于UI更新数据  ViewModel的数据是由repository提供  repository的数据是从本地 或者网络拉去 或者缓存当中取
class PlantDetailViewModel(
    private val plantRepository: PlantRepository,
    private val gardenPlantRepository: GardenPlantRepository,
    private val plantId: String
) : ViewModel() {

    //get plant from database by plantId
    //rxjava
//    val plant = plantRepository.getPlantById(plantId)

    //rxJava
    val plant = plantRepository.getPlantByIdLiveData(plantId)


    val isPlanted = gardenPlantRepository.isPlanted(plantId)

    //add plant to Garden
    fun addPlantToGarden() {
        viewModelScope.launch{
            gardenPlantRepository.createGardenPlantByPlantId(plantId)
        }
    }
}