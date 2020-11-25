package com.example.noted.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.R
import com.example.noted.data.Note
import com.example.noted.databinding.ListItemNoteBinding
import com.example.noted.utilities.TYPE_HEADER
import com.example.noted.utilities.TYPE_ITEM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException


class NotesAdapter(private val itemClickListener: NoteClickListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(NoteDiffCallBack()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Note>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.NoteItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> NoteViewHolder.from(parent)
            TYPE_HEADER -> TextViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoteViewHolder -> {
                val noteItem = getItem(position) as DataItem.NoteItem
                holder.bind(noteItem.note, itemClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> TYPE_HEADER
            is DataItem.NoteItem -> TYPE_ITEM
        }
    }


    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.home_header, parent, false)
                return TextViewHolder(view)
            }
        }
    }


    class NoteViewHolder private constructor(
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

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemNoteBinding.inflate(layoutInflater, parent, false)
                return NoteViewHolder(binding)
            }
        }
    }

    private class NoteDiffCallBack : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    interface NoteClickListener {
        fun onClick(note: Note)
    }

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