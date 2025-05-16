package com.example.psyconnect.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate

// Data class para la sesión
data class Sesion(
    val fecha: LocalDate,
    val hora: String,
    val descripcion: String
)

// ViewModel que contiene las sesiones y la lógica para agregar nuevas sesiones
class CalendarioViewModel : ViewModel() {

    // Lista mutable de sesiones
    private val _sesiones = mutableStateListOf(
        Sesion(LocalDate.now(), "10:00", "Sesión con Psic. Laura"),
        Sesion(LocalDate.now().plusDays(2), "14:00", "Terapia Grupal"),
        Sesion(LocalDate.now().plusDays(5), "09:00", "Seguimiento")
    )

    // Lista pública de sesiones que se muestra en la UI
    val sesiones: List<Sesion> = _sesiones

    // Función para agregar una nueva sesión
    fun agregarSesion(fecha: LocalDate, descripcion: String, hora: String) {
        val nuevaSesion = Sesion(fecha, hora, descripcion)
        _sesiones.add(nuevaSesion)
    }
}
