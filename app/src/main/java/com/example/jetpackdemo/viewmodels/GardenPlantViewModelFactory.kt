package com.example.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.database.repository.GardenPlantRepository

class GardenPlantViewModelFactory<T: ViewModel?>(private val gardenPlantRepository: GardenPlantRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GardenPlantViewModel::class.java)) {
            return GardenPlantViewModel(gardenPlantRepository) as T
        }

        throw ClassCastException("Your class is not a GardenPlantViewModel.class")
    }
}