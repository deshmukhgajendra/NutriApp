package com.example.databasetestingwithhilt.Authentications

import android.util.Log
import com.example.databasetestingwithhilt.Database.PersonalEntity
import com.example.databasetestingwithhilt.ui.theme.fire
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class AuthRepository @Inject constructor(
     val firebaseAuth: FirebaseAuth,
     val databaseReference: DatabaseReference
){
    fun register (email : String, password : String): Task<AuthResult>{
        return firebaseAuth.createUserWithEmailAndPassword(email,password)
    }

    fun login (email: String,password: String): Task<AuthResult>{
        return firebaseAuth.signInWithEmailAndPassword(email,password)
    }

    fun logout(){
       // Log.d("Gajendra", "Logging out repository")

        firebaseAuth.signOut()
       // Log.d("Gajendra", "User logged out")

    }

    fun getCurrentUserName(): String?{
        return firebaseAuth.currentUser?.displayName
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
                      //  Log.d("gajendra", "Data saved successfully")
                        onComplete(true)
                    } else {
                      //  Log.e("gajendra", "Error saving data", task.exception)
                        onComplete(false)
                    }
                }
        }else{
            onComplete(false)
        }
    }

    fun saveRecordToFirebase(nutrientRecord: NutrientRecord,CurrentDate:String, onComplete: (Boolean) -> Unit){

        val currentUser = firebaseAuth.currentUser
        if(currentUser != null){
            val userId = currentUser.uid
            Log.d("gajendra", "saveUserData: $userId")

            databaseReference.child("users").child(userId).child("NutritionRecord").child(CurrentDate)
                .setValue(nutrientRecord)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Log.d("gajendra", "Data saved successfully")

                        onComplete(true)
                    }else{
                        Log.e("gajendra", "Error saving data", task.exception)

                        onComplete(false)
                    }
                }
        }else{
            onComplete(false)
        }
    }
}

data class NutrientRecord(

    val Protein:Float,
    val Carbohydrate:Float,
    val Energy:Float,
    val Starch:Float,
    val Sucrose:Float,
    val Glucose:Float,
    val Fructose:Float,
    val Lactose:Float,
    val Alcohol:Float,
    val Wate:Float,
    val Caffeine:Float,
    val Sugar:Float,
    val Fiber:Float,
    val Calcium:Float,
    val Iron:Float,
    val Magnesium:Float,
    val Potassium:Float,
    val Sodium:Float,
    val Zinc:Float,
    val Copper:Float,
    val Manganese:Float,
    val Vitamin_A:Float,
    val Retinol:Float,
    val Beta_Carotene:Float,
    val Vitamin_E:Float,
    val Vitamin_D:Float,
    val Lycopene:Float,
    val Vitamin_C:Float,
    val Niacin:Float,
    val Vitamin_B6:Float,
    val Folate:Float,
    val Vitamin_B12:Float,
    val Vitamin_K:Float,
    val Folic_acid:Float,
    val Cholesterol:Float,
    val Trans_Fatty_acids:Float,
    val Saturated_Fatty_acids:Float,
    val Monosaturated_Fatty_acids:Float,
    val Polysaturated_Fatty_acids:Float
)