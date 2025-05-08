package com.example.databasetestingwithhilt.Database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import java.time.LocalDate
import javax.inject.Inject

class SleepRepository @Inject constructor(
    private val sleepDao : SleepDao,
    val firebaseAuth: FirebaseAuth,
    val databaseReference: DatabaseReference
) {

    suspend fun insertSleepRecord(sleepEntity: SleepEntity)
    = sleepDao.insertSleepRecord(sleepEntity)

    suspend fun getLastSleepRecord() :SleepEntity?
    =sleepDao.getLastRecord()

    suspend fun updateWaketime(date:String , wakeTime: String)
    = sleepDao.updateWakeTime(date,wakeTime)

    suspend fun getSleepData()=sleepDao.getSleepRecord()


    suspend fun saveSleepRecordToFirebase(sleepEntity: SleepEntity) {
        val currentUser = firebaseAuth.currentUser
        val currentDate = LocalDate.now().toString()

        if (currentUser != null) {
            val userId = currentUser.uid
            val uniqueKey = databaseReference.child("users").child(userId).child("SleepRecord")
                .child(currentDate).push().key // Generate a unique key

            if (uniqueKey != null) {
                val sleepData = mapOf(
                    "Date" to sleepEntity.Date,
                    "SleepTime" to sleepEntity.SleepTime,
                    "WokeUpTime" to sleepEntity.WakeUp
                )

                databaseReference.child("users")
                    .child(userId)
                    .child("SleepRecord")
                    .child(currentDate)
                    .child(uniqueKey)
                    .setValue(sleepData)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("gajendra", "SleepRecord --> Data saved successfully")
                        } else {
                            Log.d("gajendra", "SleepRecord --> Failed to save data")
                        }
                    }
            }
        }
    }

}
