package com.example.jetpackdemo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackdemo.database.entity.Plant
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): Flowable<List<Plant>>

    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlantsLiveData(): List<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlants(plants: List<Plant>)

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlantById(plantId: String): Single<Plant>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlantByIdLiveData(plantId: String): LiveData<Plant>

}