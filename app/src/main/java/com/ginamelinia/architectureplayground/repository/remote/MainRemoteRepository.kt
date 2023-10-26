package com.ginamelinia.architectureplayground.repository.remote

import com.ginamelinia.architectureplayground.Note
import com.ginamelinia.architectureplayground.repository.MainRepository

class MainRemoteRepository : MainRepository {
    override fun provideNotes(): List<Note> {
        // todo implement remote function to provideNotes()
        return emptyList()
    }

    override fun addNote(): List<Note> {
        // todo implement remote function to provideNotes()
        return emptyList()
    }
}