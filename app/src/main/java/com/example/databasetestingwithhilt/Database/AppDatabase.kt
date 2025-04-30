package com.example.databasetestingwithhilt.Database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FoodEntity::class, SleepEntity::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun foodDao(): FoodDao
    abstract fun sleepDao(): SleepDao

    companion object{
        val databaseName = "NutrientDatabase"
    }
}