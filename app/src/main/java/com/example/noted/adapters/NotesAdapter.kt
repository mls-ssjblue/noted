package com.example.noted.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.noted.data.Note
import com.example.noted.databinding.ListItemNoteBinding

class NotesAdapter
    : ListAdapter<Note, NotesAdapter.NoteViewHolder>(
    NoteDiffCallBack()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ListItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        (holder as NoteViewHolder).bind(note)
    }

    class NoteViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.note?.let { note ->
                    navigateToNote(note, view)
                }
            }
        }

        private fun navigateToNote(note: Note, view: View) {
//            val direction = HomeViewPagerFragmentDirections
//                .actionViewPagerFragmentToPlantDetailFragment(plantId)
//            view.findNavController().navigate(direction)
        }

        fun bind(item: Note) {
            with(binding) {
                note = item
                executePendingBindings()
            }
        }
    }

    private class NoteDiffCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }


}