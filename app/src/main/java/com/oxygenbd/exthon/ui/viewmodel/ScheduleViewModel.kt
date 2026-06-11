package com.oxygenbd.exthon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxygenbd.exthon.data.local.entity.ScheduleEntity
import com.oxygenbd.exthon.domain.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _schedules = MutableStateFlow<List<ScheduleEntity>>(emptyList())
    val schedules: StateFlow<List<ScheduleEntity>> = _schedules.asStateFlow()

    private val _currentDaySchedules = MutableStateFlow<List<ScheduleEntity>>(emptyList())
    val currentDaySchedules: StateFlow<List<ScheduleEntity>> = _currentDaySchedules.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadAllSchedules()
        loadTodaySchedule()
    }

    private fun loadAllSchedules() {
        viewModelScope.launch {
            scheduleRepository.getAllSchedules().collect { scheduleList ->
                _schedules.value = scheduleList
            }
        }
    }

    private fun loadTodaySchedule() {
        val calendar = Calendar.getInstance()
        val dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7
        
        viewModelScope.launch {
            scheduleRepository.getScheduleByDay(dayOfWeek).collect { scheduleList ->
                _currentDaySchedules.value = scheduleList
            }
        }
    }

    fun getScheduleByDay(dayOfWeek: Int) {
        viewModelScope.launch {
            scheduleRepository.getScheduleByDay(dayOfWeek).collect { scheduleList ->
                _currentDaySchedules.value = scheduleList
            }
        }
    }

    fun addSchedule(schedule: ScheduleEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                scheduleRepository.insertSchedule(schedule)
                loadAllSchedules()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSchedule(schedule: ScheduleEntity) {
        viewModelScope.launch {
            scheduleRepository.updateSchedule(schedule)
            loadAllSchedules()
        }
    }

    fun deleteSchedule(schedule: ScheduleEntity) {
        viewModelScope.launch {
            scheduleRepository.deleteSchedule(schedule)
            loadAllSchedules()
        }
    }
}
