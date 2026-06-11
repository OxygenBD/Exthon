package com.oxygenbd.exthon.data.repository

import com.oxygenbd.exthon.data.local.dao.*
import com.oxygenbd.exthon.data.local.entity.*
import com.oxygenbd.exthon.domain.repository.*
import kotlinx.coroutines.flow.Flow

class PlaylistRepositoryImpl(
    private val playlistDao: PlaylistDao,
    private val videoDao: VideoDao
) : PlaylistRepository {
    override fun getAllPlaylists(): Flow<List<PlaylistEntity>> = playlistDao.getAllPlaylists()
    override fun getPlaylistById(id: Int): Flow<PlaylistEntity?> = playlistDao.getPlaylistById(id)
    override suspend fun insertPlaylist(playlist: PlaylistEntity): Long = playlistDao.insertPlaylist(playlist)
    override suspend fun updatePlaylist(playlist: PlaylistEntity) = playlistDao.updatePlaylist(playlist)
    override suspend fun deletePlaylist(id: Int) = playlistDao.deletePlaylistById(id)
    override fun getVideosByPlaylist(playlistId: Int): Flow<List<VideoEntity>> = videoDao.getVideosByPlaylist(playlistId)
    override suspend fun insertVideos(videos: List<VideoEntity>) = videoDao.insertVideos(videos)
}

class ScheduleRepositoryImpl(
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {
    override fun getScheduleByDay(dayOfWeek: Int): Flow<List<ScheduleEntity>> = scheduleDao.getScheduleByDay(dayOfWeek)
    override fun getAllSchedules(): Flow<List<ScheduleEntity>> = scheduleDao.getAllSchedules()
    override suspend fun insertSchedule(schedule: ScheduleEntity): Long = scheduleDao.insertSchedule(schedule)
    override suspend fun updateSchedule(schedule: ScheduleEntity) = scheduleDao.updateSchedule(schedule)
    override suspend fun deleteSchedule(schedule: ScheduleEntity) = scheduleDao.deleteSchedule(schedule)
}

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()
    override fun getNotesByGroup(groupId: Int): Flow<List<NoteEntity>> = noteDao.getNotesByGroup(groupId)
    override suspend fun insertNote(note: NoteEntity): Long = noteDao.insertNote(note)
    override suspend fun updateNote(note: NoteEntity) = noteDao.updateNote(note)
    override suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)
    override fun getAllGroups(): Flow<List<NoteGroupEntity>> = noteDao.getAllGroups()
    override suspend fun insertGroup(group: NoteGroupEntity): Long = noteDao.insertGroup(group)
}

class PomodoroRepositoryImpl(
    private val pomodoroDao: PomodoroDao
) : PomodoroRepository {
    override fun getSessionsByDate(date: String): Flow<List<PomodoroSessionEntity>> = pomodoroDao.getSessionsByDate(date)
    override suspend fun insertSession(session: PomodoroSessionEntity): Long = pomodoroDao.insertSession(session)
    override suspend fun updateSession(session: PomodoroSessionEntity) = pomodoroDao.updateSession(session)
    override fun getSetting(): Flow<PomodoroSettingEntity?> = pomodoroDao.getSetting()
    override suspend fun updateSetting(setting: PomodoroSettingEntity) = pomodoroDao.insertSetting(setting)
}

class AchievementRepositoryImpl(
    private val achievementDao: AchievementDao
) : AchievementRepository {
    override fun getLockedAchievements(): Flow<List<AchievementEntity>> = achievementDao.getLockedAchievements()
    override fun getUnlockedAchievements(): Flow<List<AchievementEntity>> = achievementDao.getUnlockedAchievements()
    override suspend fun updateAchievement(achievement: AchievementEntity) = achievementDao.updateAchievement(achievement)
    override fun getDailyStats(date: String): Flow<DailyStatsEntity?> = achievementDao.getDailyStats(date)
    override suspend fun updateDailyStats(stats: DailyStatsEntity) = achievementDao.updateDailyStats(stats)
}

class BlockingRepositoryImpl(
    private val blockingDao: BlockingDao
) : BlockingRepository {
    override fun getActiveBlockedSites(): Flow<List<BlockedSiteEntity>> = blockingDao.getActiveBlockedSites()
    override suspend fun insertBlockedSite(site: BlockedSiteEntity): Long = blockingDao.insertBlockedSite(site)
    override suspend fun updateBlockedSite(site: BlockedSiteEntity) = blockingDao.updateBlockedSite(site)
    override suspend fun deleteBlockedSite(site: BlockedSiteEntity) = blockingDao.deleteBlockedSite(site)
    override fun getBlockingStatsByDate(date: String): Flow<List<BlockingStatsEntity>> = blockingDao.getBlockingStatsByDate(date)
}
