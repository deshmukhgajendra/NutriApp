package com.example.databasetestingwithhilt

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.example.databasetestingwithhilt.WorkManager.SaveDataWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory


    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    override fun onCreate() {
        super.onCreate()
        scheduleTestWork()
    }

    private fun scheduleTestWork() {
        val workRequest = PeriodicWorkRequestBuilder<SaveDataWorker>(15, TimeUnit.MINUTES) // For testing, minimum periodic time is 15 minutes.
            .setInitialDelay(1, TimeUnit.MINUTES) // Set a short initial delay for testing
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SaveDataWorker",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }
}
