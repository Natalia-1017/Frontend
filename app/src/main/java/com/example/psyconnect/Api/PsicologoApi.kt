package com.example.psyconnect.Api

import com.example.psyconnect.Model.FormularioAsignado
import com.example.psyconnect.Model.Psicologo
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import com.example.psyconnect.Model.FormularioData

interface PsicologoApi {
    @GET("/api/Psicologo/listar")
    suspend fun listar(): List<Psicologo>
    @POST("/api/formulario/asignar")

    suspend fun asignarFormulario(@Body formularioAsignado: FormularioAsignado)

    @GET("/api/Psicologo/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Psicologo

    @POST("/api/Psicologo/guardar")
    suspend fun guardar(@Body psicologo: Psicologo): Psicologo

    @PUT("/api/Psicologo/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body psicologo: Psicologo): Psicologo

    @DELETE("/api/Psicologo/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}