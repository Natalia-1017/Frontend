package com.example.psyconnect.Model

data class Diario(
    val id: Long = 0,
    val fecha: String,
    val entrada: String,
    val paciente: Paciente
)
