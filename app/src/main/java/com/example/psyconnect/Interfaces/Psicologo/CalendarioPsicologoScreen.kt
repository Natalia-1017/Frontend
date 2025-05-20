package com.example.psyconnect.Interfaces.Psicologo

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

// Colores para psicólogo con tonos más fuertes para ventana flotante
val AzulSuavePsicologo = Color(0xFF90CAF9)        // Azul suave
val AzulProfundoDialogo = Color(0xFF1565C0)       // Azul más oscuro para diálogo/ventana
val VerdeCalmaPsicologo = Color(0xFF80CBC4)
val GrisSuavePsicologo = Color(0xFFECEFF1)

@Composable
fun CalendarioPsicologoScreen(
    navController: NavHostController,
    viewModel: CalendarioViewModel = viewModel()
) {
    var diaSeleccionado by remember { mutableStateOf<LocalDate?>(null) }
    var mostrarDialogo by remember { mutableStateOf(true) }

    if (mostrarDialogo) {
        Dialog(onDismissRequest = { mostrarDialogo = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = AzulSuavePsicologo
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Calendario de Citas",
                        style = MaterialTheme.typography.titleLarge,
                        color = AzulProfundoDialogo,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    CalendarioPsicologoContent(
                        viewModel = viewModel,
                        diaSeleccionado = diaSeleccionado,
                        setDiaSeleccionado = { diaSeleccionado = it }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { mostrarDialogo = false },
                        colors = ButtonDefaults.buttonColors(containerColor = AzulProfundoDialogo),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Cerrar", color = Color.White)
                    }
                }
            }
        }
    } else {
        // Si quieres volver al dashboard u otra pantalla cuando cierres el diálogo
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
    }
}

@Composable
fun CalendarioPsicologoContent(
    viewModel: CalendarioViewModel,
    diaSeleccionado: LocalDate?,
    setDiaSeleccionado: (LocalDate?) -> Unit
) {
    val sesiones = viewModel.sesiones
    var mesActual by remember { mutableStateOf(YearMonth.now()) }

    val diasMes = remember(mesActual) {
        (1..mesActual.lengthOfMonth()).map { dia -> mesActual.atDay(dia) }
    }
    val sesionesDelDia = sesiones.filter { it.fecha == diaSeleccionado }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {
            mesActual = mesActual.minusMonths(1)
            setDiaSeleccionado(null)
        }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Mes anterior", tint = AzulProfundoDialogo)
        }

        Text(
            text = mesActual.month.getDisplayName(java.time.format.TextStyle.FULL, Locale("es"))
                .replaceFirstChar { it.uppercase() } + " de ${mesActual.year}",
            style = MaterialTheme.typography.titleLarge,
            color = AzulProfundoDialogo
        )

        IconButton(onClick = {
            mesActual = mesActual.plusMonths(1)
            setDiaSeleccionado(null)
        }) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "Mes siguiente", tint = AzulProfundoDialogo)
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(modifier = Modifier.fillMaxWidth()) {
        listOf("DO", "LU", "MA", "MI", "JU", "VI", "SA").forEach {
            Text(
                text = it,
                color = AzulProfundoDialogo,
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
                                    },
                                color = when {
                                    dia == LocalDate.now() -> AzulSuavePsicologo
                                    tieneSesion -> AzulProfundoDialogo
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
            color = AzulProfundoDialogo
        )

        if (sesionesDelDia.isNotEmpty()) {
            sesionesDelDia.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = VerdeCalmaPsicologo)
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
}
