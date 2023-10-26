package com.ginamelinia.architectureplayground.repository.local

import android.content.SharedPreferences
import com.ginamelinia.architectureplayground.repository.LoginRepository
import com.ginamelinia.architectureplayground.repository.MainRepository
import com.ginamelinia.architectureplayground.repository.data.Note
import java.util.Date

class LocalRepository(
    private val sharedPreferences: SharedPreferences
) : MainRepository, LoginRepository{

    companion object {
        private const val KEY_TOKEN = "token"
        const val PREF_NAME = "sharedPref"
    }

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

    override fun validateInput(username: String, password: String): Boolean {
        return username.isNotEmpty()
                && username.isNotBlank()
                && password.isNotEmpty()
                && password.isNotBlank()
    }

    override fun authenticate(username: String, password: String): String? {
        return if (username == "gina" && password == "123456") {
            "token"
        } else {
            throw UnsupportedOperationException("username dan password salah!")
        }
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun loadToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }
}