package com.abhi.runningtracker.data

import android.hardware.Sensor
import android.hardware.SensorManager
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodayRunningRepository @Inject constructor(
    private val sensorManager: SensorManager,
    private val stepCounterSensor : Sensor
) : RunningRepository {

    
    override suspend fun getSteps()  = suspendCancellableCoroutine{ cancellableContinuation ->  

    }

}