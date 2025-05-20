package com.example.psyconnect.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psyconnect.Model.Diario
import com.example.psyconnect.Repository.DiarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiarioViewModel : ViewModel() {
    private val repository = DiarioRepository()
    val diarios = MutableLiveData<List<Diario>?>(emptyList())

    fun listar() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.obtenerDiarios() }
            diarios.postValue(result)
        }
    }

    fun buscarPorId(id: Long) {
        viewModelScope.launch {
            repository.obtenerDiario(id)
        }
    }

    fun guardar(diario: Diario) {
        viewModelScope.launch {
            repository.guardarDiario(diario)
            listar()
        }
    }

    fun actualizar(id: Long, diario: Diario) {
        viewModelScope.launch {
            repository.actualizarDiario(id, diario)
            listar()
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            repository.eliminarDiario(id)
            listar()
        }
    }
        }
