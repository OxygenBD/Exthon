package com.oxygenbd.exthon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playlistId: String,
    val title: String,
    val description: String = "",
    val thumbnailUrl: String = "",
    val videoCount: Int = 0,
    val lastWatchedVideoId: String? = null,
    val lastWatchedPosition: Long = 0L,
    val lastWatchedTime: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true
)

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val videoId: String,
    val playlistId: Int,
    val title: String,
    val description: String = "",
    val thumbnailUrl: String = "",
    val duration: Long = 0L,
    val watchPosition: Long = 0L,
    val isWatched: Boolean = false,
    val watchedAt: Long? = null,
    val position: Int = 0,
    val addedAt: Long = System.currentTimeMillis()
)