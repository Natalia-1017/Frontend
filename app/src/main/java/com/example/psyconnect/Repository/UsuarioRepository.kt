package com.example.psyconnect.Repository

import com.example.psyconnect.Model.Usuario
import com.example.psyconnect.RetrofitClient

class UsuarioRepository {

    suspend fun obtenerUsuarios(): List<Usuario> {
        return RetrofitClient.usuarioApi.listar()
    }

    suspend fun obtenerUsuario(id: Long): Usuario {
        return RetrofitClient.usuarioApi.buscarPorId(id)
    }

    suspend fun guardarUsuario(usuario: Usuario): Usuario {
        return RetrofitClient.usuarioApi.guardar(usuario)
    }

    suspend fun eliminarUsuario(id: Long) {
        RetrofitClient.usuarioApi.eliminar(id)
    }
    suspend fun actualizarUsuario(id: Long, usuario: Usuario): Usuario {
        return RetrofitClient.usuarioApi.actualizar(id, usuario)
    }
}