package com.oxygenbd.exthon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxygenbd.exthon.data.local.entity.PlaylistEntity
import com.oxygenbd.exthon.data.local.entity.VideoEntity
import com.oxygenbd.exthon.domain.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    private val _playlists = MutableStateFlow<List<PlaylistEntity>>(emptyList())
    val playlists: StateFlow<List<PlaylistEntity>> = _playlists.asStateFlow()

    private val _selectedPlaylist = MutableStateFlow<PlaylistEntity?>(null)
    val selectedPlaylist: StateFlow<PlaylistEntity?> = _selectedPlaylist.asStateFlow()

    private val _videos = MutableStateFlow<List<VideoEntity>>(emptyList())
    val videos: StateFlow<List<VideoEntity>> = _videos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadPlaylists()
    }

    private fun loadPlaylists() {
        viewModelScope.launch {
            playlistRepository.getAllPlaylists().collect { playlistList ->
                _playlists.value = playlistList
            }
        }
    }

    fun selectPlaylist(playlistId: Int) {
        viewModelScope.launch {
            playlistRepository.getPlaylistById(playlistId).collect { playlist ->
                _selectedPlaylist.value = playlist
                if (playlist != null) {
                    loadPlaylistVideos(playlistId)
                }
            }
        }
    }

    private fun loadPlaylistVideos(playlistId: Int) {
        viewModelScope.launch {
            playlistRepository.getVideosByPlaylist(playlistId).collect { videoList ->
                _videos.value = videoList
            }
        }
    }

    fun addPlaylist(playlist: PlaylistEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                playlistRepository.insertPlaylist(playlist)
                loadPlaylists()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deletePlaylist(playlistId: Int) {
        viewModelScope.launch {
            playlistRepository.deletePlaylist(playlistId)
            loadPlaylists()
        }
    }
}
