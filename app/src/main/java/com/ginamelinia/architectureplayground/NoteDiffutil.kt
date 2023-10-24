package com.ginamelinia.architectureplayground

import androidx.recyclerview.widget.DiffUtil

class NoteDiffutil : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.date == newItem.date
    }
}