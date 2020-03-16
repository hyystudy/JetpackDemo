package com.example.jetpackdemo.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.jetpackdemo.database.dao.PlantDao
import com.example.jetpackdemo.database.entity.GardenPlant
import com.example.jetpackdemo.database.entity.Plant
import com.example.jetpackdemo.utilities.APP_DATABASE_NAME
import com.example.jetpackdemo.workers.SeedDatabaseWorker
import com.example.jetpackdemo.workers.SeedDatabaseWorkerCoroutine

@Database(entities = [Plant::class, GardenPlant::class], version = 2, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getPlantDao(): PlantDao

    companion object {
        val TAG = AppDataBase::class.java.simpleName
        @Volatile
        private var instance: AppDataBase? = null

        private val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `garden_plants` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `plant_id` TEXT NOT NULL, FOREIGN KEY(`plant_id`) REFERENCES `plants`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
                database.execSQL("CREATE INDEX IF NOT EXISTS `index_garden_plants_plant_id` ON `garden_plants` (`plant_id`)")
            }

        }

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
            })
            .addMigrations(migration_1_2)
            .build()
    }
}