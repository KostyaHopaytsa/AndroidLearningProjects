package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<TodoListViewModel> {
        TodoListViewModelFactory(
            (application as TodoListApp).database.dao
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                val state by viewModel.state.collectAsState()
                TodoListScreen(state = state, onEvent = viewModel::onEven)
            }
        }
    }
}
