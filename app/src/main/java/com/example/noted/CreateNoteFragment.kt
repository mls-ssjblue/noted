package com.example.noted

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.noted.data.Note
import com.example.noted.viewmodels.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.*


@AndroidEntryPoint
class CreateNoteFragment : Fragment() {

    private val notesViewModel: NotesViewModel by viewModels()

    private lateinit var noteId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.create_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            val existingNote = bundle.get("note") as? Note
            if (existingNote != null) {
                noteId = existingNote.noteId
                requireView().findViewById<EditText>(R.id.note_title).setText(existingNote.title)
                requireView().findViewById<EditText>(R.id.note_content)
                    .setText(existingNote.content)
            } else {
                noteId = UUID.randomUUID().toString()
            }
        }

        view.findViewById<EditText>(R.id.note_content).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val title = view.findViewById<EditText>(R.id.note_title).text.toString()
                val content = view.findViewById<EditText>(R.id.note_content).text.toString()
                val category = "Tasks"
                notesViewModel.addNote(Note(noteId, title, content, category, LocalDateTime.now()))
            }
        })

        view.findViewById<EditText>(R.id.note_title).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val title = view.findViewById<EditText>(R.id.note_title).text.toString()
                val content = view.findViewById<EditText>(R.id.note_content).text.toString()
                val category = "Tasks"

                notesViewModel.addNote(Note(noteId, title, content, category, LocalDateTime.now()))
            }
        })
    }

    override fun onResume() {
        super.onResume()
        activity?.findViewById<FloatingActionButton>(R.id.fab)?.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_note -> {
                notesViewModel.deleteNote(noteId)
                val navController = activity?.findNavController(R.id.nav_host_fragment)
                navController?.navigate(R.id.action_CreateNoteFragment_to_HomeFragment)
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}