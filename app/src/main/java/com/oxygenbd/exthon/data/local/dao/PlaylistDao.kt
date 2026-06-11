package com.oxygenbd.exthon.data.local.dao

import androidx.room.*
import com.oxygenbd.exthon.data.local.entity.PlaylistEntity
import com.oxygenbd.exthon.data.local.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity): Long

    @Update
    suspend fun updatePlaylist(playlist: PlaylistEntity)

    @Delete
    suspend fun deletePlaylist(playlist: PlaylistEntity)

    @Query("SELECT * FROM playlists WHERE id = :id")
    fun getPlaylistById(id: Int): Flow<PlaylistEntity?>

    @Query("SELECT * FROM playlists WHERE isActive = 1 ORDER BY updatedAt DESC")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Query("SELECT * FROM playlists WHERE playlistId = :playlistId")
    suspend fun getPlaylistByPlaylistId(playlistId: String): PlaylistEntity?

    @Query("DELETE FROM playlists WHERE id = :id")
    suspend fun deletePlaylistById(id: Int)

    @Query("UPDATE playlists SET lastWatchedVideoId = :videoId, lastWatchedPosition = :position, lastWatchedTime = :timestamp WHERE id = :playlistId")
    suspend fun updateLastWatched(playlistId: Int, videoId: String, position: Long, timestamp: Long)
}

@Dao
interface VideoDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: VideoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideoEntity>)

    @Update
    suspend fun updateVideo(video: VideoEntity)

    @Delete
    suspend fun deleteVideo(video: VideoEntity)

    @Query("SELECT * FROM videos WHERE playlistId = :playlistId ORDER BY position ASC")
    fun getVideosByPlaylist(playlistId: Int): Flow<List<VideoEntity>>

    @Query("SELECT * FROM videos WHERE videoId = :videoId LIMIT 1")
    suspend fun getVideoByVideoId(videoId: String): VideoEntity?

    @Query("SELECT * FROM videos WHERE id = :id")
    fun getVideoById(id: Int): Flow<VideoEntity?>

    @Query("UPDATE videos SET watchPosition = :position, isWatched = :isWatched, watchedAt = :watchedAt WHERE id = :videoId")
    suspend fun updateWatchProgress(videoId: Int, position: Long, isWatched: Boolean, watchedAt: Long? = null)

    @Query("DELETE FROM videos WHERE playlistId = :playlistId")
    suspend fun deleteVideosByPlaylist(playlistId: Int)
}