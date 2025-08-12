package com.example.todolist

import com.example.todolist.Database.Todo

data class TodoState(
    val todos: List<Todo> = emptyList(),
    val description: String = "",
    val deadline: String = "",
    val checked: Boolean = false,
    val isAddingTodo: Boolean = false,
    val isUnfoldTodo: Boolean = false
)
