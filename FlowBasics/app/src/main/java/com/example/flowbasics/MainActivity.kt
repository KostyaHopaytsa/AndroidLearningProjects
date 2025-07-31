package com.example.flowbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowbasics.ui.theme.FlowBasicsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowBasicsTheme {
                val viewModel = viewModel<MainViewModel>()

//                val time = viewModel.countDownFlow.collectAsState(initial = 10)
//                    Text(
//                        text = time.value.toString(),
//                        fontSize = 30.sp,
//                        modifier = Modifier.align(Alignment.Center)
//                    )

//                val count = viewModel.stateFlow.collectAsState(initial = 0)
//                that button for StateFlow count
//                Box(modifier = Modifier.fillMaxSize()) {
//                    Button(
//                        onClick = { viewModel.incrementCounter() },
//                        modifier = Modifier.align(Alignment.Center)
//                    ) {
//                        Text(text = "Counter: ${count.value}")
//                    }
//                }

                var latestNumber by remember { mutableStateOf<Int?>(null) }
                LaunchedEffect(key1 = true) {
                    viewModel.sharedFlow.collect { number ->
                        latestNumber = number
                    }
                }

                Box (modifier = Modifier.fillMaxSize()){
                    Text(
                        text = "Latest squared number: ${latestNumber}",
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
