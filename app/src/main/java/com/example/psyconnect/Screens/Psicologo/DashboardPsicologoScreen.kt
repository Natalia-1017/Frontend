package com.example.psyconnect.Interfaces.Psicologo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlin.random.Random

val AzulSuaveClaro = Color(0xFFE1F5FE)
val AzulProfundoClaro = Color(0xFF81D4FA)
val VerdeCalmaClaro = Color(0xFFE8F5E9)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPsicologoScreen(navController: NavController) {
    val imageUrls = listOf(
        "https://i.pinimg.com/736x/11/14/8c/11148c3a4d12fc8b7c7898170a5766e1.jpg",
        "https://i.pinimg.com/736x/f9/e8/5f/f9e85f38718bed9b3571a575ebee5a13.jpg",
        "https://i.pinimg.com/736x/9a/c5/a7/9ac5a7fbbdcf7fec6667c4c33284837c.jpg"
    )

    var currentImageIndex by remember { mutableStateOf(Random.nextInt(imageUrls.size)) }

    LaunchedEffect(key1 = imageUrls) {
        while (true) {
            delay(5000L)
            currentImageIndex = Random.nextInt(imageUrls.size)
        }
    }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Panel del Psicólogo",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate("calendario_psicologo") },
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(AzulProfundoClaro)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = "Abrir calendario",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AzulProfundoClaro
                )
            )
        },
        containerColor = AzulSuaveClaro
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VerdeCalmaClaro)
        ) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = imageUrls[currentImageIndex],
                    contentDescription = "Banner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                FormularioCard(
                    imageUrl = "https://i.pinimg.com/736x/96/11/5e/96115eedef49aa8f4280bea793a10fe1.jpg",
                    titulo = "Asignar Formulario",
                    estadoFormulario = "Dirígete a asignar un nuevo formulario",
                    fechaEnvio = "Disponible ahora",
                    onClick = { navController.navigate("asignar_Formulario") }
                )

                FormularioCard(
                    imageUrl = "https://i.pinimg.com/736x/9b/22/42/9b22423b4dced87df8010388d0bdf34e.jpg",
                    titulo = "Ver Formularios",
                    estadoFormulario = "Respuestas de los pacientes",
                    fechaEnvio = "Actualizado diariamente",
                    onClick = { navController.navigate("lista_formularios_paciente") }
                )

                FormularioCard(
                    imageUrl = "https://i.pinimg.com/736x/5d/ce/c1/5dcec1c6301aeda57ddc1d3bd3fce7ef.jpg",
                    titulo = "Alertas de Emergencia",
                    estadoFormulario = "Pacientes en estado crítico",
                    fechaEnvio = "Revisar inmediatamente",
                    onClick = { navController.navigate("emergencia_alertas") }
                )

                FormularioCard(
                    imageUrl = "https://i.pinimg.com/736x/cb/36/2a/cb362af190633f1755703e9bf412f7df.jpg",
                    titulo = "Diario del Paciente",
                    estadoFormulario = "Entradas recientes disponibles",
                    fechaEnvio = "Últimos registros",
                    onClick = { navController.navigate("diarioPacientes") }
                )
            
            }
        }
    }
}

@Composable
fun FormularioCard(
    imageUrl: String,
    titulo: String,
    estadoFormulario: String,
    fechaEnvio: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = AzulProfundoClaro),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Imagen",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = estadoFormulario,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = fechaEnvio,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        }
    }
}
