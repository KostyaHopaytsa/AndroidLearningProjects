package com.example.coroutineswithfirestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutineswithfirestore.ui.theme.CoroutinesWithFirestoreTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutinesWithFirestoreTheme {
                PersonScreen()
            }
        }
    }
}

@Composable
fun PersonScreen() {
    var person by remember { mutableStateOf<Person?>(null) }
    val lessonDocumented = Firebase.firestore
        .collection("coroutines")
        .document("lesson")
    val oleg = Person("Oleg", 40)
    LaunchedEffect(Unit) {
        delay(3000L)
        lessonDocumented.set(oleg).await()
        val result = lessonDocumented.get().await().toObject(Person::class.java)
        withContext(Dispatchers.Main) {
            person = result
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = person?.toString() ?: "Завантаження..."
        )
    }
}