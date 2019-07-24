package elamien.abdullah.socialnote.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import elamien.abdullah.socialnote.database.Note
import elamien.abdullah.socialnote.databinding.ListItemNotesBinding
import elamien.abdullah.socialnote.ui.AddNoteActivity
import elamien.abdullah.socialnote.utils.Constants
import elamien.abdullah.socialnote.utils.NotesDiffCallback


/**
 * Created by AbdullahAtta on 7/23/2019.
 */
class PagedNoteListAdapter(private val context : Context) :
    PagedListAdapter<Note, PagedNoteListAdapter.NotesViewHolder>(NotesDiffCallback()) {
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : NotesViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemNotesBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder : NotesViewHolder, position : Int) {
        holder.bind(getItem(position))
    }

    inner class NotesViewHolder(var mBinding : ListItemNotesBinding) : RecyclerView.ViewHolder(mBinding.root) {
        init {
            mBinding.handlers = this
        }

        fun bind(note : Note?) {
            mBinding.note = note
        }

        fun onNoteClick(view : View) {
            val noteIntent = Intent(context, AddNoteActivity::class.java)
            noteIntent.putExtra(Constants.NOTE_INTENT_KEY, getItem(adapterPosition)?.id)
            context.startActivity(noteIntent)
        }
    }
}