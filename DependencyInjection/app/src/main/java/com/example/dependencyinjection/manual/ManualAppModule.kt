package com.example.dependencyinjection.manual

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjection.MyAppDatabase
import com.example.dependencyinjection.MyRepository
import com.example.dependencyinjection.koin.KoinViewModel

interface ManualAppModule {
    val database: MyAppDatabase
    val repository: MyRepository
    val myViewModelFactory: ViewModelProvider.Factory
}

class ManualAppModuleImpl(
    private val appContext: Context
): ManualAppModule {
    override val database: MyAppDatabase by lazy {
        MyAppDatabase(appContext)
    }

    override val repository: MyRepository
        get() = MyRepository()

    override val myViewModelFactory: ViewModelProvider.Factory
        get() = viewModelFactory {
            KoinViewModel(
                database = database,
                repository = repository
            )
        }
}