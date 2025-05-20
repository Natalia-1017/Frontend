package com.example.psyconnect.Model


data class Usuario(
    val idUsuario: Long = 0,
    val nombre: String,
    val correo: String,
    val contraseña: String,
    val rol: Rol
)

enum class Rol {
    PACIENTE, PSICOLOGO, ADMINISTRADOR
}
