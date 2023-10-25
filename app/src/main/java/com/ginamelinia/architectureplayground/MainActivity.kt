package com.ginamelinia.architectureplayground

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ginamelinia.architectureplayground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.lifecycleOwner = this
        binding?.viewModel = mainViewModel

        binding?.adapter = NoteAdapter(mainViewModel)
        binding?.layoutManager = LinearLayoutManager(this)
    }
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, noteAdapter: NoteAdapter?) {
    recyclerView.adapter = noteAdapter
}

@BindingAdapter("setLayoutManager")
fun setLayoutManager(recyclerView: RecyclerView, layoutManager: LayoutManager) {
    recyclerView.layoutManager = layoutManager
}
@SuppressLint("NotifyDataSetChanged")
@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<Note>?) {
    (recyclerView.adapter as NoteAdapter).submitList(list)
    (recyclerView.adapter as NoteAdapter).notifyDataSetChanged()
}

