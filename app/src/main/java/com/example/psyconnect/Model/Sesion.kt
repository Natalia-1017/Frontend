package com.example.psyconnect.Model

data class Sesion(
    val idSesion: Long = 0,
    val fecha: String, // LocalDateTime en string ISO
    val notas: String,
    val estado: EstadoSesion,
    val paciente: Paciente,
    val psicologo: Psicologo
)
enum class EstadoSesion {
    PENDIENTE, COMPLETADA, CANCELADA
}