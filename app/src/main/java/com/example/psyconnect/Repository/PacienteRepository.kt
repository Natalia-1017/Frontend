package com.example.psyconnect.Repository

import com.example.psyconnect.Model.Paciente
import com.example.psyconnect.RetrofitClient

class PacienteRepository {
    suspend fun obtenerPacientes(): List<Paciente> {
        return RetrofitClient.pacienteApi.listar()
    }

    suspend fun obtenerPaciente(id: Long): Paciente {
        return RetrofitClient.pacienteApi.buscarPorId(id)
    }

    suspend fun guardarPaciente(paciente: Paciente): Paciente {
        return RetrofitClient.pacienteApi.guardar(paciente)
    }

    suspend fun eliminarPaciente(id: Long) {
        RetrofitClient.pacienteApi.eliminar(id)
    }
    suspend fun actualizarPaciente(id: Long, paciente: Paciente): Paciente{
        return RetrofitClient.pacienteApi.actualizar(id, paciente)
    }
}