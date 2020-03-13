package com.example.jetpackdemo.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.jetpackdemo.database.dao.PlantDao
import com.example.jetpackdemo.database.entity.Plant
import com.example.jetpackdemo.utilities.APP_DATABASE_NAME
import com.example.jetpackdemo.workers.SeedDatabaseWorker
import com.example.jetpackdemo.workers.SeedDatabaseWorkerCoroutine

@Database(entities = [Plant::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getPlantDao(): PlantDao

    companion object {
        val TAG = AppDataBase::class.java.simpleName
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase = instance ?: synchronized(this) {
            instance ?: buildDataBase(context)
        }

        private fun buildDataBase(context: Context): AppDataBase = Room.databaseBuilder(
                context,
                AppDataBase::class.java, APP_DATABASE_NAME
            )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d(TAG, "DataBase OnCreate")

                    //创建任务 初始化plant list  rxjava2 执行任务
//                    val worker = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
//                        .addTag("SeedPlants").build()
                    //提交系统 执行任务
//                    WorkManager.getInstance(context.applicationContext).enqueue(worker)

                    val worker = OneTimeWorkRequestBuilder<SeedDatabaseWorkerCoroutine>()
                        .addTag("SeedPlants").build()
                    //提交系统 执行任务
                    WorkManager.getInstance(context.applicationContext).enqueue(worker)
                }
            }).build()
    }
}