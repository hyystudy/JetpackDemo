package com.example.jetpackdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
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
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorkerCoroutine(appContext: Context, workerParameters: WorkerParameters): CoroutineWorker(appContext, workerParameters) {

    companion object {
        val TAG = SeedDatabaseWorkerCoroutine::class.java.simpleName
    }

    override suspend fun doWork(): Result = coroutineScope{
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<Plant>>() {}.type
                    val plants: List<Plant> = Gson().fromJson(jsonReader, type)

                    val plantDao = AppDataBase.getInstance(applicationContext).getPlantDao()
                    plantDao.insertAllPlants(plants = plants)

                }
            }
            Result.success()
        }catch (e: Exception) {
            Log.e(TAG, "Error seeding database", e)
            Result.failure()
        }

    }
}