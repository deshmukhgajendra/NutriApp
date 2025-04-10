package com.example.databasetestingwithhilt.Database

import javax.inject.Inject

class NutrientRepository @Inject constructor(
    private val dao: FoodDao
) {

    suspend fun insertFood(food: FoodEntity) = dao.insertFood(food)
    suspend fun getAllFoods() = dao.getAllFoods()
    suspend fun getTotalCalories()= dao.getCalories()
    suspend fun getTotalProtein()=dao.getProteins()
    suspend fun getTotalFats()=dao.getFats()
    suspend fun getTotalCarbs()=dao.getCarbs()

    suspend fun insertPersonalData(personalEntity: PersonalEntity)
    = dao.insertPersonalData(personalEntity)

    suspend fun getRequiredCalories()=dao.getRequiredCalories()
    suspend fun getRequiredProteins()=dao.getRequiredProteins()
    suspend fun getRequiredFats()=dao.getRequiredFats()
    suspend fun getRequiredCarbs()=dao.getRequiredCarbs()
}