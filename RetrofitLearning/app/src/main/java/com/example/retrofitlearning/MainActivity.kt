package com.example.retrofitlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofitlearning.ui.theme.RetrofitLearningTheme

class MainActivity : ComponentActivity() {

    private val viewModel = TodoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitLearningTheme {
                val todos by viewModel.todos.collectAsState()
                TodoList(todos = todos)
            }
        }
    }
}

@Composable
fun TodoList(
    todos: List<Todo>
) {
    LazyColumn() {
        items(todos.size) { index ->
            val todo = todos[index]
            Item(todo)
        }
    }
}

@Composable
fun Item(
    todo: Todo
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        Text(
            text = todo.title,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        )
        Checkbox(
            checked = todo.completed,
            onCheckedChange = null,
            modifier = Modifier
                .weight(0.3f)
                .align(Alignment.CenterVertically)
        )
    }
}