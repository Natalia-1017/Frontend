package com.example.psyconnect.Interfaces.Paciente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class Sesion(
    val fecha: String,
    val psicologo: String,
    val tipo: String,
    val estado: String
)

val sesionesEjemplo = listOf(
    Sesion("10/05/2025", "Dra. Ana Torres", "Individual", "Completada"),
    Sesion("03/05/2025", "Dr. Juan Pérez", "Seguimiento", "Completada"),
    Sesion("28/04/2025", "Dra. Ana Torres", "Inicial", "Cancelada"),
    Sesion("20/04/2025", "Dra. Ana Torres", "Individual", "Pendiente")
)

@Composable
fun HistorialSesionesScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DiarioGrisSuave)
            .padding(16.dp)
    ) {
        Text(
            text = "Historial de Sesiones",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sesionesEjemplo) { sesion ->
                SesionCard(sesion)
            }
        }
    }
}

@Composable
fun SesionCard(sesion: Sesion) {
    val colorEstado = when (sesion.estado) {
        "Completada" -> Color(0xFFC8E6C9) // Verde
        "Pendiente" -> Color(0xFFFFF9C4) // Amarillo claro
        "Cancelada" -> Color(0xFFFFCDD2) // Rojo claro
        else -> Color.LightGray
    }

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Fecha: ${sesion.fecha}", fontWeight = FontWeight.Bold)
            Text(text = "Psicólogo: ${sesion.psicologo}")
            Text(text = "Tipo: ${sesion.tipo}")
            Text(
                text = "Estado: ${sesion.estado}",
                modifier = Modifier
                    .padding(top = 4.dp)
                    .background(colorEstado)
                    .padding(4.dp),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
