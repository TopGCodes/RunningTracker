package com.abhi.runningtracker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.abhi.runningtracker.base.AppNavHost
import com.abhi.runningtracker.ui.RunningTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import requestPermission
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var notificationManager: NotificationManager

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RunningTrackerTheme {
                Scaffold(Modifier.fillMaxSize()) { innerpadding ->
                    enablePushNotifications()
                    requestPermission()
                    AppNavHost(navController = rememberNavController(), modifier = Modifier)
                }
            }
        }
    }

    fun enablePushNotifications() {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val description = R.string.notifcation_description
        val notificationChannel = NotificationChannel(
            "runningtrackerChannnelid",
            "runningtrackerchannel", importance
        )
        notificationChannel.description = description.toString()
        notificationManager.createNotificationChannel(notificationChannel)
    }
}






