/*package com.example.psyconnect.Interfaces.Paciente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psyconnect.Model.FormularioData
import com.example.psyconnect.ViewModel.PsicologoFormulariosViewModel
import com.example.psyconnect.ViewModel.PsicologoFormulariosViewModelFactory // importa la factory
import com.example.psyconnect.RetrofitClient

@Composable
fun FormularioScreen(
    navController: NavController,
    psicologoFormulariosViewModel: PsicologoFormulariosViewModel
) {
    var respuesta1 by remember { mutableStateOf("") }
    var respuesta2 by remember { mutableStateOf("") }
    var respuesta3 by remember { mutableStateOf("") }
    var respuesta4 by remember { mutableStateOf("") }
    var respuesta5 by remember { mutableStateOf("") }

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
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = respuesta2,
                    onValueChange = { respuesta2 = it },
                    label = { Text("Tema abordado hoy en terapia") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = respuesta3,
                    onValueChange = { respuesta3 = it },
                    label = { Text("¿Qué aprendizaje o avance consideras que te llevas hoy?") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = respuesta4,
                    onValueChange = { respuesta4 = it },
                    label = { Text("¿Qué tarea o compromiso personal te llevas hoy?") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = respuesta5,
                    onValueChange = { respuesta5 = it },
                    label = { Text("Comentario o sugerencia de tu proceso") },
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
                                "Fecha" to respuesta1,
                                "Tema abordado hoy en terapia" to respuesta2,
                                "¿Qué aprendizaje o avance consideras que te llevas hoy?" to respuesta3,
                                "¿Qué tarea o compromiso personal te llevas hoy?" to respuesta4,
                                "Comentario o sugerencia de tu proceso" to respuesta5
                            )
                        )
                        psicologoFormulariosViewModel.agregarFormulario(formulario)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = VerdeCalmaClaro)
                ) {
                    Text("Enviar", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// --- NUEVO: Contenedor donde creas el ViewModel con la Factory y llamas FormularioScreen ---
@Composable
fun FormularioScreenContainer(navController: NavController) {
    val factory = PsicologoFormulariosViewModelFactory(
        formularioApi = RetrofitClient.formularioApi,
        preguntaApi = RetrofitClient.preguntaFormularioApi
    )
    val viewModel: PsicologoFormulariosViewModel = viewModel(factory = factory)

    FormularioScreen(navController = navController, psicologoFormulariosViewModel = viewModel)

}
*/