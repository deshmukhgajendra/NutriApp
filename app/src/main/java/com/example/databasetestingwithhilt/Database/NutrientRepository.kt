package com.example.databasetestingwithhilt.Database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NutrientRepository @Inject constructor(
    private val dao: FoodDao,
    private val auth: FirebaseAuth,
    private val databaseReference: DatabaseReference
) {

    suspend fun insertFood(food: FoodEntity) = dao.insertFood(food)
    suspend fun getAllFoods() = dao.getAllFoods()
    suspend fun getTotalCalories()= dao.getCalories()
    suspend fun getTotalProtein()=dao.getProteins()
    suspend fun getTotalFats()=dao.getFats()
    suspend fun getTotalCarbs()=dao.getCarbs()

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
    suspend fun getEnergy()=dao.getEnergy()
    suspend fun getStarch()=dao.getStarch()
    suspend fun getSucrose()=dao.getSucrose()
    suspend fun getFructose()=dao.getFructose()
    suspend fun getLactose()=dao.getLactose()
    suspend fun getAlcohol()=dao.getAlcohol()
    suspend fun getCaffeine()= dao.getCaffeine()
    suspend fun getManganese()=dao.getManganese()
    suspend fun getBetaCarotene()=dao.getBeta_Carotene()
    suspend fun getLycopene()=dao.getLycopene()
   suspend fun getSaturatedFat()=dao.getSaturated_Fatty_acids()

    // required nutrients

    private suspend fun getRequiredValuesFromFirebase(key: String): Float {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            Log.d("xyz", "Current User ID: $userId")

            val userRef = databaseReference.child("users").child(userId).child("Personal_Data")
            Log.d("xyz", "Querying Database Path: users/$userId/Personal_Data")

            try {
                val snapshot = userRef.get().await()
                if (snapshot.exists()) {
                    Log.d("xyz", "Snapshot Data: ${snapshot.value}")
                    val value = snapshot.child(key).getValue(Float::class.java) ?: 0f
                    Log.d("xyz", "Fetched Value for Key $key: $value")
                    return value
                } else {
                    Log.d("xyz", "Snapshot does not exist for path: users/$userId/Personal_Data")
                }
            } catch (e: Exception) {
                Log.e("xyz", "Error fetching data", e)
            }
        } else {
            Log.e("xyz", "User is not authenticated")
        }
        return 0f
    }


    suspend fun getRequiredCalories(): Float{
        return getRequiredValuesFromFirebase("requiredCalorie")

    }

    suspend fun getRequiredProtein(): Float{
        return getRequiredValuesFromFirebase("requiredProtein")
    }

    suspend fun getRequiredFats(): Float{
        return getRequiredValuesFromFirebase("requiredFats")
    }

    suspend fun getRequiredCarbs(): Float{
        return getRequiredValuesFromFirebase("requiredCarbs")
    }

}