package com.example.asyncawaitcoroutines

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
import com.example.asyncawaitcoroutines.ui.theme.AsyncAwaitCoroutinesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsyncAwaitCoroutinesTheme {

            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
//                terrible way to write it
//                var answer1: String? = null
//                var answer2: String? = null
//                val job1 = launch { answer1 = networkCall1() }
//                val job2 = launch { answer2 = networkCall2() }
//                job1.join()
//                job2.join()

                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }
                Log.d(TAG, "Answer1 is ${answer1.await()}")
                Log.d(TAG, "Answer2 is ${answer2.await()}")
            }
            Log.d(TAG, "Request took $time ms.")
        }

    }

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer2"
    }
}
