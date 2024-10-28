package com.abhi.runningtracker.data.local

import androidx.room.Entity

@Entity(tableName = "StepsTable")
data class LocalStepsCount(
    val steps : Long
) {
}