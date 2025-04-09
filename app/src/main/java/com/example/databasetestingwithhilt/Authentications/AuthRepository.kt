package com.example.databasetestingwithhilt.Authentications

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepository @Inject constructor(
     val firebaseAuth: FirebaseAuth
){
    fun register (email : String, password : String): Task<AuthResult>{
        return firebaseAuth.createUserWithEmailAndPassword(email,password)
    }

    fun login (email: String,password: String): Task<AuthResult>{
        return firebaseAuth.signInWithEmailAndPassword(email,password)
    }

    fun logout(){
        firebaseAuth.signOut()
    }

    fun isUserLoggedIn() : Boolean{
        return firebaseAuth.currentUser != null
    }

    fun getUserId(): String?{
        return firebaseAuth.currentUser?.uid
    }
}