package com.ginamelinia.architectureplayground

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteViewHolder(
    itemView: View,
    private val mainViewModel: MainViewModel
    ) : RecyclerView.ViewHolder(itemView) {
    fun bind(note: Note) {
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        val tvNote = itemView.findViewById<TextView>(R.id.tv_note)

        tvNote.text = note.note
        tvDate.text = mainViewModel.formateDate(note.date)

    }


}