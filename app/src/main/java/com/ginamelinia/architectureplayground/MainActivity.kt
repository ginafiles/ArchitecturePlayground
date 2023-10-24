package com.ginamelinia.architectureplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var noteAdapter: NoteAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonCreateNote: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerView = findViewById(R.id.recycler)
        buttonCreateNote = findViewById(R.id.button_create_note)
        noteAdapter = NoteAdapter(mainViewModel)

        recyclerView?.adapter = noteAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this)

        noteAdapter.submitList(mainViewModel.fetchData())
        buttonCreateNote?.setOnClickListener{
            createNote()
        }

    }

    private fun createNote() {
        mainViewModel.createNote()
        val notes = mainViewModel.fetchData()
        noteAdapter.submitList(notes)
        noteAdapter.notifyDataSetChanged()
    }


}