package com.example.databasetestingwithhilt.Database

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    suspend fun getSleepData(): List<SleepEntity>{
        return sleepDao.getSleepRecord()
    }


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

   suspend fun syncAllRecordToFirebase(){
       val sleepRecords = getSleepData() ?: emptyList() // Handle null case
       for (record in sleepRecords){
           saveSleepRecordToFirebase(record)
       }
    }

//    suspend fun fetchSingleSleepData(date: String): Pair<Float, Float>? {
//        return suspendCoroutine { continuation ->
//            val currentUser = firebaseAuth.currentUser
//            if (currentUser == null) {
//                Log.e("FetchSleepData", "User not logged in")
//                continuation.resume(null)
//                return@suspendCoroutine
//            }
//
//            val userId = currentUser.uid
//            databaseReference.child("users")
//                .child(userId)
//                .child("SleepRecord")
//                .child(date)
//                .addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        try {
//                            val sleepData = snapshot.getValue(SleepData::class.java)
//                                ?: SleepData(date, "0", "0")
//                            val sleepTime = convertTimeToFloat(sleepData.SleepTime)
//                            val wokeUpTime = convertTimeToFloat(sleepData.WokeUpTime)
//                            continuation.resume(Pair(sleepTime, wokeUpTime))
//                        } catch (e: Exception) {
//                            Log.e("ConversionError", "Error converting time: ${e.message}", e)
//                            continuation.resume(null)
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        Log.e("FetchSleepData", "Error: ${error.message}", error.toException())
//                        continuation.resume(null)
//                    }
//                })
//        }
//    }
//
//
//
//    fun convertTimeToFloat(time: String): Float {
//        val timeFormat = if (time.contains("AM") || time.contains("PM")) "hh:mma" else "HH:mm"
//        val formatter = java.time.format.DateTimeFormatter.ofPattern(timeFormat, java.util.Locale.ENGLISH)
//        val localTime = java.time.LocalTime.parse(time.uppercase(), formatter)
//        return localTime.hour + (localTime.minute / 60f)
//    }

    suspend fun getSleepData(date: String): Pair<Float, Float> {
        val database = FirebaseDatabase.getInstance()
        return try {
            val snapshot = database.getReference("SleepRecord/$date")
                .get()
                .await()

            // Debug logging
            Log.d("FirebaseData", "Snapshot: ${snapshot.value}")

            val sleepTimeStr = snapshot.child("SleepTime").getValue(String::class.java)
                ?: throw Exception("SleepTime not found")
            val wakeTimeStr = snapshot.child("WokeUpTime").getValue(String::class.java)
                ?: throw Exception("WokeUpTime not found")

            Pair(
                convertTimeToFloat(sleepTimeStr),
                convertTimeToFloat(wakeTimeStr)
            )
        } catch (e: Exception) {
            Log.e("SleepRepository", "Error fetching data", e)
            throw e
        }
    }

    private fun convertTimeToFloat(timeString: String): Float {
        return try {
            // Handle "03:08 PM" format
            val timeParts = timeString.split(" ")
            if (timeParts.size != 2) throw Exception("Invalid time format")

            val (time, period) = timeParts
            val (hours, minutes) = time.split(":").map { it.toInt() }

            var total = hours.toFloat()
            if (period == "PM" && hours != 12) total += 12
            if (period == "AM" && hours == 12) total = 0f

            total + (minutes.toFloat() / 60)
        } catch (e: Exception) {
            Log.e("TimeConversion", "Error converting $timeString", e)
            0f
        }
    }

}

data class SleepData(
    val date :String="",
    val SleepTime : String="",
    val WokeUpTime: String=""
)