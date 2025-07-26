package com.example.coroutinecontexts

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.coroutinecontexts.ui.theme.CoroutineContextsTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutineContextsTheme {
                MainScreen()
            }
        }

    }
}

suspend fun doNetworkCall(): String {
    delay(3000L)
    return "This is the answer"
}

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen() {
    val textState = remember { mutableStateOf("Loading...") }
    val launched = remember { mutableStateOf(false) }

    //this condition and scope can be simplify and safer by LaunchedEffect
    if (!launched.value) {
        launched.value = true
        /*Coroutine Dispatchers (Контексти виконання):

        Dispatchers.Default
        Для CPU-інтенсивних задач (сортування, парсинг, обчислення)
        Працює на пулі потоків (4+)

        Dispatchers.IO
        Для I/O-операцій (мережа, база даних, файли)
        Дуже легкий, підтримує багато паралельних потоків

        Dispatchers.Main
        Головний потік (UI)
        Використовується в Android для оновлення інтерфейсу

        Dispatchers.Unconfined
        Починає в поточному потоці, але може змінити його при first suspension
        Часто використовується для тестів або специфічних випадків

        newSingleThreadContext("MyThread")
        Створює абсолютно новий потік (витратно, краще уникати)

        withContext(...) використовується для переключення контекстів:
        withContext(Dispatchers.Main) { update UI }*/
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                textState.value = answer
            }
        }
    }

    Dummy(name = textState.value)
}

@Composable
fun Dummy(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = name,
        )
    }
}
