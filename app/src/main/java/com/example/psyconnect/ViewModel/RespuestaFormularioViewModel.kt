package com.example.psyconnect.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psyconnect.Model.RespuestaFormulario
import com.example.psyconnect.Repository.RespuestaFormularioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RespuestaFormularioViewModel : ViewModel() {
    private val repository = RespuestaFormularioRepository()
    val respuestas = MutableLiveData<List<RespuestaFormulario>?>(emptyList())

    fun listar() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.obtenerRespuestas() }
            respuestas.postValue(result)
        }
    }

    fun buscarPorId(id: Long) {
        viewModelScope.launch {
            repository.obtenerRespuesta(id)
        }
    }

    fun guardar(respuesta: RespuestaFormulario) {
        viewModelScope.launch {
            repository.guardarRespuesta(respuesta)
            listar()
        }
    }

    fun actualizar(id: Long, respuesta: RespuestaFormulario) {
        viewModelScope.launch {
            repository.actualizarRespuesta(id, respuesta)
            listar()
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            repository.eliminarRespuesta(id)
            listar()
        }
    }
}