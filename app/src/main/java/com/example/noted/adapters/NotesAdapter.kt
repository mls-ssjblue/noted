package com.example.noted.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.data.Note
import com.example.noted.databinding.ListItemNoteBinding


class NotesAdapter(val itemClickListener: NoteClickListener) :
    ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        context = parent.context

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
        holder.bind(note, itemClickListener)
    }


    class NoteViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note, noteClickListener: NoteClickListener) {
            binding.apply {
                note = item
                executePendingBindings()
            }
            itemView.setOnClickListener {
                noteClickListener.onClick(item)
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

    interface NoteClickListener {
        fun onClick(note: Note)
    }

    sealed class DataItem {

        abstract val id: String

        data class NoteItem(val note: Note) : DataItem() {
            override val id = note.noteId
        }

        object Header : DataItem() {
            override val id = "headerId"
        }


    }
}