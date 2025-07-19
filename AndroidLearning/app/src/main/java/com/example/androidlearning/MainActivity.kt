package com.example.androidlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onDestroy")
        setContent {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(top = 100.dp, start = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .fillMaxHeight(0.2f)
                        .background(Color.Cyan)
                        .border(5.dp, Color.Black)
                        .padding(start = 16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text("Hello",
                        modifier = Modifier
                        .offset(50.dp, 16.dp)
                    )
                    Text("World")
                }
                Row (
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .background(Color.Blue),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text("Hello")
                    Text("World")
                }
                Column (
                    modifier = Modifier
                        .fillMaxSize(0.25f)
                        .background(Color.Red),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Hello")
                    Spacer(modifier = Modifier
                        .height(50.dp)
                        .clickable {  }
                    )
                    Text("World")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }
}
