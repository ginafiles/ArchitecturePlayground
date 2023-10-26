package com.ginamelinia.architectureplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ginamelinia.architectureplayground.Note
import com.ginamelinia.architectureplayground.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(
    private val local: MainRepository,
    private val remote: MainRepository
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun provideData() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            delay(1000)
            _notes.postValue(local.provideNotes())
        }
    }

    fun formateDate(date: Date): String {
        return  SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
    }

    fun createNote() {

        _notes.postValue(local.addNote())
    }
}