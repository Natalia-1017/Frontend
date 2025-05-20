package com.example.psyconnect.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psyconnect.Repository.SesionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.psyconnect.Model.Sesion


class SesionViewModel : ViewModel() {
    private val repository = SesionRepository()
    val sesiones = MutableLiveData<List<Sesion>?>(emptyList())

    fun listar() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.obtenerSesiones() }
            sesiones.postValue(result)
        }
    }

    fun buscarPorId(id: Long) {
        viewModelScope.launch {
            repository.obtenerSesion(id)
        }
    }

    fun guardar(sesion: Sesion) {
        viewModelScope.launch {
            repository.guardarSesion(sesion)
            listar()
        }
    }

    fun actualizar(id: Long, sesion: Sesion) {
        viewModelScope.launch {
            repository.actualizarSesion(id, sesion)
            listar()
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            repository.eliminarSesion(id)
            listar()
        }
    }
}