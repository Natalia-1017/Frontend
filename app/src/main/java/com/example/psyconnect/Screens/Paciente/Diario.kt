package com.example.psyconnect.Interfaces.Paciente

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

// Paleta de colores suaves para DiarioScreen (más calmados)
val DiarioAzulMuyClaro = Color(0xFFE3F2FD)
val DiarioAzulClaro = Color(0xFF64B5F6)
val DiarioVerdeMentaClaro = Color(0xFFC8E6C9)
val DiarioGrisSuave = Color(0xFFF5F5F5)

@Composable
fun DiarioScreen(navController: NavHostController) {
    var fecha by remember { mutableStateOf("") }
    var sentimientos by remember { mutableStateOf("") }
    var estadoAnimoSeleccionado by remember { mutableStateOf("") }

    val emociones = listOf("😊", "😐", "😞")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .background(color = DiarioAzulMuyClaro)
    ) {
        Text(
            text = "Registro diario de salud mental",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DiarioAzulClaro
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fecha
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = DiarioVerdeMentaClaro,
                focusedBorderColor = DiarioAzulClaro,
                unfocusedBorderColor = DiarioAzulClaro
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Estado de ánimo
        Text("¿Cómo me siento hoy?", fontWeight = FontWeight.SemiBold, color = DiarioAzulClaro)
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
                            color = if (estadoAnimoSeleccionado == emocion) DiarioAzulClaro else Color.Gray
                        )
                        .background(
                            if (estadoAnimoSeleccionado == emocion) DiarioAzulClaro.copy(alpha = 0.3f) else Color.Transparent
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

        EditableSection(
            title = "Hoy me sentí:",
            value = sentimientos,
            onValueChange = { sentimientos = it },
            backgroundColor = DiarioVerdeMentaClaro,
            borderColor = DiarioAzulClaro
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun EditableSection(
    title: String,
    value: String,
    height: Dp = 100.dp,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.LightGray
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(title, fontWeight = FontWeight.SemiBold, color = DiarioAzulClaro)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            placeholder = { Text("Escribe aquí...") },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = backgroundColor,
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                cursorColor = borderColor
            )
        )
    }
}
