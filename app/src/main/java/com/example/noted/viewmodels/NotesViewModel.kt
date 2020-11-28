package com.example.noted.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noted.data.Note
import com.example.noted.data.NoteRepository
import kotlinx.coroutines.launch

class NotesViewModel @ViewModelInject internal constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    val notes: LiveData<List<Note>> = noteRepository.getNotes()

    fun addNote(note: Note) {

        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }

}