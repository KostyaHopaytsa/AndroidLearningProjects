package com.example.lifecyclescopeandviewmodelscope

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.lifecyclescopeandviewmodelscope.ui.theme.LifecycleScopeAndViewModelScopeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifecycleScopeAndViewModelScopeTheme {
                NavigationButton()
            }
        }
    }
}

@Composable
fun NavigationButton() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val activity = context as? Activity
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            lifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "Second screen running...")
                }
            }
            lifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                delay(3000L)
                context.startActivity(Intent(context, SecondActivity::class.java))
                activity?.finish()
            }
        }
        ) {
            Text("Go to SecondActivity")
        }
    }
}
