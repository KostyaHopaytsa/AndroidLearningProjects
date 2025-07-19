package com.example.effecthandlers

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EffectHandlersDemo()
        }
    }
}

@Composable
fun EffectHandlersDemo() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var counter by remember { mutableStateOf(0) }

    val latestMessage = rememberUpdatedState("Counter = $counter")

    // 1. LaunchedEffect — запускається 1 раз
    LaunchedEffect(Unit) {
        Log.d("Effect", "LaunchedEffect: Run once on first composition")
        delay(500)
    }

    // 2. SideEffect — викликається після кожного складання
    SideEffect {
        Log.d("Effect", "SideEffect: Called after every recomposition")
    }

    // 3. DisposableEffect — підписка та очищення
    DisposableEffect(context) {
        Log.d("Effect", "DisposableEffect: Subscribed")
        onDispose {
            Log.d("Effect", "DisposableEffect: Cleaned up")
        }
    }

    // 4. snapshotFlow — стежить за змінами counter
    LaunchedEffect(Unit) {
        snapshotFlow { counter }
            .filter { it % 2 == 0 }
            .collectLatest {
                Log.d("Effect", "snapshotFlow: Even number $it")
            }
    }

    // 5. produceState — симуляція завантаження даних
    val data by produceState(initialValue = "Loading...") {
        delay(1500)
        value = "Loaded from network"
    }

    // 6. derivedStateOf — залежний стан
    val isEven by remember {
        derivedStateOf { counter % 2 == 0 }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$data", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Counter: $counter (even: $isEven)")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { counter++ }) {
            Text("Increment")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            coroutineScope.launch {
                Toast.makeText(context, latestMessage.value, Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Show Toast (rememberUpdatedState)")
        }
    }
}