package com.example.finalnotesapp.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalnotesapp.Model.Todo
import com.example.finalnotesapp.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel :ViewModel() {

    fun insert(todo: Todo,context: Context){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                TodoRepository.insertTodo(context,todo)
            }
        }
    }

    fun getAll(context: Context):LiveData<List<Todo>>?{
        return TodoRepository.getAllTodo(context)
    }

    fun updateTodo(todo: Todo,context: Context){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                TodoRepository.updateTodo(context, todo)
            }
        }
    }

    fun deleteTodo(todo: Todo,context: Context){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                TodoRepository.deleteTodo(context, todo)
            }
        }
    }






}