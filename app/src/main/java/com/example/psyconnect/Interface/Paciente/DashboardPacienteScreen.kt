package com.example.psyconnect.Interface.Paciente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.psyconnect.R
import kotlinx.coroutines.delay
import kotlin.random.Random

// Colores personalizados
val AzulSuaveClaro = Color(0xFFE1F5FE)
val AzulProfundoClaro = Color(0xFF81D4FA)
val VerdeCalmaClaro = Color(0xFFE8F5E9)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPacienteScreen(navController: NavController) {
    val images = listOf(
        R.drawable.imagen1,
        R.drawable.imagen2,
        R.drawable.imagen3
    )

    var currentImageIndex by remember { mutableStateOf(Random.nextInt(images.size)) }

    // Cambiar imagen cada 5 segundos
    LaunchedEffect(key1 = images) {
        while (true) {
            delay(5000L)
            currentImageIndex = Random.nextInt(images.size)
        }
    }

    val scrollState = rememberScrollState()
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Bienvenido Paciente",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate("calendario") },
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
        Box(modifier = Modifier
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
                // Imagen carrusel
                Image(
                    painter = painterResource(id = images[currentImageIndex]),
                    contentDescription = "Banner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                // Tarjeta formulario
                FormularioCard(
                    imageUrl = "https://i.pinimg.com/736x/05/4f/b4/054fb4c0ca125008cc2abfe3cefa25ab.jpg",
                    titulo = "Formulario de seguimiento",
                    estadoFormulario = "Formulario rellenado",
                    fechaEnvio = "15 de mayo, 2025",
                    onClick = { navController.navigate("formulario") }
                )
            }

            // FABs desplegables
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp, bottom = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (expanded) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(bottom = 72.dp)
                    ) {
                        FloatingActionButtonWithLabel(
                            label = "Historial",
                            icon = Icons.Filled.History,
                            onClick = {
                                navController.navigate("historial")
                                expanded = false
                            }
                        )
                        FloatingActionButtonWithLabel(
                            label = "Emergencia",
                            icon = Icons.Filled.Emergency,
                            onClick = {
                                navController.navigate("emergencia")
                                expanded = false
                            }
                        )
                        FloatingActionButtonWithLabel(
                            label = "Diario",
                            icon = Icons.Filled.Book,
                            onClick = {
                                navController.navigate("diario")
                                expanded = false
                            }
                        )
                    }
                }

                FloatingActionButton(
                    onClick = { expanded = !expanded },
                    containerColor = AzulProfundoClaro
                ) {
                    Icon(Icons.Filled.Help, contentDescription = "Recursos de Bienestar", tint = Color.White)
                }
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
                contentDescription = "Formulario Imagen",
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

@Composable
fun FloatingActionButtonWithLabel(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    var isTouched by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isTouched = true
                        awaitRelease()
                        isTouched = false
                    }
                )
            }
    ) {
        if (isTouched) {
            Text(
                text = label,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .background(AzulProfundoClaro, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        FloatingActionButton(
            onClick = onClick,
            containerColor = AzulProfundoClaro
        ) {
            Icon(icon, contentDescription = label, tint = Color.White)
        }
    }
}
