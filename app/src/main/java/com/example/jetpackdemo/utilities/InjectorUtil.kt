package com.example.jetpackdemo.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.database.AppDataBase
import com.example.jetpackdemo.database.repository.PlantRepository
import com.example.jetpackdemo.viewmodels.PlantDetailViewModel
import com.example.jetpackdemo.viewmodels.PlantDetailViewModelFactory
import com.example.jetpackdemo.viewmodels.PlantListViewModelFactory

object InjectorUtil {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(
            AppDataBase.getInstance(context.applicationContext).getPlantDao()
        )
    }

    fun getPlantListViewModel(fragment: Fragment): PlantListViewModelFactory {
        val plantListRepository = getPlantRepository(fragment.requireContext())

        return PlantListViewModelFactory(plantListRepository, fragment)
    }

    fun getPlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory<PlantDetailViewModel> {
        val plantRepository = getPlantRepository(context)
        return PlantDetailViewModelFactory(plantRepository, plantId)
    }
}