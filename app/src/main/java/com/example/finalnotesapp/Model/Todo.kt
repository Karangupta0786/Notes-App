package com.example.finalnotesapp.Model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo_table")
class Todo {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var title: String =""
    var description: String =""
    var creationDate:String =""
//    var priority: String

    @Ignore
    constructor(title: String, description: String,creationDate: String) {
        this.title = title
        this.description = description
        this.creationDate = creationDate
//        this.priority = priority
    }

    constructor(id: Long, title: String, description: String,creationDate:String) {
        this.id = id
        this.title = title
        this.description = description
        this.creationDate = creationDate
    }
}