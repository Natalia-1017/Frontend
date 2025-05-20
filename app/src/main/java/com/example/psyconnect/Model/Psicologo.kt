package com.example.psyconnect.Model

data class Psicologo(
    val idPsicologo: Long = 0,
    val usuario: Usuario,
    val paciente: Paciente,
    val formulario: Formulario,
    val sesion: Sesion,
    val alerta: AlertaEmergencia
)
