package com.example.retrofitlearning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.HttpRetryException

class TodoViewModel : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch{
            try{
                val response = RetrofitInstance.api.getTodos()
                if (response.isSuccessful && response.body() != null) {
                    _todos.value = response.body()!!
                }
            }catch (e: HttpRetryException) {
                e.printStackTrace()
            }
        }
    }
}