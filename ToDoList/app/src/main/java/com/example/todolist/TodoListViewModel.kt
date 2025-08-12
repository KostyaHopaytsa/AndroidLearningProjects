package com.example.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.Database.Todo
import com.example.todolist.Database.TodoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(private val dao: TodoDao): ViewModel() {
    val todos = dao.getAllTodos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    private val _state = MutableStateFlow(TodoState())
    val state: StateFlow<TodoState> = _state

    fun onEven(event: TodoEvent) {
        when(event) {
            is TodoEvent.DeleteTodo -> {
                viewModelScope.launch {
                    dao.deleteTodo(event.todo)
                }
            }
            TodoEvent.SaveTodo -> {
                val description = state.value.description
                val deadline = state.value.deadline
                val checked = state.value.checked

                if (description.isBlank() || deadline.isBlank()) {
                    return
                }

                val todo = Todo(
                    description = description,
                    deadline = deadline,
                    checked = checked
                )
                viewModelScope.launch {
                    dao.upsertTodo(todo)
                }
                _state.update { it.copy(
                    isAddingTodo = false,
                    description = "",
                    deadline = "",
                    checked = false
                ) }
            }
            is TodoEvent.SetChecked -> {
                _state.update { it.copy(
                    checked = event.checked
                )}
            }
            is TodoEvent.SetDeadline -> {
                _state.update { it.copy(
                    deadline = event.deadline
                )}
            }
            is TodoEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                )}
            }
            TodoEvent.HideAddDialog -> {
                _state.update { it.copy(
                    isAddingTodo = false
                ) }
            }
            TodoEvent.HideUnfoldDialog -> {
                _state.update { it.copy(
                    isUnfoldTodo = false
                ) }
            }
            TodoEvent.ShowAddDialog -> {
                _state.update { it.copy(
                    isAddingTodo = true
                )}
            }
            TodoEvent.ShowUnfoldDialog -> {
                _state.update { it.copy(
                    isUnfoldTodo = true
                )}
            }
        }
    }
}