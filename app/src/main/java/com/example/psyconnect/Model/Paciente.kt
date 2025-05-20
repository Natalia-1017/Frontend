package com.example.psyconnect.Model

data class Paciente(
    val idPaciente: Long = 0,
    val usuario: Usuario,
    val psicologo: Psicologo?,
    val respuesta: RespuestaFormulario,
    val sesion: Sesion,
    val alerta: AlertaEmergencia,
    val diario: Diario
)
