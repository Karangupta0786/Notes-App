package com.example.finalnotesapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.finalnotesapp.Dao.TodoDao
import com.example.finalnotesapp.Database.TodoDatabase
import com.example.finalnotesapp.Model.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodoRepository {
    companion object{
        private var instance:TodoDatabase? = null

        private fun getDatabase(context: Context):TodoDatabase?{
            instance = TodoDatabase.initialiseDatabase(context)
            return instance
        }
    suspend fun insertTodo(context: Context,todo: Todo){
            instance = getDatabase(context)
            instance?.dao()?.insertTodo(todo)
    }

        suspend fun updateTodo(context: Context,todo: Todo){
            instance = getDatabase(context)
            instance?.dao()?.updateTodo(todo)
        }

        suspend fun deleteTodo(context: Context,todo: Todo){
                instance = getDatabase(context)
                instance?.dao()?.deleteTodo(todo)
        }

        fun getAllTodo(context: Context): LiveData<List<Todo>>? {
            instance = getDatabase(context = context)
            return instance?.dao()?.getAllTodos()
        }

}


}