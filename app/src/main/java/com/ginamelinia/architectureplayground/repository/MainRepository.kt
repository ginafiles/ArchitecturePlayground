package com.ginamelinia.architectureplayground.repository

import com.ginamelinia.architectureplayground.repository.data.Note

interface MainRepository {

    fun provideNotes(): List<Note>

    fun addNote(): List<Note>

    fun clearToken()
}