package com.example.databasetestingwithhilt.WorkManager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.databasetestingwithhilt.repository.NutrientRepository
import com.example.databasetestingwithhilt.repository.SleepRepository
import com.example.databasetestingwithhilt.repository.StepRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class SaveDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val sleepRepository: SleepRepository,
    val nutrientRepository: NutrientRepository,
    val stepRepository: StepRepository
) : CoroutineWorker(appContext, workerParams) {


        @RequiresApi(Build.VERSION_CODES.O)
        override suspend fun doWork(): Result {
            return try {
                //  Log.d("SaveDataWorker", "Worker started")
                sleepRepository.syncAllRecordToFirebase()
                nutrientRepository.syncNutrientDataToFirebase()
                stepRepository.saveStepsRecordTofirebase()

           //     Log.d("kaam", "doWork: work done ")
                Result.success()
            } catch (e: Exception) {
                Log.e("SaveDataWorker", "Error saving data", e)

                Result.failure()
            }
        }
    }




