package com.oxygenbd.exthon.data.local.dao

import androidx.room.*
import com.oxygenbd.exthon.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity): Long

    @Update
    suspend fun updateSchedule(schedule: ScheduleEntity)

    @Delete
    suspend fun deleteSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM schedules WHERE dayOfWeek = :dayOfWeek ORDER BY startTime ASC")
    fun getScheduleByDay(dayOfWeek: Int): Flow<List<ScheduleEntity>>

    @Query("SELECT * FROM schedules ORDER BY dayOfWeek, startTime ASC")
    fun getAllSchedules(): Flow<List<ScheduleEntity>>
}

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): Flow<NoteEntity?>

    @Query("SELECT * FROM notes WHERE groupId = :groupId AND deletedAt IS NULL ORDER BY isPinned DESC, updatedAt DESC")
    fun getNotesByGroup(groupId: Int): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE deletedAt IS NULL ORDER BY isPinned DESC, updatedAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: NoteGroupEntity): Long

    @Query("SELECT * FROM note_groups ORDER BY createdAt DESC")
    fun getAllGroups(): Flow<List<NoteGroupEntity>>
}

@Dao
interface PomodoroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: PomodoroSessionEntity): Long

    @Update
    suspend fun updateSession(session: PomodoroSessionEntity)

    @Query("SELECT * FROM pomodoro_sessions WHERE date = :date ORDER BY startTime DESC")
    fun getSessionsByDate(date: String): Flow<List<PomodoroSessionEntity>>

    @Query("SELECT * FROM pomodoro_sessions WHERE category = :category AND date = :date")
    fun getSessionsByDateAndCategory(date: String, category: String): Flow<List<PomodoroSessionEntity>>

    @Query("SELECT SUM(duration) FROM pomodoro_sessions WHERE date = :date AND isCompleted = 1")
    fun getTotalDurationByDate(date: String): Flow<Long?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetting(setting: PomodoroSettingEntity)

    @Query("SELECT * FROM pomodoro_settings WHERE id = 1")
    fun getSetting(): Flow<PomodoroSettingEntity?>
}

@Dao
interface AchievementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: AchievementEntity): Long

    @Update
    suspend fun updateAchievement(achievement: AchievementEntity)

    @Query("SELECT * FROM achievements WHERE isUnlocked = 0 ORDER BY progressCurrent DESC")
    fun getLockedAchievements(): Flow<List<AchievementEntity>>

    @Query("SELECT * FROM achievements WHERE isUnlocked = 1 ORDER BY unlockedAt DESC")
    fun getUnlockedAchievements(): Flow<List<AchievementEntity>>

    @Query("SELECT * FROM achievements ORDER BY category, rarity DESC")
    fun getAllAchievements(): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyStats(stats: DailyStatsEntity): Long

    @Update
    suspend fun updateDailyStats(stats: DailyStatsEntity)

    @Query("SELECT * FROM daily_stats WHERE date = :date")
    fun getDailyStats(date: String): Flow<DailyStatsEntity?>
}

@Dao
interface BlockingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlockedSite(site: BlockedSiteEntity): Long

    @Update
    suspend fun updateBlockedSite(site: BlockedSiteEntity)

    @Delete
    suspend fun deleteBlockedSite(site: BlockedSiteEntity)

    @Query("SELECT * FROM blocked_sites WHERE isActive = 1 ORDER BY category ASC")
    fun getActiveBlockedSites(): Flow<List<BlockedSiteEntity>>

    @Query("SELECT * FROM blocked_sites")
    fun getAllBlockedSites(): Flow<List<BlockedSiteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlockingStats(stats: BlockingStatsEntity): Long

    @Query("SELECT * FROM blocking_stats WHERE date = :date")
    fun getBlockingStatsByDate(date: String): Flow<List<BlockingStatsEntity>>
}

@Dao
interface PrayerTimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerTimes(prayerTime: PrayerTimeEntity): Long

    @Update
    suspend fun updatePrayerTimes(prayerTime: PrayerTimeEntity)

    @Query("SELECT * FROM prayer_times WHERE date = :date LIMIT 1")
    fun getPrayerTimesByDate(date: String): Flow<PrayerTimeEntity?>
}

@Dao
interface DailyStatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: DailyStatsEntity): Long

    @Update
    suspend fun updateStats(stats: DailyStatsEntity)

    @Query("SELECT * FROM daily_stats WHERE date = :date")
    fun getStatsByDate(date: String): Flow<DailyStatsEntity?>

    @Query("SELECT * FROM daily_stats WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getStatsBetweenDates(startDate: String, endDate: String): Flow<List<DailyStatsEntity>>
}