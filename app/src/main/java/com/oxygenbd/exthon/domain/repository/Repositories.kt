package com.oxygenbd.exthon.domain.repository

import com.oxygenbd.exthon.data.local.entity.*
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>
    fun getPlaylistById(id: Int): Flow<PlaylistEntity?>
    suspend fun insertPlaylist(playlist: PlaylistEntity): Long
    suspend fun updatePlaylist(playlist: PlaylistEntity)
    suspend fun deletePlaylist(id: Int)
    fun getVideosByPlaylist(playlistId: Int): Flow<List<VideoEntity>>
    suspend fun insertVideos(videos: List<VideoEntity>)
}

interface ScheduleRepository {
    fun getScheduleByDay(dayOfWeek: Int): Flow<List<ScheduleEntity>>
    fun getAllSchedules(): Flow<List<ScheduleEntity>>
    suspend fun insertSchedule(schedule: ScheduleEntity): Long
    suspend fun updateSchedule(schedule: ScheduleEntity)
    suspend fun deleteSchedule(schedule: ScheduleEntity)
}

interface NoteRepository {
    fun getAllNotes(): Flow<List<NoteEntity>>
    fun getNotesByGroup(groupId: Int): Flow<List<NoteEntity>>
    suspend fun insertNote(note: NoteEntity): Long
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    fun getAllGroups(): Flow<List<NoteGroupEntity>>
    suspend fun insertGroup(group: NoteGroupEntity): Long
}

interface PomodoroRepository {
    fun getSessionsByDate(date: String): Flow<List<PomodoroSessionEntity>>
    suspend fun insertSession(session: PomodoroSessionEntity): Long
    suspend fun updateSession(session: PomodoroSessionEntity)
    fun getSetting(): Flow<PomodoroSettingEntity?>
    suspend fun updateSetting(setting: PomodoroSettingEntity)
}

interface AchievementRepository {
    fun getLockedAchievements(): Flow<List<AchievementEntity>>
    fun getUnlockedAchievements(): Flow<List<AchievementEntity>>
    suspend fun updateAchievement(achievement: AchievementEntity)
    fun getDailyStats(date: String): Flow<DailyStatsEntity?>
    suspend fun updateDailyStats(stats: DailyStatsEntity)
}

interface BlockingRepository {
    fun getActiveBlockedSites(): Flow<List<BlockedSiteEntity>>
    suspend fun insertBlockedSite(site: BlockedSiteEntity): Long
    suspend fun updateBlockedSite(site: BlockedSiteEntity)
    suspend fun deleteBlockedSite(site: BlockedSiteEntity)
    fun getBlockingStatsByDate(date: String): Flow<List<BlockingStatsEntity>>
}
