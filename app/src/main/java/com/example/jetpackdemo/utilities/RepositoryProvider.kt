package com.example.jetpackdemo.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.database.AppDataBase
import com.example.jetpackdemo.database.repository.GardenPlantRepository
import com.example.jetpackdemo.database.repository.PlantRepository
import com.example.jetpackdemo.viewmodels.*

object RepositoryProvider {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(
            AppDataBase.getInstance(context.applicationContext).getPlantDao()
        )
    }

    private fun getGardenPlantRepository(context: Context): GardenPlantRepository {
        return GardenPlantRepository.getInstance(
            AppDataBase.getInstance(context.applicationContext).getGardenPlantDao()
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
        val gardenPlantRepository = getGardenPlantRepository(context)
        return PlantDetailViewModelFactory(plantRepository, gardenPlantRepository, plantId)
    }

    fun getGardenPlantViewModelFactory(
        context: Context
    ): GardenPlantViewModelFactory<GardenPlantViewModel> {
        val gardenPlantRepository = getGardenPlantRepository(context)
        return GardenPlantViewModelFactory(gardenPlantRepository)
    }
}