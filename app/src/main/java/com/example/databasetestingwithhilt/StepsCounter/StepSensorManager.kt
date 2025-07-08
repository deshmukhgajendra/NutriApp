package com.example.databasetestingwithhilt.StepsCounter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import javax.inject.Inject

class StepSensorManager @Inject constructor(

    val context: Context

): SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var stepSensor: Sensor? = null
    private var onStepCountChanged:((Int) -> Unit)? = null

    fun startListening(onStepUpdate : (Int)-> Unit){
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        this.onStepCountChanged = onStepUpdate

        stepSensor?.let {
            sensorManager?.registerListener(this,it,SensorManager.SENSOR_DELAY_UI)
        }
    }

    fun stopListening(){

        sensorManager?.unregisterListener(this)
    }


    override fun onSensorChanged(event: SensorEvent?) {
       if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER){
           val steps = event.values[0].toInt()
           onStepCountChanged?.invoke(steps)
       }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}