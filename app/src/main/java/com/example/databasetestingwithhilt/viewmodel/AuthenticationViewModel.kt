package com.example.databasetestingwithhilt.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databasetestingwithhilt.UiScreens.MainActivity
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.PersonalInformation
import com.example.databasetestingwithhilt.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    val firebaseAuth : FirebaseAuth,
    val authRepository: AuthRepository,
    val database : DatabaseReference
) :ViewModel(){


    private val _authState = MutableStateFlow<FirebaseUser?>(null)
    val authState: StateFlow<FirebaseUser?> = _authState

    private val _autherror = MutableStateFlow<String?>(null)
    val autherror: StateFlow<String?> = _autherror

    private val _user = MutableStateFlow<GoogleSignInAccount?>(null)
    val user:StateFlow<GoogleSignInAccount?> = _user

    var errorMessage by mutableStateOf<String?>(null)
    fun setError(message: String) {
        errorMessage = message
    }



    fun emailPasswordLogin(email : String, password: String, context: Context){
        viewModelScope.launch {
            authRepository.login(email,password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    _authState.value = authRepository.firebaseAuth.currentUser
                    val i = Intent(context.applicationContext, MainActivity::class.java)
                    context.startActivity(i)
                    (context as Activity).finish()
                }else{
                    _autherror.value = task.exception?.message
                }
            }
        }
    }

    fun register(email: String, password: String, context: Context){
        viewModelScope.launch {
            authRepository.register(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    _authState.value= authRepository.firebaseAuth.currentUser
                    val i = Intent(context.applicationContext, PersonalInformation::class.java)
                    context.startActivity(i)
                    (context as Activity).finish()
                }else{
                    _autherror.value = task.exception?.message
                }
            }
        }
    }

//    fun googleSignIn(data : Intent?){
//        viewModelScope.launch {
//
//            try {
//                val account = authRepository.getSignInAccountFromIntent(data)
//                _user.value = account
//            }catch (e :ApiException){
//                Log.e("AuthViewModel", "Google sign-in failed", e)
//            }
//        }
//    }

    fun getGoogleIntent(): Intent = authRepository.getGoogleSignInIntent()

//    fun signInWithGoogleToken(token:String, onSuccess : ()->Unit){
//        val credential = GoogleAuthProvider.getCredential(token,null)
////        authRepository.firebaseAuthWithGoogle(credential){ success, error ->
////            if (success) onSuccess else setError(error ?: "Login failed")
////        }
//    }

    fun signInWithGoogleToken(token:String, onResult: (goToMain :Boolean) -> Unit){
        val credential = GoogleAuthProvider.getCredential(token,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val user = task.result?.user
                    user?.uid?.let {uid->
                        checkUserDataExists(uid){exists ->
                            onResult(exists)
                        }

                    } ?: onResult(false)
                }else{
                    setError(task.exception?.message ?: "Login failed")
                }
            }
    }

    private fun checkUserDataExists(uid: String, onResult: (Boolean) -> Unit) {

        val userRef  = database.child("users").child(uid)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                onResult(snapshot.exists())
            }

            override fun onCancelled(error: DatabaseError) {
                onResult(false)
            }
        })
    }

    fun logout(){
        // Log.d("Gajendra", "Logout called")

        authRepository.logout()
        _authState.value=null
        // Log.d("Gajendra", "Current user: ${FirebaseAuth.getInstance().currentUser}")

    }
}