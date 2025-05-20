package com.example.psyconnect.Api

import com.example.psyconnect.Model.Paciente
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PacienteApi {
    @GET("/api/Paciente/listar")
    suspend fun listar(): List<Paciente>

    @GET("/api/Paciente/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Paciente

    @POST("/api/Paciente/guardar")
    suspend fun guardar(@Body paciente: Paciente): Paciente

    @PUT("/api/Paciente/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body paciente: Paciente): Paciente

    @DELETE("/api/Paciente/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}