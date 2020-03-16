package com.example.jetpackdemo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackdemo.database.entity.Plant
import com.example.jetpackdemo.database.repository.PlantRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

//构造参数中的plantListRepository 是数据处理类
class PlantListViewModel(private val plantRepository: PlantRepository) : ViewModel() {
    companion object {
        val TAG = PlantListViewModel::class.java.simpleName
    }


    val plantListSubject = PublishSubject.create<List<Plant>>()

    //使用Rxjava2
//    private val plants: Single<List<Plant>> by lazy {
//       // Log.d(TAG, "getPlantList")
//        plantRepository.getPlantList()
//    }

    fun getPlantsByDatabase(): Flowable<List<Plant>> =  plantRepository.getPlantList()

    fun getPlantListByDataBase(){
        val subscribe = getPlantsByDatabase()
            .subscribeOn(Schedulers.io())
            .doOnNext {
                Log.d(TAG, "database plants inited")
                //在这里发送数据
                plantListSubject.onNext(it)
            }
            .subscribe()
    }

    fun getPlantsData(): Observable<List<Plant>> = plantListSubject.hide()

    //使用LiveData
    private val allPlants: LiveData<List<Plant>> by lazy {
        plantRepository.getPlantList2()
    }

    fun getPlantsByDatabase2(): LiveData<List<Plant>> = allPlants
}