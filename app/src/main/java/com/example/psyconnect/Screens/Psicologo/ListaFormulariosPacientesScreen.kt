package com.example.psyconnect.Interfaces.Paciente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.psyconnect.ui.theme.AzulProfundoClaro
import com.example.psyconnect.ui.theme.AzulSuaveClaro

@Composable
fun ListaFormulariosPacientesScreen(
    navController: NavController
) {
    // Datos simulados estáticos para diseño
    val formularios = listOf(
        Formulario("Formulario de Ansiedad", listOf(
            "¿Con qué frecuencia te sientes ansioso?" to "Frecuentemente",
            "¿Has tenido ataques de pánico?" to "No"
        )),
        Formulario("Formulario de Sueño", listOf(
            "¿Cuántas horas duermes en promedio?" to "6 horas",
            "¿Tienes problemas para conciliar el sueño?" to "Sí"
        ))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulSuaveClaro)
            .padding(16.dp)
    ) {
        if (formularios.isEmpty()) {
            Text(
                "No han enviado ningún formulario aún.",
                modifier = Modifier.align(Alignment.Center),
                color = AzulProfundoClaro,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        } else {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                formularios.forEach { formulario ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = Color.White,
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                formulario.titulo,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = AzulProfundoClaro
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            formulario.preguntas.forEach { (pregunta, respuesta) ->
                                Text(
                                    pregunta,
                                    fontWeight = FontWeight.SemiBold,
                                    color = AzulProfundoClaro
                                )
                                Text(respuesta)
                                Spacer(modifier = Modifier.height(6.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

// Clase de datos para formulario estático
data class Formulario(
    val titulo: String,
    val preguntas: List<Pair<String, String>>
)
