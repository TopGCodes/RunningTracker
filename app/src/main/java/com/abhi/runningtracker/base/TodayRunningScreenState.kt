package com.abhi.runningtracker.base

sealed class TodayRunningScreenState {
    data object Loading : TodayRunningScreenState()
    data class Success(val totalSteps : Long, val todayStepsgoal : Long) : TodayRunningScreenState()
    data class Error(val errorMessage : String) : TodayRunningScreenState()
}