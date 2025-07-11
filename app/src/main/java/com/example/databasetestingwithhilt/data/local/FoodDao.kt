package com.example.databasetestingwithhilt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.databasetestingwithhilt.model.FoodEntity


@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: FoodEntity)


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

    @Query("SELECT food_name FROM food_log")
    suspend fun getAllFoodName(): List<String>

    @Query("DELETE FROM FOOD_LOG WHERE food_name = :foodname ")
    suspend fun deleteFoodRecord(foodname: String)

    // nutrients

    @Query("SELECT SUM(Trans_Fatty_acids) FROM food_log")
    suspend fun getTransFat(): Float?

    @Query("SELECT SUM(Vitamin_A) FROM food_log")
    suspend fun getVitaminA(): Float?

    @Query("SELECT SUM(Vitamin_B6) FROM food_log")
    suspend fun getVitaminB6(): Float?

    @Query("SELECT SUM(Vitamin_B12) FROM food_log")
    suspend fun getVitaminB12(): Float?

    @Query("SELECT SUM(Vitamin_C) FROM food_log")
    suspend fun getVitaminC(): Float?

    @Query("SELECT SUM(Vitamin_D) FROM food_log")
    suspend fun getVitaminD(): Float?

    @Query("SELECT SUM(Vitamin_E) FROM FOOD_LOG")
    suspend fun getVitaminE(): Float?

    @Query("SELECT SUM(Vitamin_K) FROM food_log")
    suspend fun getVitaminK(): Float?

    @Query("SELECT SUM(Copper) FROM food_log")
    suspend fun getCopper(): Float?

    @Query("SELECT SUM(Zinc) FROM food_log")
    suspend fun getZinc(): Float?

    @Query("SELECT SUM(Sodium) FROM food_log")
    suspend fun getSodium(): Float?

    @Query("SELECT SUM(Potassium) FROM food_log")
    suspend fun getPotassium(): Float?

    @Query("SELECT SUM(Iron) FROM food_log")
    suspend fun getIron(): Float?

    @Query("SELECT SUM(Calcium) FROM food_log")
    suspend fun getCalcium(): Float?

    @Query("SELECT SUM(Fiber) FROM food_log")
    suspend fun getFibar(): Float?

    @Query("SELECT SUM(Sugar) FROM food_log")
    suspend fun getSuger(): Float?

    @Query("SELECT SUM(Wate) FROM food_log")
    suspend fun getWater(): Float?

    @Query("SELECT SUM(Glucose) FROM food_log")
    suspend fun getGlucose(): Float?

    @Query("SELECT SUM(Folic_acid) FROM food_log")
    suspend fun getFolicAcid(): Float?

    @Query("SELECT SUM(Niacin) FROM food_log")
    suspend fun getNiacine(): Float?

    @Query("SELECT SUM(Retinol) FROM food_log")
    suspend fun getRetinol(): Float?

    @Query("SELECT SUM(Magnesium) FROM food_log")
    suspend fun getMagnesium(): Float?

    @Query("SELECT SUM(Folate) FROM food_log")
    suspend fun getFolate(): Float?

    @Query("SELECT SUM(Cholesterol) FROM food_log")
    suspend fun getCholesterol(): Float?

    @Query("SELECT SUM(Monosaturated_Fatty_acids) FROM food_log")
    suspend fun getMonosaturatedFat(): Float?

    @Query("SELECT SUM(Polysaturated_Fatty_acids) FROM food_log")
    suspend fun getPolysaturatedFat(): Float?

    @Query("SELECT SUM(Energy) FROM food_log")
    suspend fun getEnergy(): Float?

    @Query("SELECT SUM(Phosphorus) FROM food_log")
    suspend fun getStarch(): Float?

    @Query("SELECT SUM(Sucrose) FROM food_log")
    suspend fun getSucrose(): Float?

    @Query("SELECT SUM(Fructose) FROM food_log")
    suspend fun getFructose():Float?

    @Query("SELECT SUM(Lactose) FROM food_log")
    suspend fun getLactose():Float?

    @Query("SELECT SUM(Alcohol) FROM food_log")
    suspend fun getAlcohol():Float?

    @Query("SELECT SUM(Caffeine) FROM food_log")
    suspend fun getCaffeine():Float?

    @Query("SELECT SUM(Manganese) FROM food_log")
    suspend fun getManganese():Float?

    @Query("SELECT SUM(Beta_Carotene) FROM food_log")
    suspend fun getBeta_Carotene():Float?

    @Query("SELECT SUM(Lycopene) FROM food_log")
    suspend fun getLycopene():Float?

    @Query("SELECT SUM(Saturated_Fatty_acids) FROM food_log")
    suspend fun getSaturated_Fatty_acids():Float?

}