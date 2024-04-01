package com.example.finalnotesapp.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalnotesapp.CreateNoteActivity
import com.example.finalnotesapp.Globals
import com.example.finalnotesapp.MainActivity
import com.example.finalnotesapp.Model.Todo
import com.example.finalnotesapp.R
import com.example.finalnotesapp.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

class TodoAdapter(private val applicationContext:Context, var todoList: List<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    interface OnTextChangeListener{
        fun setOnTextChange()
    }

    class TodoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val cardLayout: CardView = itemView.findViewById(R.id.card_layout)

        val title:TextView = itemView.findViewById(R.id.txt_title)
        val desc:TextView = itemView.findViewById(R.id.txt_desc)
        val delete:ImageView = itemView.findViewById(R.id.img_delete)
        val date:TextView = itemView.findViewById(R.id.txt_date)
//        val priority:TextView = itemView.findViewById(R.id.txt_priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(applicationContext)
        val view = inflater.inflate(R.layout.item_todos,parent,false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currData = todoList[position]

        holder.title.text = currData.title
        holder.desc.text = currData.description
        holder.date.text = currData.creationDate

//        holder.priority.text = currData.priority


        holder.delete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                TodoRepository.deleteTodo(applicationContext,currData)
            }
        }

        val intent = Intent(applicationContext,CreateNoteActivity::class.java)

        holder.cardLayout.setOnClickListener {
            Globals.isUpdating = true

            Log.e("clicking on item",currData.title.toString())
            intent.putExtra("title",currData.title.toString())
            intent.putExtra("desc",currData.description.toString())
            intent.putExtra("id",currData.id)
            Log.e("the current id is:",currData.id.toString())
            applicationContext.startActivity(intent)
        }
    }

    fun onSearch(filteredList:List<Todo>){
        todoList = filteredList
        notifyDataSetChanged()
    }

}