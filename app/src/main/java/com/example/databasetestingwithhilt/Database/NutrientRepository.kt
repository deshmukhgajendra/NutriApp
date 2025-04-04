package com.example.databasetestingwithhilt.Database

import javax.inject.Inject

class NutrientRepository @Inject constructor(
    private val dao: FoodDao
) {

    suspend fun insertFood(food: FoodEntity) = dao.insertFood(food)
    suspend fun getAllFoods() = dao.getAllFoods()
    suspend fun getTotalCalories()= dao.getCalories()

    suspend fun insertPersonalData(personalEntity: PersonalEntity)
    = dao.insertPersonalData(personalEntity)
}