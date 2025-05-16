package com.example.psyconnect.Interface.Paciente

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// Paleta de colores específicos para DiarioScreen
val DiarioAzulSuaveClaro = Color(0xFFB3E5FC)
val DiarioAzulProfundoClaro = Color(0xFF0288D1)
val DiarioVerdeCalmaClaro = Color(0xFFC8E6C9)
val DiarioGrisSuave = Color(0xFFF5F5F5)

@Composable
fun DiarioScreen(navController: NavHostController) {
    var fecha by remember { mutableStateOf("") }
    var agradecimientos by remember { mutableStateOf("") }
    var metas by remember { mutableStateOf("") }
    var retos by remember { mutableStateOf("") }
    var afirmacion by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    var prioridades by remember { mutableStateOf("") }
    var estadoAnimoSeleccionado by remember { mutableStateOf("") }

    val emociones = listOf("😊", "😐", "😞")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .background(color = DiarioAzulSuaveClaro)
    ) {
        Text(
            text = "Registro diario de salud mental",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fecha
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = DiarioVerdeCalmaClaro
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Estado de ánimo
        Text("¿Cómo me siento hoy?", fontWeight = FontWeight.SemiBold)
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            emociones.forEach { emocion ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            width = if (estadoAnimoSeleccionado == emocion) 3.dp else 1.dp,
                            color = if (estadoAnimoSeleccionado == emocion) DiarioAzulProfundoClaro else Color.Gray
                        )
                        .background(
                            if (estadoAnimoSeleccionado == emocion) DiarioAzulProfundoClaro else Color.Transparent
                        )
                        .clickable {
                            estadoAnimoSeleccionado = emocion
                        }
                ) {
                    Text(
                        text = emocion,
                        fontSize = 28.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        EditableSection(title = "Hoy agradezco:", value = agradecimientos) {
            agradecimientos = it
        }

        EditableSection(title = "Hoy quiero lograr:", value = metas) {
            metas = it
        }

        EditableSection(title = "Mis retos:", value = retos) {
            retos = it
        }

        EditableSection(title = "Afirmación positiva:", value = afirmacion) {
            afirmacion = it
        }

        EditableSection(title = "Mis notas:", value = notas) {
            notas = it
        }

        EditableSection(title = "Prioridades del día:", value = prioridades, height = 120.dp) {
            prioridades = it
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun EditableSection(
    title: String,
    value: String,
    height: Dp = 100.dp,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(title, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            placeholder = { Text("Escribe aquí...") },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = DiarioVerdeCalmaClaro
            )
        )
    }
}