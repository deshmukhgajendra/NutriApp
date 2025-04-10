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

    @Query("SELECT SUM(Protein) FROM food_log")
    suspend fun getProteins(): Float?

    @Query("SELECT SUM(Monosaturated_Fatty_acids + \n" +
            "        Polysaturated_Fatty_acids + \n" +
            "        Oleic_acid + \n" +
            "        Palmitoleic_acid + \n" +
            "        Linoleic_acid + \n" +
            "        Alpha_Linolenic_acid + \n" +
            "        DHA + \n" +
            "        cis_Vaccenic_acid) FROM food_log")
    suspend fun getFats(): Float?

    @Query("SELECT SUM(Carbohydrate + \n" +
            "     Starch + \n" +
            "     Sucrose + \n" +
            "     Glucose + \n" +
            "     Fructose + \n" +
            "     Lactose + \n" +
            "     Maltose + \n" +
            "     Sugar + \n" +
            "     Fiber) FROM food_log")
    suspend fun getCarbs(): Float?

    @Query("SELECT RequiredCalorie FROM PersonalData_table")
    suspend fun getRequiredCalories(): Float?

    @Query(" SELECT RequiredProtein FROM PersonalData_table ")
    suspend fun getRequiredProteins(): Float?

    @Query("SELECT RequiredFats FROM PersonalData_table")
    suspend fun getRequiredFats(): Float?

    @Query("SELECT RequiredCarbs FROM PersonalData_table")
    suspend fun getRequiredCarbs(): Float?
}