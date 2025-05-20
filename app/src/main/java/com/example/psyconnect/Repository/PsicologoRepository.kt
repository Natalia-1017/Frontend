package com.example.psyconnect.Repository

import com.example.psyconnect.Model.Psicologo
import com.example.psyconnect.RetrofitClient

class PsicologoRepository {
    suspend fun obtenerPsicologos(): List<Psicologo> {
        return RetrofitClient.psicologoApi.listar()
    }

    suspend fun obtenerPsicologo(id: Long): Psicologo {
        return RetrofitClient.psicologoApi.buscarPorId(id)
    }

    suspend fun guardarPsicologo(psicologo: Psicologo): Psicologo {
        return RetrofitClient.psicologoApi.guardar(psicologo)
    }

    suspend fun eliminarPsicologo(id: Long) {
        RetrofitClient.psicologoApi.eliminar(id)
    }
    suspend fun actualizarPsicologo(id: Long, psicologo: Psicologo): Psicologo {
        return RetrofitClient.psicologoApi.actualizar(id, psicologo)
    }
}