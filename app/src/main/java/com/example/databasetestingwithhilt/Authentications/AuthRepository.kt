package com.example.databasetestingwithhilt.Authentications

import com.example.databasetestingwithhilt.Database.PersonalEntity
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
}

