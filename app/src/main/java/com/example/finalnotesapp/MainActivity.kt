package com.example.finalnotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalnotesapp.Adapters.TodoAdapter
import com.example.finalnotesapp.Model.Todo
import com.example.finalnotesapp.ViewModel.TodoViewModel
import com.example.finalnotesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewModel: TodoViewModel? =null
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerTodo.layoutManager = LinearLayoutManager(this)


        // Initialise ViewModel
        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        // Using the observe u will find the list of data in Lambda
        viewModel?.getAll(this)?.observe(this){list->
            adapter = TodoAdapter(this,list)
            binding.recyclerTodo.adapter = adapter
            adapter.notifyDataSetChanged()

            binding.etSearch.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val filteredData = list.filter {todoData->
                        todoData.title.contains(s.toString(),ignoreCase = true) || todoData.description.contains(s.toString(),ignoreCase = true)
                    }
                    adapter.onSearch(filteredData)
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }

        binding.imgAdd.setOnClickListener {
            Globals.isUpdating = false
            val intent = Intent(this@MainActivity,CreateNoteActivity::class.java)
            startActivity(intent)
        }
    }
}