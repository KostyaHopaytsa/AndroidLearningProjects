package com.example.intentandintentfilters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.intentandintentfilters.ui.theme.IntentAndIntentFiltersTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        intent.getStringExtra("")
        setContent {
            IntentAndIntentFiltersTheme {
                Text(text = "SecondActivity")
            }
        }
    }
}