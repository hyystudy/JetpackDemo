package com.example.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.database.repository.PlantRepository

class PlantDetailViewModelFactory<T>(
    private val plantRepository: PlantRepository,
    private val plantId: String
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, plantId) as T
    }
}