package com.oxygenbd.exthon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pomodoro_sessions")
data class PomodoroSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String, // Reading, Writing, Prayer, Play, Work, etc.
    val date: String, // YYYY-MM-DD format
    val duration: Long, // milliseconds
    val startTime: Long,
    val endTime: Long,
    val isCompleted: Boolean = false,
    val isBreak: Boolean = false,
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "pomodoro_settings")
data class PomodoroSettingEntity(
    @PrimaryKey
    val id: Int = 1,
    val workDurationMinutes: Int = 25,
    val breakDurationMinutes: Int = 5,
    val longBreakDurationMinutes: Int = 15,
    val sessionsBeforeLongBreak: Int = 4,
    val autoStartBreak: Boolean = true,
    val autoStartNextSession: Boolean = false,
    val soundEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val notificationEnabled: Boolean = true,
    val updatedAt: Long = System.currentTimeMillis()
)