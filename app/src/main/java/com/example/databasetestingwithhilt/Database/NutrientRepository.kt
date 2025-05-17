package com.example.databasetestingwithhilt.Database

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import javax.inject.Inject

class NutrientRepository @Inject constructor(
    private val dao: FoodDao,
    private val auth: FirebaseAuth,
    private val databaseReference: DatabaseReference
) {

    suspend fun insertFood(food: FoodEntity) = dao.insertFood(food)
    suspend fun getAllFoods() = dao.getAllFoods()
    suspend fun getTotalCalories() = dao.getCalories()
    suspend fun getTotalProtein() = dao.getProteins()
    suspend fun getTotalFats() = dao.getFats()
    suspend fun getTotalCarbs() = dao.getCarbs()

    suspend fun getAllFoodName(): List<String> {
        return dao.getAllFoodName()
    }

    suspend fun deleteFoodRecord(foodname: String) {
        dao.deleteFoodRecord(foodname)
    }

    // nutrients

    suspend fun getTransFat() = dao.getTransFat()
    suspend fun getVitaminA() = dao.getVitaminA()
    suspend fun getVitaminB6() = dao.getVitaminB6()
    suspend fun getVitaminB12() = dao.getVitaminB12()
    suspend fun getVitaminC() = dao.getVitaminC()
    suspend fun getVitaminD() = dao.getVitaminD()
    suspend fun getVitaminE() = dao.getVitaminE()
    suspend fun getVitaminK() = dao.getVitaminK()
    suspend fun getCopper() = dao.getCopper()
    suspend fun getZinc() = dao.getZinc()
    suspend fun getSodium() = dao.getSodium()
    suspend fun getPotassium() = dao.getPotassium()
    suspend fun getIron() = dao.getIron()
    suspend fun getCalcium() = dao.getCalcium()
    suspend fun getFibar() = dao.getFibar()
    suspend fun getSuger() = dao.getSuger()
    suspend fun getWater() = dao.getWater()
    suspend fun getGlucose() = dao.getGlucose()
    suspend fun getFolicAcid() = dao.getFolicAcid()
    suspend fun getNiacine() = dao.getNiacine()
    suspend fun getRetinol() = dao.getRetinol()
    suspend fun getMagnesium() = dao.getMagnesium()
    suspend fun getFolate() = dao.getFolate()
    suspend fun getCholestrol() = dao.getCholesterol()
    suspend fun getMonosaturatedFat() = dao.getMonosaturatedFat()
    suspend fun getPolysatiratedFat() = dao.getPolysaturatedFat()
    suspend fun getEnergy() = dao.getEnergy()
    suspend fun getStarch() = dao.getStarch()
    suspend fun getSucrose() = dao.getSucrose()
    suspend fun getFructose() = dao.getFructose()
    suspend fun getLactose() = dao.getLactose()
    suspend fun getAlcohol() = dao.getAlcohol()
    suspend fun getCaffeine() = dao.getCaffeine()
    suspend fun getManganese() = dao.getManganese()
    suspend fun getBetaCarotene() = dao.getBeta_Carotene()
    suspend fun getLycopene() = dao.getLycopene()
    suspend fun getSaturatedFat() = dao.getSaturated_Fatty_acids()

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


    suspend fun getRequiredCalories(): Float {
        return getRequiredValuesFromFirebase("requiredCalorie")

    }

    suspend fun getRequiredProtein(): Float {
        return getRequiredValuesFromFirebase("requiredProtein")
    }

    suspend fun getRequiredFats(): Float {
        return getRequiredValuesFromFirebase("requiredFats")
    }

    suspend fun getRequiredCarbs(): Float {
        return getRequiredValuesFromFirebase("requiredCarbs")
    }

 suspend fun syncNutrientDataToFirebase(){
        try {

            val Protein = dao.getProteins()
            val Calorie =dao.getCalories()
            val Carbohydrate = dao.getCarbs()
            val Fats = dao.getFats()
            val VitaminA =dao.getVitaminA()
            val VitaminB6 = dao.getVitaminB6()
            val VitaminB12 = dao.getVitaminB12()
            val VitaminC = dao.getVitaminC()
            val VitaminD = dao.getVitaminD()
            val VitaminE = dao.getVitaminE()
            val VitaminK = dao.getVitaminK()
            val Energy =dao.getEnergy()
            val Iron = dao.getIron()
            val Starch =dao.getStarch()
            val sucrose = dao.getSucrose()
            val Fructose = dao.getFructose()
            val Glucose = dao.getGlucose()
            val Lactose = dao.getLactose()
            val Alcohol = dao.getAlcohol()
            val Water = dao.getWater()
            val zinc = dao.getZinc()
            val suger = dao.getSuger()
            val calcium =dao.getCalcium()
            val magnesium = dao.getMagnesium()
            val potasium = dao.getPotassium()
            val sodium =dao.getSodium()
            val copper =dao.getCopper()
            val magnese =dao.getManganese()
            val retinol = dao.getRetinol()
            val lycopene = dao.getLycopene()
            val betacarotine = dao .getBeta_Carotene()
            val folicAcid = dao.getFolicAcid()
            val trasfat = dao.getTransFat()
            val polyfat = dao.getPolysaturatedFat()
            val monofat = dao.getMonosaturatedFat()
            val colestrol = dao.getCholesterol()
            val caffene = dao.getCaffeine()
            val fiber = dao.getFibar()
            val folate = dao.getFolate()


            val nutrientRecord = mapOf(
                "Protein" to Protein,
                "Calories" to Calorie,
                "Carbohydrate" to Carbohydrate,
                "Fats" to Fats,
                "VitaminA" to VitaminA,
                "VitaminB6" to VitaminB6,
                "VitaminB12" to VitaminB12,
                "VitaminC" to VitaminC,
                "VitaminD" to VitaminD,
                "VitaminE" to VitaminE,
                "Vitamink" to VitaminK,
                "Energy" to Energy,
                "Iron" to Iron,
                "Starch" to Starch,
                "Sucrose" to sucrose,
                "Fructose" to Fructose,
                "Glucose" to Glucose,
                "Lactose" to Lactose,
                "Alcohol" to Alcohol,
                "Water" to Water,
                "Zinc" to zinc,
                "Suger" to suger,
                "Calcium" to calcium,
                "Magnesium" to magnesium,
                "Potassium" to potasium,
                "Sodium" to sodium,
                "Copper" to copper,
                "Magnese" to magnese,
                "Retinol" to retinol,
                "Lycopene" to lycopene,
                "Beta_Carotene" to betacarotine,
                "folicAcid" to folicAcid,
                "TrasFat" to trasfat,
                "PolysaturatedFat" to polyfat,
                "MonosaturatedFat" to monofat,
                "Cholesterol" to colestrol,
                "Caffeine" to caffene,
                "Fiber" to fiber,
                "Folate" to folate

            )

            saveRecordToFirebase(nutrientRecord)
        }catch (e : Exception){

        }
    }

    fun saveRecordToFirebase(
        nutrientRecord: Map<String , Float?>,
        ) {
        val currentUser = auth.currentUser
        val CurrentDate = LocalDate.now().toString()

        if (currentUser != null) {
            val userId = currentUser.uid
            Log.d("gajendra", "saveUserData: $userId")

            databaseReference.child("users").child(userId).child("NutritionRecord")
                .child(CurrentDate)
                .setValue(nutrientRecord)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("gajendra", "Data saved successfully")

                    } else {
                        Log.e("gajendra", "Error saving data", task.exception)

                    }
                }
        }
    }

    suspend fun getProteinValue(date: String): Float {
        val user = auth.currentUser
            ?: throw Exception("User not authenticated")

        return try {
            val snapshot = FirebaseDatabase.getInstance()
                .getReference("users/${user.uid}/NutritionRecord/$date/protein")
                .get()
                .await()

            snapshot.getValue(Float::class.java) ?: 0f
        } catch (e: Exception) {
            throw Exception("Failed to fetch protein: ${e.message}")
        }
    }

    suspend fun getFatsValue(date : String) : Float {
        val user = auth.currentUser
            ?: throw Exception("User not authenticated")

        return try {
            val snapshot = FirebaseDatabase.getInstance()
                .getReference("users/${user.uid}/NutritionRecord/$date/fats")
                .get()
                .await()

            snapshot.getValue(Float::class.java) ?:0f
        }catch (e : Exception){
            throw Exception("Failed to fetch protein: ${e.message}")
        }
    }

    suspend fun getCarbsValue(date : String) : Float {
        val user= auth.currentUser
            ?: throw Exception("User not authenticated")

        return try {
            val snapshot = FirebaseDatabase.getInstance()
                .getReference("users/${user.uid}/NutritionRecord/$date/carbohydrate")
                .get()
                .await()
            snapshot.getValue(Float::class.java) ?:0f
        }catch (e : Exception){
            throw Exception("Failed to fetch protein: ${e.message}")
        }
    }

    suspend fun getCalorieValue(date : String) : Float {
        val user = auth.currentUser
            ?: throw Exception("User not authenticated")

        return try {
            val snapshot = FirebaseDatabase.getInstance()
                .getReference("users/${user.uid}/NutritionRecord/$date/energy")
                .get()
                .await()
            snapshot.getValue(Float::class.java) ?:0f
        }catch (e : Exception){
            throw Exception("Failed to fetch protein: ${e.message}")
        }
    }
}

