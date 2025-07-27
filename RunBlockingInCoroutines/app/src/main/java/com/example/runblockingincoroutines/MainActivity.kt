package com.example.runblockingincoroutines

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.runblockingincoroutines.ui.theme.RunBlockingInCoroutinesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunBlockingInCoroutinesTheme {

            }
        }

        /*
        runBlocking — міст між звичайним та асинхронним кодом.

        Блокує потік, у якому викликається (зазвичай main-потік).
        Виконує всі корутини всередині і чекає, поки вони завершаться.
        Можна використовувати для виклику suspend функцій напряму в мейн.

        Краще не використовувати runBlocking в Android UI — він заморозить інтерфейс!
        Замість нього є lifecycleScope / viewModelScope / LaunchedEffect.
        */

        Log.d(TAG, "Before run blocking")
        runBlocking {
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "Finish IO coroutine1")
            }
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "Finish IO coroutine2")
            }
            Log.d(TAG, "Start run blocking")
            delay(5000L)
            Log.d(TAG, "Start run blocking")
        }
        Log.d(TAG, "After run blocking")
    }
}
