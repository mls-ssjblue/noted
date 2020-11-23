package com.example.noted.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.CreateNoteFragment
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
        (holder as NoteViewHolder).bind(note, itemClickListener)
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
        fun bind(item: Note, noteClickListener: NoteClickListener) {
            binding.apply {
                note = item
                executePendingBindings()
            }
            itemView.setOnClickListener{
                noteClickListener.onClick(item)
            }
        }

        private fun navigateToNote(note: Note, view: View) {
            val createNoteFragment = CreateNoteFragment()
//            val activity = context as AppCompatActivity

//            activity.supportFragmentManager.beginTransaction()
//                .replace(R.id.list_container, createNoteFragment)
//                .addToBackStack(null).commit()

//            val direction = HomeViewPagerFragmentDirections
//                .actionViewPagerFragmentToPlantDetailFragment(plantId)
//            view.findNavController().navigate(direction)
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
}