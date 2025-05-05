package com.example.databasetestingwithhilt.WorkManager

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.databasetestingwithhilt.UserViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDate


@HiltWorker
class SaveDataWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val viewModel: UserViewModel
) : CoroutineWorker(context, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val currentDate = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            val record = viewModel.fetchCurrentDayData() // Replace with appropriate logic
            viewModel.SaveRecordsToFirebase(record, currentDate)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
