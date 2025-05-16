package com.example.psyconnect.Interface.Paciente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.psyconnect.Model.FormularioData

@Composable
fun FormularioScreen(navController: NavController, onFormularioEnviado: (FormularioData) -> Unit) {
    var respuesta1 by remember { mutableStateOf("") }
    var respuesta2 by remember { mutableStateOf("") }
    var respuesta3 by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulSuaveClaro)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 8.dp,
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Formulario de Seguimiento",
                    style = MaterialTheme.typography.h6.copy(
                        color = AzulProfundoClaro,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                OutlinedTextField(
                    value = respuesta1,
                    onValueChange = { respuesta1 = it },
                    label = { Text("¿Cómo te sientes hoy?") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = respuesta2,
                    onValueChange = { respuesta2 = it },
                    label = { Text("¿Has dormido bien?") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = respuesta3,
                    onValueChange = { respuesta3 = it },
                    label = { Text("¿Te has sentido ansioso últimamente?") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val formulario = FormularioData(
                            imageUrl = "https://i.pinimg.com/736x/05/4f/b4/054fb4c0ca125008cc2abfe3cefa25ab.jpg",
                            titulo = "Formulario de Seguimiento",
                            estadoFormulario = "Formulario rellenado",
                            fechaEnvio = "16/05/2025",
                            preguntas = listOf(
                                "¿Cómo te sientes hoy?" to respuesta1,
                                "¿Has dormido bien?" to respuesta2,
                                "¿Te has sentido ansioso últimamente?" to respuesta3
                            )
                        )
                        onFormularioEnviado(formulario)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = VerdeCalmaClaro)
                ) {
                    Text("Enviar", color = Color.Black)
                }
            }
        }
    }
}
