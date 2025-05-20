package com.example.psyconnect.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psyconnect.Model.PreguntaFormulario
import com.example.psyconnect.Repository.PreguntaFormularioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PreguntaFormularioViewModel : ViewModel() {
    private val repository = PreguntaFormularioRepository()
    val preguntas = MutableLiveData<List<PreguntaFormulario>?>(emptyList())

    fun listar() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.obtenerPreguntas() }
            preguntas.postValue(result)
        }
    }

    fun buscarPorId(id: Long) {
        viewModelScope.launch {
            repository.obtenerPregunta(id)
        }
    }

    fun guardar(pregunta: PreguntaFormulario) {
        viewModelScope.launch {
            repository.guardarPregunta(pregunta)
            listar()
        }
    }

    fun actualizar(id: Long, pregunta: PreguntaFormulario) {
        viewModelScope.launch {
            repository.actualizarPregunta(id, pregunta)
            listar()
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            repository.eliminarPregunta(id)
            listar()
        }
    }
}