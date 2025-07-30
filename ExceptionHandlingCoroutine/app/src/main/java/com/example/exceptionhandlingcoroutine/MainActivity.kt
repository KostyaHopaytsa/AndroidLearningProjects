package com.example.exceptionhandlingcoroutine

import android.os.Bundle
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
import androidx.lifecycle.lifecycleScope
import com.example.exceptionhandlingcoroutine.ui.theme.ExceptionHandlingCoroutineTheme
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.net.HttpRetryException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val deferred = lifecycleScope.async {
//            val string = async {
//                delay(1L)
//                throw Exception("error")
//            }
//        }
//        lifecycleScope.launch {
//            Don't catch exceptions like that
//            try {
//                deferred.await()
//            } catch(e: Exception) {
//                e.printStackTrace()
//            }
//        }

//        right way to catch exceptions
//        val handler = CoroutineExceptionHandler { _, throwable ->
//            println("Caught exception: $throwable")
//        }
//        lifecycleScope.launch(handler) {
//            launch {
//                throw Exception("error")
//            }
//        }

//        val handler = CoroutineExceptionHandler { _, throwable ->
//            println("Caught exception: $throwable")
//        }
//        CoroutineScope(Dispatchers.Main + handler).launch {
//            supervisorScope {
//                launch {
//                    delay(300L)
//                    throw Exception("Coroutine 1 failed")
//                }
//                launch {
//                    delay(400L)
//                    println("Coroutine 2 finished")
//                }
//            }
//        }

        lifecycleScope.launch {
            val job = launch {
                try {
                    delay(500L)
                }
//                both variants work

//                catch (e: HttpRetryException) {
//                    e.printStackTrace()
//                }
                
                catch (e: Exception) {
                    if(e is CancellationException){
                        throw e
                    }
                    e.printStackTrace()
                }

                println("Coroutine finished")
            }
            delay(300L)
            job.cancel()
        }
    }
}
