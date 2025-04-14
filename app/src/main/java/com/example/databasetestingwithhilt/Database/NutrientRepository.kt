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
    suspend fun getAllFoodName(): List<String> {
        return dao.getAllFoodName()
    }

    suspend fun deleteFoodRecord(foodname: String){
        dao.deleteFoodRecord(foodname)
    }
    
    // nutrients
    
    suspend fun getTransFat()= dao.getTransFat()
    suspend fun getVitaminA()= dao.getVitaminA()
    suspend fun getVitaminB6()= dao.getVitaminB6()
    suspend fun getVitaminB12()=dao.getVitaminB12()
    suspend fun getVitaminC()=dao.getVitaminC()
    suspend fun getVitaminD()=dao.getVitaminD()
    suspend fun getVitaminE()=dao.getVitaminE()
    suspend fun getVitaminK()=dao.getVitaminK()
    suspend fun getCopper()=dao.getCopper()
    suspend fun getZinc()=dao.getZinc()
    suspend fun getSodium()=dao.getSodium()
    suspend fun getPotassium()=dao.getPotassium()
    suspend fun getIron()=dao.getIron()
    suspend fun getCalcium()=dao.getCalcium()
    suspend fun getFibar()=dao.getFibar()
    suspend fun getSuger()=dao.getSuger()
    suspend fun getWater()=dao.getWater()
    suspend fun getGlucose()=dao.getGlucose()
    suspend fun getFolicAcid()=dao.getFolicAcid()
    suspend fun getNiacine()=dao.getNiacine()
    suspend fun getRetinol()=dao.getRetinol()
    suspend fun getMagnesium()=dao.getMagnesium()
    suspend fun getFolate()=dao.getFolate()
    suspend fun getCholestrol()=dao.getCholesterol()
    suspend fun getMonosaturatedFat()=dao.getMonosaturatedFat()
    suspend fun getPolysatiratedFat()=dao.getPolysaturatedFat()
}