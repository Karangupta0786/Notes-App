package com.example.finalnotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.finalnotesapp.Adapters.TodoAdapter
import com.example.finalnotesapp.Model.Todo
import com.example.finalnotesapp.ViewModel.TodoViewModel
import com.example.finalnotesapp.databinding.ActivityCreateNoteBinding
import com.example.finalnotesapp.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateNoteActivity : AppCompatActivity() {
    private var viewModel: TodoViewModel? = null
//    private lateinit var adapter: TodoAdapter
    private lateinit var binding: ActivityCreateNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        if (intent.getStringExtra("title")?.isNotEmpty() == true ||intent.getStringExtra("desc")?.isNotEmpty()==true){
            val title = intent.getStringExtra("title").toString()
            val desc = intent.getStringExtra("desc").toString()

            binding.edtNote.setText(desc)
            binding.edtTitle.setText(title)
        }

        binding.imgSaveCreateNote.setOnClickListener {
            if (Globals.isUpdating){
                val title = binding.edtTitle.text.toString()
                val desc = binding.edtNote.text.toString()
                val id = intent.getLongExtra("id",0)

                CoroutineScope(Dispatchers.IO).launch {
                    val todo = Todo(id,title,desc,getFormattedDate())
                    viewModel?.updateTodo(todo,applicationContext)
                }
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                var title = binding.edtTitle.text.toString()
                var note = binding.edtNote.text.toString()

                intent.putExtra("time",getFormattedDate())

                var todo = Todo(title,note,getFormattedDate())
                Log.e("Title is:===",title)
                Log.e("Note or Description is:===",note)
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel?.insert(todo,this@CreateNoteActivity)
                }
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

//        adapter.notifyDataSetChanged()

    }

        private fun getFormattedDate():String {
            val currDate = Date()
            val formattedDate = SimpleDateFormat("dd/mm/yyyy hh:mm:ss", Locale.getDefault())
            return formattedDate.format(currDate)
        }
}