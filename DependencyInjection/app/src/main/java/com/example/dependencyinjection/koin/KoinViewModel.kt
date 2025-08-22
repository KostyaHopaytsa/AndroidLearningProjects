package com.example.dependencyinjection.koin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dependencyinjection.MyAppDatabase
import com.example.dependencyinjection.MyRepository

class KoinViewModel(
    private val database: MyAppDatabase,
    private val repository: MyRepository
): ViewModel() {

    var state by mutableStateOf("database not synced")
        private set

    fun syncDatabase() {
        val data = repository.fetchData()
        database.addData(data)
        state = "database synced"
    }
}