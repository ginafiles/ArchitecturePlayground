package com.ginamelinia.architectureplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel : ViewModel() {

    private var i = 0

    private val noteList: MutableList<Note> = mutableListOf()

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun provideData() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            delay(1000)
            _notes.postValue(noteList)
        }
    }

    fun formateDate(date: Date): String {
        return  SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
    }

    fun createNote() {
        val note = Note(Date(), "note: $i")
        i++

        noteList.add(note)
        _notes.postValue(noteList)
    }


    //-- Ini versi sebelum data binding --
//    fun createNote() {
//        val note = Note(Date(), "note: $i")
//        i++
//
//        noteList.add(note)
//        fetchData()
//    }

//    fun fetchData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            withContext(Dispatchers.Main) {
//                _loading.value = true
//            }
//            delay(3000)
//            withContext(Dispatchers.Main) {
//                _notes.value = this@MainViewModel.noteList
//                _loading.value = false
//            }
//        }
//
//    }
}