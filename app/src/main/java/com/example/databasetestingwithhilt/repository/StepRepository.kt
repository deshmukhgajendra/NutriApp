package com.example.databasetestingwithhilt.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalTime
import javax.inject.Inject

class StepRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val databaseReference: DatabaseReference
){

    private val _stepCount = MutableStateFlow(0)
    val stepCount :StateFlow<Int> =_stepCount

    fun updateSteps(count:Int){
        _stepCount.value=count
    }

    fun resetStepsCount(){
        _stepCount.value=0
    }

    fun saveStepsRecordTofirebase(){
        val currentUser = firebaseAuth.currentUser
        val currentDate = LocalTime.now().toString()

        if (currentUser != null){
            val userId = currentUser.uid

            databaseReference.child("users")
                .child(userId)
                .child("Steps-Record")
                .child(currentDate)
                .setValue(stepCount)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Log.d("gajendra", "Steps-Record --> Data saved successfully")
                        resetStepsCount()
                    }else{
                        Log.d("gajendra", "Steps-Record --> Failed to save data")

                    }
                }
        }


    }
}