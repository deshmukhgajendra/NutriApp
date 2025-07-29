package com.example.databasetestingwithhilt.repository

import android.util.Log
import com.example.databasetestingwithhilt.data.local.FoodDao
import com.example.databasetestingwithhilt.model.PersonalEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    private val dao: FoodDao,
    val firebaseAuth: FirebaseAuth,
    val databaseReference: DatabaseReference
) {

    fun getCurrentUserName(onResult: (String?) -> Unit) {
        val uid = firebaseAuth.currentUser?.uid
        if (uid != null) {
            val nameRef = databaseReference.child("users").child(uid)
                .child("Personal_Data")
                .child("name")

            nameRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = snapshot.getValue(String::class.java)
                    onResult(name) // pass value back to caller
                }

                override fun onCancelled(error: DatabaseError) {
                    onResult(null)
                }
            })
        } else {
            onResult(null)
        }
    }

    fun getCurrenUsertEmail(): String?{
        return firebaseAuth.currentUser?.email
    }

    fun getUserId(): String?{
        return firebaseAuth.currentUser?.uid
    }

    fun saveUserData(personalEntity: PersonalEntity, onComplete:(Boolean) ->Unit){

        val currentUser = firebaseAuth.currentUser
        if (currentUser != null){
            val userId = currentUser.uid
            //   Log.d("gajendra", "saveUserData: $userId")
            databaseReference.child("users").child(userId).child("Personal_Data")
                .setValue(personalEntity)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                         // Log.d("gajendra", "Data saved successfully")
                        onComplete(true)
                    } else {
                         // Log.e("gajendra", "Error saving data", task.exception)
                        onComplete(false)
                    }
                }
        }else{
            onComplete(false)
        }
    }

    // required nutrients

    private suspend fun getRequiredValuesFromFirebase(key: String): Float {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
           // Log.d("xyz", "Current User ID: $userId")

            val userRef = databaseReference.child("users").child(userId).child("Personal_Data")
          //  Log.d("xyz", "Querying Database Path: users/$userId/Personal_Data")

            try {
                val snapshot = userRef.get().await()
                if (snapshot.exists()) {
                 //   Log.d("xyz", "Snapshot Data: ${snapshot.value}")
                    val value = snapshot.child(key).getValue(Float::class.java) ?: 0f
                  //  Log.d("xyz", "Fetched Value for Key $key: $value")
                    return value
                } else {
                  //  Log.d("xyz", "Snapshot does not exist for path: users/$userId/Personal_Data")
                }
            } catch (e: Exception) {
             //   Log.e("xyz", "Error fetching data", e)
            }
        } else {
           // Log.e("xyz", "User is not authenticated")
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
        val currentUser = firebaseAuth.currentUser
        val CurrentDate = LocalDate.now().toString()

        if (currentUser != null) {
            val userId = currentUser.uid
          //  Log.d("gajendra", "saveUserData: $userId")

            databaseReference.child("users").child(userId).child("NutritionRecord")
                .child(CurrentDate)
                .setValue(nutrientRecord)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                      //  Log.d("gajendra", "Data saved successfully")

                    } else {
                       // Log.e("gajendra", "Error saving data", task.exception)

                    }
                }
        }
    }

    suspend fun getProteinValue(date: String): Float {
        val user = firebaseAuth.currentUser
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
        val user = firebaseAuth.currentUser
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
        val user= firebaseAuth.currentUser
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
        val user = firebaseAuth.currentUser
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