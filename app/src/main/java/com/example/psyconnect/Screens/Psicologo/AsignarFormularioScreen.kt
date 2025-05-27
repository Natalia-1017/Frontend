package com.example.psyconnect.Interfaces.Psicologo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AsignarFormularioScreen(
    navController: NavController
) {
    var pacienteSeleccionado by remember { mutableStateOf("") }
    var nombreFormulario by remember { mutableStateOf("") }
    var preguntaActual by remember { mutableStateOf("") }
    var preguntas by remember { mutableStateOf(listOf<String>()) }
    var expanded by remember { mutableStateOf(false) }

    // Ejemplo temporal de pacientes para dropdown
    val pacientes = listOf("Paciente 1", "Paciente 2", "Paciente 3")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulSuaveClaro)
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 8.dp,
            backgroundColor = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Asignar Formulario",
                    style = MaterialTheme.typography.h6.copy(
                        color = AzulProfundoClaro,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                // Dropdown para seleccionar paciente
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = pacienteSeleccionado,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Seleccionar paciente") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true }
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        pacientes.forEach { paciente ->
                            DropdownMenuItem(onClick = {
                                pacienteSeleccionado = paciente
                                expanded = false
                            }) {
                                Text(paciente)
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = nombreFormulario,
                    onValueChange = { nombreFormulario = it },
                    label = { Text("Nombre del formulario") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Pregunta actual + botón agregar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = preguntaActual,
                        onValueChange = { preguntaActual = it },
                        label = { Text("Agregar pregunta") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (preguntaActual.isNotBlank()) {
                                preguntas = preguntas + preguntaActual
                                preguntaActual = ""
                            }
                        }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar")
                    }
                }

                // Mostrar preguntas añadidas
                preguntas.forEachIndexed { index, pregunta ->
                    Text("${index + 1}. $pregunta", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        // Aquí va la acción de asignar (dejamos vacío)
                    },
                    enabled = pacienteSeleccionado.isNotBlank()
                            && nombreFormulario.isNotBlank()
                            && preguntas.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = VerdeCalmaClaro),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Asignar", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
