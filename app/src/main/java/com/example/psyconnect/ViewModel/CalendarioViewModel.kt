package com.example.psyconnect.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate


data class Sesion(
    val fecha: LocalDate,
    val hora: String,
    val descripcion: String
)

class CalendarioViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _sesiones = mutableStateListOf(
        Sesion(LocalDate.now(), "10:00", "Sesión con Psic. Laura"),
        Sesion(LocalDate.now().plusDays(2), "14:00", "Terapia Grupal"),
        Sesion(LocalDate.now().plusDays(5), "09:00", "Seguimiento")
    )


    @RequiresApi(Build.VERSION_CODES.O)
    val sesiones: List<Sesion> = _sesiones


    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarSesion(fecha: LocalDate, descripcion: String, hora: String) {
        val nuevaSesion = Sesion(fecha, hora, descripcion)
        _sesiones.add(nuevaSesion)
    }
}