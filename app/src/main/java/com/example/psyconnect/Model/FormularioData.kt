package com.example.psyconnect.Model

data class PreguntaFormulario(
    val idPregunta: Long = 0,
    val texto: String,
    val formulario: Formulario? = null
)

data class RespuestaFormulario(
    val idRespuesta: Long = 0,
    val respuesta: String,
    val formulario: Formulario,
    val paciente: Paciente,
    val pregunta: PreguntaFormulario
)

data class Formulario(
    val idFormulario: Long = 0,
    val nombre: String,
    val psicologo: Psicologo,
    val preguntas: List<PreguntaFormulario> = emptyList(),
    val respuestas: List<RespuestaFormulario> = emptyList()
)


// Modelo simplificado para la UI con pares pregunta-respuesta
data class FormularioData(
    val imageUrl: String,
    val titulo: String,
    val estadoFormulario: String,
    val fechaEnvio: String,
    val preguntas: List<Pair<String, String>>
)