package com.example.databasetestingwithhilt.WorkManager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.databasetestingwithhilt.Authentications.AuthRepository
import com.example.databasetestingwithhilt.Database.SleepRepository
import com.example.databasetestingwithhilt.UserViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDate


@HiltWorker
class SaveDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val viewModel: UserViewModel
   // val authrepository : AuthRepository,
   // val sleepRepository: SleepRepository
) : CoroutineWorker(appContext, workerParams) {


        @RequiresApi(Build.VERSION_CODES.O)
        override suspend fun doWork(): Result {
            return try {
                Log.d("SaveDataWorker", "Worker started")
                val currentDate = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"))
//                val record = viewModel.fetchCurrentDayData()
//                viewModel.SaveRecordsToFirebase(record, currentDate)
//                viewModel.saveSleepRecordToFirebase()
                Log.d("kaam", "doWork: work done ")
                Result.success()
            } catch (e: Exception) {
                Log.e("SaveDataWorker", "Error saving data", e)

                Result.failure()
            }
        }
    }




