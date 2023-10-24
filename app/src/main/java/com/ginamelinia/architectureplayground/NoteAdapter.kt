package com.ginamelinia.architectureplayground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class NoteAdapter(private val mainViewModel: MainViewModel) : ListAdapter<Note, NoteViewHolder>(
    NoteDiffutil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            ),
            mainViewModel = mainViewModel
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}