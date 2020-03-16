package com.example.jetpackdemo.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PlantAndGardenPlantings(
    @Embedded
    val plant: Plant,
    @Relation(parentColumn = "id", entityColumn = "plant_id")
    val gardenPlantings: List<GardenPlant> = emptyList()
)