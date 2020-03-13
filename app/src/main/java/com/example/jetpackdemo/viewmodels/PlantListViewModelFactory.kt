package com.example.jetpackdemo.viewmodels

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.jetpackdemo.database.repository.PlantRepository

class PlantListViewModelFactory(
    private val plantRepository: PlantRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    companion object {
        private val TAG = PlantListViewModelFactory::class.java.simpleName
    }
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {

        Log.d(TAG, "create  plantListViewModel")
        return PlantListViewModel(plantRepository) as T
    }


}