package com.example.todolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddTodoDialog(
    state: TodoState,
    onEvent: (TodoEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
            onEvent(TodoEvent.HideAddDialog)
        },
        confirmButton = {
            Button(onClick = {
                onEvent(TodoEvent.SaveTodo)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(TodoEvent.HideAddDialog)
            }) {
                Text("Cancel")
            }
        },
        title = {
            Text(text = "Add Todo")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.description,
                    onValueChange = {
                        onEvent(TodoEvent.SetDescription(it))
                    },
                    placeholder = { Text("Description") }
                )
                TextField(
                    value = state.deadline,
                    onValueChange = {
                        onEvent(TodoEvent.SetDeadline(it))
                    },
                    placeholder = { Text("deadline") }
                )
            }
        }
    )
}