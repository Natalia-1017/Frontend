package com.example.psyconnect.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psyconnect.Model.Usuario
import com.example.psyconnect.Repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioViewModel: ViewModel() {
    private val repository = UsuarioRepository()
    val usuarios = MutableLiveData<List<Usuario>?>(emptyList())

    fun listar() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.obtenerUsuarios() }
            usuarios.postValue(result)
        }
    }

    fun buscarPorId(id: Long) {
        viewModelScope.launch {
            repository.obtenerUsuario(id)
        }
    }

    fun guardar(usuario: Usuario) {
        viewModelScope.launch {
            repository.guardarUsuario(usuario)
            listar()
        }
    }

    fun actualizar(id: Long, usuario: Usuario) {
        viewModelScope.launch {
            repository.actualizarUsuario(id, usuario)
            listar()
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            repository.eliminarUsuario(id)
            listar()
        }
    }
}