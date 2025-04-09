package com.example.databasetestingwithhilt.Database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FoodEntity::class, PersonalEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun foodDao(): FoodDao

    companion object{
        val databaseName = "NutrientDatabase"
    }
}