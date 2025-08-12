package com.example.todolist.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val description: String,
    val deadline: String,
    val checked: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
