package com.ginamelinia.architectureplayground.page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ginamelinia.architectureplayground.R
import com.ginamelinia.architectureplayground.databinding.ActivityMainBinding
import com.ginamelinia.architectureplayground.page.adapter.NoteAdapter
import com.ginamelinia.architectureplayground.repository.data.Note
import com.ginamelinia.architectureplayground.repository.local.LocalRepository

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(
                    LocalRepository(
                        getSharedPreferences(
                            LocalRepository.PREF_NAME,
                            MODE_PRIVATE))) as T
            }
        }.create(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.lifecycleOwner = this
        binding?.viewModel = mainViewModel
        binding?.view = binding?.root
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

