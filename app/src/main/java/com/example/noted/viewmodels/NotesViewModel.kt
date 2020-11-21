package com.example.noted.viewmodels
import android.view.View
import androidx.hilt.lifecycle.ViewModelAssistedFactory
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noted.data.Note
import com.example.noted.data.NoteRepository
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class  NotesViewModel @ViewModelInject internal constructor(
    private val noteRepository: NoteRepository? = null
): ViewModel() {
    val notes : List<Note>? = noteRepository?.getNotes()

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository?.insertNote(note)
        }
    }


//    @AssistedInject.Factory
//    interface AssistedFactory {
//        fun create(noteId: String): NotesViewModel
//    }
//    companion object {
//        fun provideFactory(
//            assistedFactory: AssistedFactory,
//            noteId: String
//        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//
//            @Suppress("UNCHECKED_CAST")
//            override fun <T:ViewModel ?> create (modelClass: Class<T>) :T {
//                return assistedFactory.create(noteId) as T
//            }
//        }
//    }
}