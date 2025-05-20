package com.example.psyconnect.Model

import java.time.LocalDate
import java.time.LocalTime

data class SesionAgendada(
    val id: Int,
    val fecha: LocalDate,
    val hora: LocalTime,
    val descripcion: String

)
