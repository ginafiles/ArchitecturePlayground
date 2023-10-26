package com.ginamelinia.architectureplayground.repository.local

import com.ginamelinia.architectureplayground.Note
import com.ginamelinia.architectureplayground.repository.MainRepository
import java.util.Date

class MainLocalRepository : MainRepository{

    private var i = 0

    private val noteList: MutableList<Note> = mutableListOf()

    override fun provideNotes(): List<Note> {
        return noteList
    }

    override fun addNote(): List<Note> {
        val note = Note(Date(), "note: $i")
        noteList.add(note)
        i++

        return provideNotes()
    }
}