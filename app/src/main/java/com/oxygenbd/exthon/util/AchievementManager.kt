package com.oxygenbd.exthon.util

import android.content.Context
import android.util.Log
import com.oxygenbd.exthon.data.local.ExthonDatabase
import com.oxygenbd.exthon.data.local.entity.AchievementEntity
import com.oxygenbd.exthon.service.NotificationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

object AchievementManager {

    private val TAG = "AchievementManager"

    suspend fun initializeAchievements(context: Context) {
        withContext(Dispatchers.IO) {
            try {
                val db = ExthonDatabase.getDatabase(context)

                val achievements = listOf(
                    AchievementEntity(
                        code = "first_pomodoro",
                        title = "Getting Started",
                        description = "Complete your first Pomodoro session",
                        category = "Focus",
                        rarity = "common",
                        points = 10
                    ),
                    AchievementEntity(
                        code = "week_warrior",
                        title = "Week Warrior",
                        description = "Complete 20 Pomodoro sessions in a week",
                        category = "Focus",
                        rarity = "rare",
                        points = 50,
                        progressTarget = 20
                    ),
                    AchievementEntity(
                        code = "month_master",
                        title = "Month Master",
                        description = "Complete 100 Pomodoro sessions in a month",
                        category = "Focus",
                        rarity = "epic",
                        points = 100,
                        progressTarget = 100
                    ),
                    AchievementEntity(
                        code = "prayer_faithful",
                        title = "Prayer Faithful",
                        description = "Never miss daily prayers for 7 days",
                        category = "Prayer",
                        rarity = "rare",
                        points = 50,
                        progressTarget = 7
                    ),
                    AchievementEntity(
                        code = "blocking_champion",
                        title = "Blocking Champion",
                        description = "Successfully block harmful sites for 30 days",
                        category = "Blocking",
                        rarity = "epic",
                        points = 150,
                        progressTarget = 30
                    ),
                    AchievementEntity(
                        code = "note_keeper",
                        title = "Note Keeper",
                        description = "Create 50 notes",
                        category = "Productivity",
                        rarity = "common",
                        points = 30,
                        progressTarget = 50
                    ),
                    AchievementEntity(
                        code = "reading_master",
                        title = "Reading Master",
                        description = "Complete 10 hours of reading sessions",
                        category = "Reading",
                        rarity = "rare",
                        points = 75,
                        progressTarget = 10
                    ),
                    AchievementEntity(
                        code = "year_legend",
                        title = "Year Legend",
                        description = "Maintain consistency for a full year",
                        category = "Motivation",
                        rarity = "legendary",
                        points = 500,
                        progressTarget = 365
                    )
                )

                achievements.forEach { achievement ->
                    db.achievementDao().insertAchievement(achievement)
                }

                Log.d(TAG, "Achievements initialized")
            } catch (e: Exception) {
                Log.e(TAG, "Error initializing achievements", e)
            }
        }
    }

    suspend fun checkAndUnlockAchievements(context: Context, category: String) {
        withContext(Dispatchers.IO) {
            try {
                val db = ExthonDatabase.getDatabase(context)
                val today = SimpleDateFormat("yyyy-MM-dd").format(Date())

                // Check various achievements based on progress
                val stats = db.dailyStatsDao().getStatsByDate(today)
                // Implement achievement unlock logic

                Log.d(TAG, "Achievement check completed for $category")
            } catch (e: Exception) {
                Log.e(TAG, "Error checking achievements", e)
            }
        }
    }

    suspend fun unlockAchievement(
        context: Context,
        achievementCode: String,
        title: String,
        description: String
    ) {
        withContext(Dispatchers.IO) {
            try {
                val db = ExthonDatabase.getDatabase(context)

                // Update achievement in database
                // db.achievementDao().updateAchievement(...)

                // Show notification
                NotificationService.showAchievementNotification(context, title, description)

                Log.d(TAG, "Achievement unlocked: $achievementCode")
            } catch (e: Exception) {
                Log.e(TAG, "Error unlocking achievement", e)
            }
        }
    }

    suspend fun getWeeklyAchievements(context: Context): List<AchievementEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val db = ExthonDatabase.getDatabase(context)
                // Return achievements for current week
                emptyList()
            } catch (e: Exception) {
                Log.e(TAG, "Error getting weekly achievements", e)
                emptyList()
            }
        }
    }
}