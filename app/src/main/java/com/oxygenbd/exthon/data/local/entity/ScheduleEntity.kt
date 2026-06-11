package com.oxygenbd.exthon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dayOfWeek: Int, // 0-6 (Sunday-Saturday)
    val title: String,
    val startTime: String, // HH:mm format
    val endTime: String,
    val description: String = "",
    val category: String = "", // Work, Study, Prayer, Break, etc.
    val reminderEnabled: Boolean = true,
    val reminderMinutesBefore: Int = 15,
    val color: Int = 0xFF6200EE.toInt(),
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "prayer_times")
data class PrayerTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String, // YYYY-MM-DD format
    val fajr: String, // HH:mm
    val dhuhr: String,
    val asr: String,
    val maghrib: String,
    val isha: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timezone: String = "",
    val reminderEnabled: Boolean = true,
    val reminderMinutesBefore: Int = 5,
    val createdAt: Long = System.currentTimeMillis()
)