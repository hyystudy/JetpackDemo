package com.example.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jetpackdemo.database.repository.GardenPlantRepository

class GardenPlantViewModel(private val gardenPlantRepository: GardenPlantRepository) : ViewModel() {

    fun getGardenPlants() = gardenPlantRepository.getGardenPlants()

}