package com.example.psyconnect.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psyconnect.Model.Formulario
import com.example.psyconnect.Repository.FormularioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioViewModel : ViewModel() {
    private val repository = FormularioRepository()
    val formularios = MutableLiveData<List<Formulario>?>(emptyList())

    fun listar() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.obtenerFormularios() }
            formularios.postValue(result)
        }
    }

    fun buscarPorId(id: Long) {
        viewModelScope.launch {
            repository.obtenerFormulario(id)
        }
    }

    fun guardar(formulario: Formulario) {
        viewModelScope.launch {
            repository.guardarFormulario(formulario)
            listar()
        }
    }

    fun actualizar(id: Long, formulario: Formulario) {
        viewModelScope.launch {
            repository.actualizarFormulario(id, formulario)
            listar()
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            repository.eliminarFormulario(id)
            listar()
        }
    }
}