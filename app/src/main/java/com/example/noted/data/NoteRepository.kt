package com.example.noted.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun insertNote(note: Note) = noteDao.insert(note)

    fun getNotes() :List<Note> = noteDao.getAllNotes()
}