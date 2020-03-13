package com.example.jetpackdemo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackdemo.database.entity.Plant
import com.example.jetpackdemo.database.repository.PlantRepository
import io.reactivex.Flowable

//构造参数中的plantListRepository 是数据处理类
class PlantListViewModel(private val plantRepository: PlantRepository) : ViewModel() {
    companion object {
        val TAG = PlantListViewModel::class.java.simpleName
    }

    //使用Rxjava2
    private val plants: Flowable<List<Plant>> by lazy {
        Log.d(TAG, "getPlantList")
        plantRepository.getPlantList()
    }

    fun getPlantsByDatabase(): Flowable<List<Plant>> = plants


    //使用LiveData
    private val allPlants: LiveData<List<Plant>> by lazy {
        plantRepository.getPlantList2()
    }

    fun getPlantsByDatabase2(): LiveData<List<Plant>> = allPlants
}