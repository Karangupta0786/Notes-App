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
    private lateinit var binding: ActivityCreateNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        if (intent.getStringExtra("title")?.isNotEmpty() == true || intent.getStringExtra("desc")?.isNotEmpty()==true){
            // getting the title & desc from the Adapter if available!
            val title = intent.getStringExtra("title").toString()
            val desc = intent.getStringExtra("desc").toString()

            binding.edtNote.setText(desc)   // setting all the previous data in editText if available!!
            binding.edtTitle.setText(title)
        }

        binding.imgShare.setOnClickListener { // To share the data to another app.
            // 1. store all the data in variables
            val title = binding.edtTitle.text.toString()
            val desc = binding.edtNote.text.toString()

            // 2. create an intent variable.
            val intent = Intent()

            // 3. set the action of intent as Intent.ACTION_SEND
            intent.action = Intent.ACTION_SEND

            intent.putExtra(Intent.EXTRA_TEXT,"$title\n\n$desc")

            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent,"Share via:"))

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
    }

        private fun getFormattedDate():String {
            val currDate = Date()
            val formattedDate = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
            return formattedDate.format(currDate)
        }
}