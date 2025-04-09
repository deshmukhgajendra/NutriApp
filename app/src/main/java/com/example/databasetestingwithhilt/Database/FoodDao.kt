package com.example.databasetestingwithhilt.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: FoodEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonalData(personalEntity: PersonalEntity)

    @Query("SELECT * FROM food_log")
    suspend fun getAllFoods(): List<FoodEntity>

    @Query("SELECT SUM(Energy) FROM food_log")
    suspend fun getCalories(): Float?

    @Query("SELECT RequiredCalorie FROM PersonalData_table")
    suspend fun getRequiredCalories(): Float?
}