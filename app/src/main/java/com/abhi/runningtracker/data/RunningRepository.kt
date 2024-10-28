package com.abhi.runningtracker.data

interface RunningRepository {

    suspend fun getSteps()
}