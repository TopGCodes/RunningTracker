package com.abhi.runningtracker.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.abhi.runningtracker.R

class RunningTrackerService() : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            Actions.Start.toString() -> starttrackingService()
            Actions.Stop.toString() -> stopService()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    enum class Actions{
        Start,
        Stop
    }


    fun starttrackingService(){
        val notifcation = NotificationCompat.Builder(this,"runningtrackerChannnelid")
            .setContentTitle("Your session has Started")
            .setContentText("Your steps will be counted")
            .build()
        startForeground(101, notifcation)
    }

    fun stopService(){

    }

}