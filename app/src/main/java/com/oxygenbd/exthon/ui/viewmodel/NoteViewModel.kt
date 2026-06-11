package com.oxygenbd.exthon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxygenbd.exthon.data.local.entity.NoteEntity
import com.oxygenbd.exthon.data.local.entity.NoteGroupEntity
import com.oxygenbd.exthon.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes: StateFlow<List<NoteEntity>> = _notes.asStateFlow()

    private val _groups = MutableStateFlow<List<NoteGroupEntity>>(emptyList())
    val groups: StateFlow<List<NoteGroupEntity>> = _groups.asStateFlow()

    private val _selectedNote = MutableStateFlow<NoteEntity?>(null)
    val selectedNote: StateFlow<NoteEntity?> = _selectedNote.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadNotes()
        loadGroups()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    private fun loadGroups() {
        viewModelScope.launch {
            noteRepository.getAllGroups().collect { groupList ->
                _groups.value = groupList
            }
        }
    }

    fun getNotesByGroup(groupId: Int) {
        viewModelScope.launch {
            noteRepository.getNotesByGroup(groupId).collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun selectNote(note: NoteEntity) {
        _selectedNote.value = note
    }

    fun createNote(note: NoteEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                noteRepository.insertNote(note)
                loadNotes()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
            loadNotes()
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            loadNotes()
        }
    }

    fun createGroup(group: NoteGroupEntity) {
        viewModelScope.launch {
            noteRepository.insertGroup(group)
            loadGroups()
        }
    }
}
