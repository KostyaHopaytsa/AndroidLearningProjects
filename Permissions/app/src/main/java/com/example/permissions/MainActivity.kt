package com.example.permissions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.permissions.ui.theme.PermissionsTheme
import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionsTheme {
                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                    )
                )

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifecycleOwner) {
                    val observer = LifecycleEventObserver {_, event ->
                        if(event == Lifecycle.Event.ON_START) {
                            permissionsState.launchMultiplePermissionRequest()
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    permissionsState.permissions.forEach{ perm ->
                        when(perm.permission){
                            Manifest.permission.CAMERA -> {
                                when{
                                    perm.hasPermission -> {
                                        Text(text = "Camera permission accepted")
                                    }
                                    perm.shouldShowRationale -> {
                                        Text(text = "Camera permission is needed" +
                                                "to access the camera")
                                    }
                                    perm.isPermanentlyDenied() -> {
                                        Text(text = "Camera permission was permanently" +
                                                "denied. You can enable it in the app" +
                                                "settings")
                                    }
                                }
                            }
                            Manifest.permission.RECORD_AUDIO -> {
                                when{
                                    perm.hasPermission -> {
                                        Text(text = "Record audio permission accepted")
                                    }
                                    perm.shouldShowRationale -> {
                                        Text(text = "Record audio permission is needed" +
                                                "to access the micro")
                                    }
                                    perm.isPermanentlyDenied() -> {
                                        Text(text = "Record audio permission was " +
                                                "permanently denied. You can enable " +
                                                "it in the app settings")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

