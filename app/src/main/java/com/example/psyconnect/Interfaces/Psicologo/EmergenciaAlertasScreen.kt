package com.example.psyconnect.Interfaces.Psicologo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AlertaEmergencia(
    val nombrePaciente: String,
    val fechaHora: String,
    val mensaje: String
)

@Composable
fun EmergenciaAlertasScreen(alertas: List<AlertaEmergencia>) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Alertas de Emergencia", fontSize = 20.sp) },
            backgroundColor = Color(0xFFE57373),
            contentColor = Color.White
        )

        if (alertas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay alertas de emergencia activas.", fontSize = 16.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(alertas) { alerta ->
                    Card(
                        elevation = 4.dp,
                        backgroundColor = Color(0xFFFFEBEE),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Paciente: ${alerta.nombrePaciente}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Red
                            )
                            Text(
                                text = "Fecha y hora: ${alerta.fechaHora}",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = alerta.mensaje,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
