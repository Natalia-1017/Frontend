package com.example.psyconnect.Interfaces.Administrador

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.psyconnect.Models.Usuario

@Composable
fun GestionUsuariosScreen(navController: NavController) {
    // Estado de la lista de usuarios
    var usuarios by remember { mutableStateOf(mutableListOf(
        Usuario(1, "Juan Pérez", "juan@gmail.com", "Administrador"),
        Usuario(2, "Ana Torres", "ana@gmail.com", "Psicólogo"),
        Usuario(3, "Carlos Ruiz", "carlos@gmail.com", "Paciente")
    )) }

    // Estados para los diálogos
    var showDialog by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf("") } // "add" o "edit"
    var usuarioActual by remember { mutableStateOf(Usuario(0, "", "", "Usuario")) }
    var usuarioAEliminar by remember { mutableStateOf<Usuario?>(null) }

    // Colores personalizados
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
            // Título
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
                    UsuarioCard(
                        usuario = usuario,
                        onEdit = {
                            usuarioActual = usuario.copiar()
                            dialogType = "edit"
                            showDialog = true
                        },
                        onDelete = {
                            usuarioAEliminar = usuario
                        },
                        cardColor = cardColor,
                        textColor = textColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para agregar nuevo usuario
            Button(
                onClick = {
                    usuarioActual = Usuario(0, "", "", "Usuario")
                    dialogType = "add"
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

        // Diálogo para agregar/editar usuario
        if (showDialog) {
            UsuarioDialog(
                usuario = usuarioActual,
                isEdit = dialogType == "edit",
                onConfirm = { usuarioActualizado ->
                    if (dialogType == "add") {
                        // Agregar nuevo usuario
                        val nuevoId = if (usuarios.isEmpty()) 1 else usuarios.maxOf { it.id } + 1
                        usuarios.add(usuarioActualizado.copy(id = nuevoId))
                    } else {
                        // Editar usuario existente
                        val index = usuarios.indexOfFirst { it.id == usuarioActualizado.id }
                        if (index != -1) {
                            usuarios[index] = usuarioActualizado
                        }
                    }
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }

        // Diálogo de confirmación para eliminar
        usuarioAEliminar?.let { usuario ->
            AlertDialog(
                onDismissRequest = { usuarioAEliminar = null },
                title = { Text("Confirmar eliminación") },
                text = { Text("¿Estás seguro de eliminar a ${usuario.nombre}?") },
                confirmButton = {
                    Button(
                        onClick = {
                            usuarios.remove(usuario)
                            usuarioAEliminar = null
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                    ) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { usuarioAEliminar = null }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
fun UsuarioCard(
    usuario: Usuario,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    cardColor: Color,
    textColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(usuario.nombre, fontWeight = FontWeight.SemiBold, color = textColor)
                Text(usuario.correo, color = textColor)
                Text("Rol: ${usuario.rol}", color = textColor.copy(alpha = 0.8f))
            }

            Row {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Filled.EditNote,
                        contentDescription = "Editar",
                        tint = Color(0xFF1E88E5)
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = Color(0xFFD32F2F)
                    )
                }
            }
        }
    }
}

@Composable
fun UsuarioDialog(
    usuario: Usuario,
    isEdit: Boolean,
    onConfirm: (Usuario) -> Unit,
    onDismiss: () -> Unit
) {
    var nombre by remember { mutableStateOf(usuario.nombre) }
    var correo by remember { mutableStateOf(usuario.correo) }
    var rol by remember { mutableStateOf(usuario.rol) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isEdit) "Editar Usuario" else "Agregar Usuario") },
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
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = rol,
                    onValueChange = { rol = it },
                    label = { Text("Rol") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val usuarioActualizado = usuario.copy(
                        nombre = nombre,
                        correo = correo,
                        rol = rol
                    )
                    onConfirm(usuarioActualizado)
                },
                enabled = nombre.isNotBlank() && correo.isNotBlank() && rol.isNotBlank()
            ) {
                Text(if (isEdit) "Guardar" else "Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}