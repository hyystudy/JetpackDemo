package com.example.jetpackdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.example.jetpackdemo.database.AppDataBase
import com.example.jetpackdemo.database.entity.Plant
import com.example.jetpackdemo.utilities.PLANT_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SeedDatabaseWorker(appContext: Context, workerParameters: WorkerParameters): RxWorker(appContext, workerParameters) {

    companion object {
        val TAG = SeedDatabaseWorker::class.java.simpleName
    }

    override fun createWork(): Single<Result> {
        return  Single.just(seedDatabase())
            .subscribeOn(Schedulers.io())
            .map { Result.success() }

    }

    private fun seedDatabase() {
        Log.d(TAG, "init seed database")
        Thread{
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<Plant>>() {}.type
                    val plants: List<Plant> = Gson().fromJson(jsonReader, type)

                    val plantDao = AppDataBase.getInstance(applicationContext).getPlantDao()
                    plantDao.insertAllPlants(plants = plants)

                }
            }
        }.start()
    }
}