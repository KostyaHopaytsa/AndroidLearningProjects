package com.example.dependencyinjection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dependencyinjection.hilt.HiltViewModel
import com.example.dependencyinjection.koin.KoinViewModel
import com.example.dependencyinjection.manual.ManualApplication
import com.example.dependencyinjection.manual.ManualViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hiltViewModel = hiltViewModel<HiltViewModel>()
            val koinViewModel = koinViewModel<KoinViewModel>()
            val manualViewModel = viewModel<ManualViewModel>(
                factory = ManualApplication.appModule.myViewModelFactory
            )
        }
    }
}