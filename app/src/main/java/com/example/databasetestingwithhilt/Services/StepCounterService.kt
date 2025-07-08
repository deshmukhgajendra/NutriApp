package com.example.databasetestingwithhilt.Services

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Builder
import com.example.databasetestingwithhilt.StepsCounter.StepRepository
import com.example.databasetestingwithhilt.StepsCounter.StepSensorManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StepCounterService: Service() {

    @Inject lateinit var notificationManager:NotificationManager
    @Inject lateinit var notificationBuilder: Builder
    @Inject lateinit var stepSensorManager: StepSensorManager
    @Inject lateinit var repository: StepRepository

    override fun onCreate() {
        super.onCreate()
        buildNotification()
        startForeground(1,buildNotification())

        stepSensorManager.startListening { steps->
            repository.updateSteps(steps)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stepSensorManager.stopListening()
    }
    override fun onBind(p0: Intent?): IBinder? = null


    fun buildNotification():Notification{
        return notificationBuilder
            .setContentTitle("Steps Counter")
            .setContentText("Steps are counting..")
            .build()
    }
}