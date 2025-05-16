package com.example.psyconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.psyconnect.navigation.AppNavigation
import com.example.psyconnect.ui.theme.PsyConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Activa soporte para pantallas edge-to-edge (opcional en Android 13+)
        enableEdgeToEdge()

        // Carga Jetpack Compose UI
        setContent {
            PsyConnectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Inicia la navegación con la pantalla de bienvenida
                    AppNavigation(startDestination = "welcome")
                }
            }
        }
    }
}
