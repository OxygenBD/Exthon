package com.oxygenbd.exthon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxygenbd.exthon.data.local.entity.AchievementEntity
import com.oxygenbd.exthon.data.local.entity.DailyStatsEntity
import com.oxygenbd.exthon.domain.repository.AchievementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val achievementRepository: AchievementRepository
) : ViewModel() {

    private val _unlockedAchievements = MutableStateFlow<List<AchievementEntity>>(emptyList())
    val unlockedAchievements: StateFlow<List<AchievementEntity>> = _unlockedAchievements.asStateFlow()

    private val _lockedAchievements = MutableStateFlow<List<AchievementEntity>>(emptyList())
    val lockedAchievements: StateFlow<List<AchievementEntity>> = _lockedAchievements.asStateFlow()

    private val _todayStats = MutableStateFlow<DailyStatsEntity?>(null)
    val todayStats: StateFlow<DailyStatsEntity?> = _todayStats.asStateFlow()

    private val _totalPoints = MutableStateFlow(0)
    val totalPoints: StateFlow<Int> = _totalPoints.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadAchievements()
        loadTodayStats()
    }

    private fun loadAchievements() {
        viewModelScope.launch {
            achievementRepository.getUnlockedAchievements().collect { achievements ->
                _unlockedAchievements.value = achievements
                val points = achievements.sumOf { it.points }
                _totalPoints.value = points
            }
        }
        
        viewModelScope.launch {
            achievementRepository.getLockedAchievements().collect { achievements ->
                _lockedAchievements.value = achievements
            }
        }
    }

    private fun loadTodayStats() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = dateFormat.format(Date())
        
        viewModelScope.launch {
            achievementRepository.getDailyStats(today).collect { stats ->
                _todayStats.value = stats
            }
        }
    }

    fun updateDailyStats(stats: DailyStatsEntity) {
        viewModelScope.launch {
            achievementRepository.updateDailyStats(stats)
            loadTodayStats()
        }
    }

    fun refreshAchievements() {
        loadAchievements()
    }
}
