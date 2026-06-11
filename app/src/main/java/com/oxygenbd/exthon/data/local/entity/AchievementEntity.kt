package com.oxygenbd.exthon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String, // unique identifier
    val title: String,
    val description: String,
    val category: String, // Focus, Prayer, Reading, Blocking, Motivation, etc.
    val icon: String = "",
    val condition: String = "", // JSON describing unlock condition
    val progressCurrent: Int = 0,
    val progressTarget: Int = 100,
    val isUnlocked: Boolean = false,
    val unlockedAt: Long? = null,
    val rarity: String = "common", // common, rare, epic, legendary
    val points: Int = 10,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "daily_stats")
data class DailyStatsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String, // YYYY-MM-DD format
    val totalFocusTime: Long = 0L, // milliseconds
    val pomodorosCompleted: Int = 0,
    val videosWatched: Int = 0,
    val totalVideoTime: Long = 0L,
    val notesCreated: Int = 0,
    val prayersCompleted: Int = 0,
    val blockedAttempts: Int = 0,
    val readingTime: Long = 0L,
    val writingTime: Long = 0L,
    val motivationScore: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "weekly_stats")
data class WeeklyStatsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weekStartDate: String, // YYYY-MM-DD format
    val totalFocusTime: Long = 0L,
    val pomodorosCompleted: Int = 0,
    val videosWatched: Int = 0,
    val notesCreated: Int = 0,
    val achievementsUnlocked: Int = 0,
    val streakDays: Int = 0,
    val motivationScore: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)