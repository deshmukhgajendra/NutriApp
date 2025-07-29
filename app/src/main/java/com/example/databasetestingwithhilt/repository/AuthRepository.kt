package com.example.databasetestingwithhilt.repository

import android.content.Context
import android.content.Intent
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.model.PersonalEntity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class AuthRepository @Inject constructor(
     val firebaseAuth: FirebaseAuth,
     val databaseReference: DatabaseReference,
    val context: Context,
    val googleSignInClient : GoogleSignInClient
){

//   private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(context.getString(R.string.default_web_client_id))
//        .requestEmail()
//        .build()
//
//   private val googleSignInClient = GoogleSignIn.getClient(context,gso)

//    fun getSignInIntent(): Intent = googleSignInClient.signInIntent

//    fun getSignInAccountFromIntent(data :Intent?):GoogleSignInAccount{
//        return GoogleSignIn.getSignedInAccountFromIntent(data)
//            .getResult(ApiException::class.java)
//    }


    fun getGoogleSignInIntent(): Intent = googleSignInClient.signInIntent

    fun firebaseAuthWithGoogle(credential: AuthCredential, onResult: (Boolean,String?)->Unit ){
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    val user = task.result?.user
                    onResult(true,null)
                }else{
                    onResult(false, task.exception?.message)
                }
            }
    }


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



}

