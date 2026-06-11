package com.oxygenbd.exthon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxygenbd.exthon.data.local.entity.PomodoroSessionEntity
import com.oxygenbd.exthon.data.local.entity.PomodoroSettingEntity
import com.oxygenbd.exthon.domain.repository.PomodoroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val pomodoroRepository: PomodoroRepository
) : ViewModel() {

    private val _sessions = MutableStateFlow<List<PomodoroSessionEntity>>(emptyList())
    val sessions: StateFlow<List<PomodoroSessionEntity>> = _sessions.asStateFlow()

    private val _settings = MutableStateFlow<PomodoroSettingEntity?>(null)
    val settings: StateFlow<PomodoroSettingEntity?> = _settings.asStateFlow()

    private val _timerRunning = MutableStateFlow(false)
    val timerRunning: StateFlow<Boolean> = _timerRunning.asStateFlow()

    private val _timeRemaining = MutableStateFlow(0L)
    val timeRemaining: StateFlow<Long> = _timeRemaining.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTodaySessions()
        loadSettings()
    }

    private fun loadTodaySessions() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = dateFormat.format(Date())
        
        viewModelScope.launch {
            pomodoroRepository.getSessionsByDate(today).collect { sessionList ->
                _sessions.value = sessionList
            }
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            pomodoroRepository.getSetting().collect { setting ->
                _settings.value = setting
            }
        }
    }

    fun startTimer(duration: Long) {
        _timerRunning.value = true
        _timeRemaining.value = duration
    }

    fun pauseTimer() {
        _timerRunning.value = false
    }

    fun resumeTimer() {
        _timerRunning.value = true
    }

    fun stopTimer() {
        _timerRunning.value = false
        _timeRemaining.value = 0L
    }

    fun updateTimeRemaining(time: Long) {
        _timeRemaining.value = time
    }

    fun saveSession(session: PomodoroSessionEntity) {
        viewModelScope.launch {
            pomodoroRepository.insertSession(session)
            loadTodaySessions()
        }
    }

    fun updateSettings(setting: PomodoroSettingEntity) {
        viewModelScope.launch {
            pomodoroRepository.updateSetting(setting)
            loadSettings()
        }
    }
}
