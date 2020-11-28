package com.example.noted.data

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note) = noteDao.insert(note)

    fun getNotes() : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun deleteNote(noteId: String) = noteDao.delete(noteId)
}