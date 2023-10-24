package com.ginamelinia.architectureplayground

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel : ViewModel() {

    private var i = 0
    private val notes: MutableList<Note> = mutableListOf()

    @Deprecated(message = "")
    fun provideData(): List<Note> {
        return notes
    }

    fun formateDate(date: Date): String {
        return  SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
    }

    fun createNote() {
        val note = Note(Date(), "note: $i")
        i++

        notes.add(note)
    }

    fun fetchData(): List<Note> {
        return if (this.notes.isEmpty()) {
            emptyList()
        } else {
            this.notes
        }
    }
}