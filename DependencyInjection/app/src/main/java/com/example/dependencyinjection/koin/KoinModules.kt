package com.example.dependencyinjection.koin

import com.example.dependencyinjection.MyAppDatabase
import com.example.dependencyinjection.MyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val appModule = module {
    single { MyAppDatabase(androidContext()) }
}

val viewModelModule = module {
    factoryOf(::MyRepository)
    viewModelOf(::KoinViewModel)
}