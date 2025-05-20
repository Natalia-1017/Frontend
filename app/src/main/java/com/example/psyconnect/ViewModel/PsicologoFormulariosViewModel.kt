package com.example.psyconnect.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psyconnect.Model.Formulario
import com.example.psyconnect.Model.FormularioData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PsicologoFormulariosViewModel(
    private val api: PsicologoApi // Inyecta tu Retrofit service aquí
) : ViewModel() {

    private val _formularios = MutableStateFlow<List<FormularioData>>(emptyList())
    val formularios: StateFlow<List<FormularioData>> = _formularios

    fun cargarFormulariosDesdeBackend(formulariosBackend: List<Formulario>) {
        val formulariosUI = formulariosBackend.map { mapFormularioToFormularioData(it) }
        _formularios.value = formulariosUI
    }

    private fun mapFormularioToFormularioData(formulario: Formulario): FormularioData {
        val preguntasYRespuestas = formulario.preguntas.map { pregunta ->
            val respuesta = formulario.respuestas.find { it.pregunta.idPregunta == pregunta.idPregunta }?.respuesta ?: "Sin respuesta"
            pregunta.texto to respuesta
        }
        return FormularioData(
            imageUrl = "https://url-imagen-default.jpg",
            titulo = formulario.nombre,
            estadoFormulario = "Estado desconocido",
            fechaEnvio = "Fecha desconocida",
            preguntas = preguntasYRespuestas
        )
    }

    fun agregarFormulario(formularioData: FormularioData) {
        val actuales = _formularios.value.toMutableList()
        actuales.add(formularioData)
        _formularios.value = actuales
    }

    fun asignarFormulario(formularioAsignado: FormularioAsignado) {
        viewModelScope.launch {
            try {
                api.asignarFormulario(formularioAsignado)
                // Aquí puedes hacer algo tras éxito (e.g. mostrar mensaje, refrescar lista)
            } catch (e: Exception) {
                // Manejo de error (mostrar mensaje, log, etc)
                println("Error asignando formulario: ${e.message}")
            }
        }
    }
}