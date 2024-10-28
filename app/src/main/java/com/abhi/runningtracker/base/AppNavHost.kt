package com.abhi.runningtracker.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abhi.runningtracker.ui.TodayRunning.TodayRunningScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavHost(
    navController: NavHostController ,
    modifier: Modifier
){
    NavHost(navController = navController, startDestination = TodayRunningScreenDestination) {
        composable<TodayRunningScreenDestination> {
            TodayRunningScreen(modifier = modifier)
        }
    }
}

