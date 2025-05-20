package com.example.psyconnect.Repository

import com.example.psyconnect.Model.Formulario
import com.example.psyconnect.RetrofitClient

class FormularioRepository {
    suspend fun obtenerFormularios(): List<Formulario> {
        return RetrofitClient.formularioApi.listar()
    }

    suspend fun obtenerFormulario(id: Long): Formulario {
        return RetrofitClient.formularioApi.buscarPorId(id)
    }

    suspend fun guardarFormulario(formulario: Formulario): Formulario {
        return RetrofitClient.formularioApi.guardar(formulario)
    }

    suspend fun eliminarFormulario(id: Long) {
        RetrofitClient.formularioApi.eliminar(id)
    }
    suspend fun actualizarFormulario(id: Long, formulario: Formulario): Formulario{
        return RetrofitClient.formularioApi.actualizar(id, formulario)
    }
}