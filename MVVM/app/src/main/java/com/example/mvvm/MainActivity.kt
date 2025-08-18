package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {

//    private val viewModel by viewModels<ColorsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTheme {
                val viewModel = viewModel<ColorsViewModel>(
                    factory = object: ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return ColorsViewModel(
                                helloWorld = "Hello world!"
                            ) as T
                        }
                    }
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = viewModel.backgroundColor
                ) {
                    Button(onClick = {
                        viewModel.changeBackgroundColor()
                    }) {
                        Text("Change color")
                    }
                }
            }
        }
    }
}
