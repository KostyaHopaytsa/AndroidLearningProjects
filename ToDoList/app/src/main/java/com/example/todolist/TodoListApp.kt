package com.example.todolist

import android.app.Application
import androidx.room.Room
import com.example.todolist.Database.TodoDatabase

class TodoListApp: Application() {
    val database by lazy {
        Room.databaseBuilder(
            this,
            TodoDatabase::class.java,
            "contacts.db"
        ).build()
    }
}