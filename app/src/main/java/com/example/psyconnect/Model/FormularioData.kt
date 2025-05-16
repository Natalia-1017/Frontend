package com.example.psyconnect.Model


data class FormularioData(
    val imageUrl: String,
    val titulo: String,
    val estadoFormulario: String,
    val fechaEnvio: String,
    val preguntas: List<Pair<String, String>>
)
