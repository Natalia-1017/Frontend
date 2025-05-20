package com.example.psyconnect.Model

data class AlertaEmergencia(
    val idAlerta: Long = 0,
    val mensaje: String,
    val fecha: String, // LocalDateTime como string ISO
    val paciente: Paciente,
    val psicologo: Psicologo,
    val estado: EstadoAlerta
)

enum class EstadoAlerta {
    ENVIADA, ATENDIDA, CANCELADA
}