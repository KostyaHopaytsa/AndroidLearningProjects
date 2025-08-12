package com.example.todolist

import com.example.todolist.Database.Todo

sealed interface TodoEvent {
    data object SaveTodo: TodoEvent
    data object ShowAddDialog: TodoEvent
    data object HideAddDialog: TodoEvent
    data object ShowUnfoldDialog: TodoEvent
    data object HideUnfoldDialog: TodoEvent
    data class SetDescription(val description: String): TodoEvent
    data class SetDeadline(val deadline: String): TodoEvent
    data class SetChecked(val todo: Todo, val checked: Boolean): TodoEvent
    data class DeleteTodo(val todo: Todo): TodoEvent
}