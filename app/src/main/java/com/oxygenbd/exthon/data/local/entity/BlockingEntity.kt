package com.oxygenbd.exthon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blocked_sites")
data class BlockedSiteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String,
    val appPackageName: String = "",
    val category: String, // Porn, SocialMedia, Gaming, etc.
    val isActive: Boolean = true,
    val blockStartTime: String? = null, // HH:mm
    val blockEndTime: String? = null,
    val allowedDays: String = "1234567", // bitmap for days
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "blocking_stats")
data class BlockingStatsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String, // YYYY-MM-DD format
    val blockedSiteId: Int,
    val blockedAttempts: Int = 0,
    val blockDuration: Long = 0L, // total milliseconds blocked
    val category: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "wellness_achievements")
data class WellnessAchievementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val icon: String = "",
    val blockingDaysStreak: Int = 0,
    val totalBlockedAttempts: Int = 0,
    val category: String = "", // SocialMediaFree, PornFree, GamingFree, etc.
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)