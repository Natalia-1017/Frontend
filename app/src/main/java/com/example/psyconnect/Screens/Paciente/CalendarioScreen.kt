package com.example.psyconnect.Interfaces.Paciente

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.psyconnect.ViewModel.CalendarioViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

val AzulSuave = Color(0xFFB3E5FC)
val AzulProfundo = Color(0xFF0288D1)
val VerdeCalma = Color(0xFFC8E6C9)
val GrisSuave = Color(0xFFF5F5F5)

@Composable
fun CalendarioScreen(
    navController: NavHostController,
    viewModel: CalendarioViewModel = viewModel()
) {
    var mostrarDialogo by remember { mutableStateOf(true) }
    var mostrarDialogoCita by remember { mutableStateOf(false) }
    var diaSeleccionado by remember { mutableStateOf<LocalDate?>(null) }
    var descripcionCita by remember { mutableStateOf("") }
    var horaCita by remember { mutableStateOf("") }

    if (mostrarDialogo) {
        Dialog(onDismissRequest = {
            mostrarDialogo = false
            navController.navigate("dashboard_paciente") // Ir al dashboard al cerrar
        }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                CalendarioContent(
                    onCerrar = {
                        mostrarDialogo = false
                        navController.navigate("dashboard_paciente") // Ir al dashboard al cerrar
                    },
                    viewModel = viewModel,
                    mostrarDialogoCita = mostrarDialogoCita,
                    setMostrarDialogoCita = { mostrarDialogoCita = it },
                    diaSeleccionado = diaSeleccionado,
                    setDiaSeleccionado = { diaSeleccionado = it },
                    descripcionCita = descripcionCita,
                    setDescripcionCita = { descripcionCita = it },
                    horaCita = horaCita,
                    setHoraCita = { horaCita = it }
                )
            }
        }
    } else {
        navController.navigate("dashboard_paciente") // Asegura que se muestre el dashboard
    }
}

@Composable
fun CalendarioContent(
    onCerrar: () -> Unit,
    viewModel: CalendarioViewModel,
    mostrarDialogoCita: Boolean,
    setMostrarDialogoCita: (Boolean) -> Unit,
    diaSeleccionado: LocalDate?,
    setDiaSeleccionado: (LocalDate?) -> Unit,
    descripcionCita: String,
    setDescripcionCita: (String) -> Unit,
    horaCita: String,
    setHoraCita: (String) -> Unit
) {
    val sesiones = viewModel.sesiones
    var mesActual by remember { mutableStateOf(YearMonth.now()) }

    val diasMes = remember(mesActual) {
        (1..mesActual.lengthOfMonth()).map { dia -> mesActual.atDay(dia) }
    }
    val sesionesDelDia = sesiones.filter { it.fecha == diaSeleccionado }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(GrisSuave)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                mesActual = mesActual.minusMonths(1)
                setDiaSeleccionado(null)
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Mes anterior", tint = AzulProfundo)
            }

            Text(
                text = mesActual.month.getDisplayName(java.time.format.TextStyle.FULL, Locale("es"))
                    .replaceFirstChar { it.uppercase() } + " de ${mesActual.year}",
                style = MaterialTheme.typography.titleLarge,
                color = AzulProfundo
            )

            IconButton(onClick = {
                mesActual = mesActual.plusMonths(1)
                setDiaSeleccionado(null)
            }) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "Mes siguiente", tint = AzulProfundo)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("DO", "LU", "MA", "MI", "JU", "VI", "SA").forEach {
                Text(
                    text = it,
                    color = AzulProfundo,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        val primerDiaSemana = mesActual.atDay(1).dayOfWeek.value % 7
        val totalDias = mesActual.lengthOfMonth()
        val totalCeldas = primerDiaSemana + totalDias

        Column {
            for (fila in 0 until totalCeldas step 7) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (columna in 0..6) {
                        val indice = fila + columna
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            if (indice < primerDiaSemana || indice >= totalDias + primerDiaSemana) {
                                Spacer(modifier = Modifier.size(36.dp))
                            } else {
                                val dia = mesActual.atDay(indice - primerDiaSemana + 1)
                                val tieneSesion = sesiones.any { it.fecha == dia }

                                Surface(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(50))
                                        .clickable {
                                            setDiaSeleccionado(dia)
                                            if (!tieneSesion) {
                                                setMostrarDialogoCita(true)
                                            }
                                        },
                                    color = when {
                                        dia == LocalDate.now() -> AzulSuave
                                        tieneSesion -> AzulProfundo
                                        else -> Color.Transparent
                                    }
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = dia.dayOfMonth.toString(),
                                            color = if (tieneSesion) Color.White else Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        diaSeleccionado?.let { dia ->
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Sesiones para ${dia.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("es")))}:",
                style = MaterialTheme.typography.titleMedium,
                color = AzulProfundo
            )

            if (sesionesDelDia.isNotEmpty()) {
                sesionesDelDia.forEach {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = VerdeCalma)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = "Hora: ${it.hora}", fontWeight = FontWeight.Bold)
                            Text(text = "Descripción: ${it.descripcion}")
                        }
                    }
                }
            } else {
                Text(text = "No hay sesiones para este día.")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onCerrar() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Cerrar")
        }
    }

    // Diálogo para agregar cita con botón Cancelar
    if (mostrarDialogoCita) {
        Dialog(onDismissRequest = { setMostrarDialogoCita(false) }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Descripción de la cita:", fontWeight = FontWeight.Bold)
                    TextField(
                        value = descripcionCita,
                        onValueChange = setDescripcionCita,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Escribe la descripción") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Hora de la cita:", fontWeight = FontWeight.Bold)
                    TextField(
                        value = horaCita,
                        onValueChange = setHoraCita,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Escribe la hora de la cita") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = {
                            setMostrarDialogoCita(false) // Cierra el diálogo de cita
                        }) {
                            Text("Cancelar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (descripcionCita.isNotBlank() && horaCita.isNotBlank()) {
                                    viewModel.agregarSesion(diaSeleccionado!!, descripcionCita, horaCita)
                                    setMostrarDialogoCita(false)
                                    setDescripcionCita("")
                                    setHoraCita("")
                                }
                            }
                        ) {
                            Text("Guardar Cita")
                        }
                    }
                }
            }
        }
    }
}
