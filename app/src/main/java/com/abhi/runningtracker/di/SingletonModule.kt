package com.abhi.runningtracker.di

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.core.content.getSystemService
import com.abhi.runningtracker.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {


    @Provides
    @Singleton
    fun providesSensorManager(@ApplicationContext context: Context): SensorManager {
        val sensorManager =
            context.applicationContext.getSystemService<SensorManager>() as SensorManager
        return sensorManager
    }

    @Provides
    @Singleton
    fun providesStepCounterSensor(sensorManager: SensorManager): Sensor? {
        val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        return stepCounterSensor
    }

    @Provides
    @Singleton
    fun providesNotificationChannel(@ApplicationContext context: Context): NotificationManager {
        return context.getSystemService<NotificationManager>() as NotificationManager
    }

}

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {


    @Provides
    @Singleton
    @ApplicationScope
    fun providesCoroutineScope(
        @DefaultDispatcher dispatcher: CoroutineDispatcher
    ) = CoroutineScope(SupervisorJob() + dispatcher)


    @Provides
    @Singleton
    @IoDispatcher
    fun providesIoDispatcher() = Dispatchers.IO


    @Provides
    @Singleton
    @DefaultDispatcher
    fun providesDefaultDispatcher() = Dispatchers.Default

}

