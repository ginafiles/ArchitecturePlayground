package com.ginamelinia.architectureplayground.repository

import com.ginamelinia.architectureplayground.Note

interface MainRepository {

    fun provideNotes(): List<Note>

    fun addNote(): List<Note>
}