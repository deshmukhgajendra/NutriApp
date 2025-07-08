package com.example.databasetestingwithhilt.util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.databasetestingwithhilt.data.local.FoodDao
import com.example.databasetestingwithhilt.data.local.SleepDao
import com.example.databasetestingwithhilt.model.FoodEntity
import com.example.databasetestingwithhilt.model.SleepEntity


@Database(entities = [FoodEntity::class, SleepEntity::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun foodDao(): FoodDao
    abstract fun sleepDao(): SleepDao

    companion object{
        val databaseName = "NutrientDatabase"
    }
}