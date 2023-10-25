package com.ginamelinia.architectureplayground

import android.os.Bundle
import android.widget.ViewFlipper
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
    private lateinit var flipper: ViewFlipper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerView = findViewById(R.id.recycler)
        buttonCreateNote = findViewById(R.id.button_create_note)
        noteAdapter = NoteAdapter(mainViewModel)
        flipper = findViewById(R.id.flipper)

        recyclerView?.adapter = noteAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this)

        buttonCreateNote?.setOnClickListener{
            createNote()
        }

        observeNotes()

        mainViewModel.fetchData()

    }


    private fun createNote() {
        mainViewModel.createNote()
    }


    private fun observeNotes() {
        mainViewModel.notes.observe(this) { notes ->
            noteAdapter?.submitList(notes)
            noteAdapter?.notifyDataSetChanged()
        }
        mainViewModel.loading.observe(this) { isLoading ->
            handleLoadingState(isLoading)
        }

    }

    private  fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            flipper?.displayedChild = 0 // index 0 --> progress bar
        } else {
            flipper.displayedChild = 1 // index 1 --> recycler view
        }
    }

}