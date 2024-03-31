package com.example.finalnotesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalnotesapp.Dao.TodoDao
import com.example.finalnotesapp.Model.Todo

@Database(entities = [Todo::class], version = 2)
abstract class TodoDatabase :RoomDatabase() {
    abstract fun dao():TodoDao

    companion object{
        @Volatile
        var INSTANCE:TodoDatabase? = null

        private const val database_name = "Todo-database"

        fun initialiseDatabase(context: Context):TodoDatabase? {

            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    database_name
                ).build()
            }

            return INSTANCE
            }
        }
}