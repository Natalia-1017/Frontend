package com.example.psyconnect.Interface.Administrador

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Modelo de usuario
data class Usuario(val id: Int, var nombre: String, var correo: String)

@Composable
fun GestionUsuariosScreen(navController: NavController) {
    var usuarios by remember {
        mutableStateOf(
            listOf(
                Usuario(1, "Juan Pérez", "juan@gmail.com"),
                Usuario(2, "Ana Torres", "ana@gmail.com"),
                Usuario(3, "Carlos Ruiz", "carlos@gmail.com")
            )
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var userToEdit by remember { mutableStateOf<Usuario?>(null) }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var userToDelete by remember { mutableStateOf<Usuario?>(null) }

    // Colores consistentes con LoginAdminScreen
    val backgroundColor = Color(0xFFE3F2FD)
    val cardColor = Color(0xFFF1F8E9)
    val textColor = Color(0xFF37474F)
    val buttonColor = Color(0xFFB2DFDB)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Título consistente con LoginAdminScreen
            Text(
                text = "Gestión de Usuarios",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = textColor,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de usuarios
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(usuarios) { usuario ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(usuario.nombre, fontWeight = FontWeight.SemiBold, color = textColor)
                                Text(usuario.correo, color = textColor)
                            }

                            Row {
                                IconButton(onClick = {
                                    userToEdit = usuario
                                    showDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.EditNote,
                                        contentDescription = "Editar",
                                        tint = Color(0xFF1E88E5) // azul más suave
                                    )
                                }

                                IconButton(onClick = {
                                    userToDelete = usuario
                                    showDeleteDialog = true
                                }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Eliminar",
                                        tint = Color(0xFFD32F2F) // rojo más suave
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón agregar usuario
            Button(
                onClick = {
                    userToEdit = null
                    showDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = "Agregar")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Agregar Usuario", fontSize = 16.sp)
            }
        }
    }

    // Diálogo para editar o agregar usuario
    if (showDialog) {
        val isEditing = userToEdit != null
        var nombre by remember { mutableStateOf(userToEdit?.nombre ?: "") }
        var correo by remember { mutableStateOf(userToEdit?.correo ?: "") }

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(if (isEditing) "Editar Usuario" else "Nuevo Usuario") },
            confirmButton = {
                Button(onClick = {
                    if (isEditing) {
                        userToEdit?.let { usuario ->
                            usuario.nombre = nombre
                            usuario.correo = correo
                        }
                    } else {
                        val nuevo = Usuario(usuarios.size + 1, nombre, correo)
                        usuarios = usuarios + nuevo
                    }
                    showDialog = false
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
    }

    // Diálogo para confirmar eliminación
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro de eliminar a ${userToDelete?.nombre}?") },
            confirmButton = {
                Button(onClick = {
                    usuarios = usuarios.filter { it.id != userToDelete?.id }
                    showDeleteDialog = false
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
