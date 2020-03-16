package com.example.jetpackdemo.database.entity

import androidx.room.*


//外键  索引
@Entity(
    tableName = "garden_plants", foreignKeys = [
        ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])],
    indices = [Index("plant_id")]
)
data class GardenPlant(
    @ColumnInfo(name = "plant_id") val plantId: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}