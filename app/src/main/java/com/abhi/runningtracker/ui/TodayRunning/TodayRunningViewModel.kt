package com.abhi.runningtracker.ui.TodayRunning

import androidx.compose.runtime.saveable.listSaver
import androidx.lifecycle.ViewModel
import com.abhi.runningtracker.base.TodayRunningScreenState
import com.abhi.runningtracker.data.TodayRunningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


data class CurrentSessionState(
    val steps : Long
)

@HiltViewModel
class TodayRunningViewModel @Inject constructor(repository: TodayRunningRepository) : ViewModel() {

    private val _todayRunningState  : MutableStateFlow<TodayRunningScreenState> = MutableStateFlow(TodayRunningScreenState.Loading)
    val todayRunningState = _todayRunningState.asStateFlow()

}
