package com.example.psyconnect.Api

import com.example.psyconnect.Model.AlertaEmergencia
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AlertaEmergenciaApi {
    @GET("/api/AlertaEmergencia/listar")
    suspend fun listar(): List<AlertaEmergencia>

    @GET("/api/AlertaEmergencia/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): AlertaEmergencia

    @POST("/api/AlertaEmergencia/guardar")
    suspend fun guardar(@Body alerta: AlertaEmergencia): AlertaEmergencia

    @PUT("/api/AlertaEmergencia/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body alerta: AlertaEmergencia): AlertaEmergencia

    @DELETE("/api/AlertaEmergencia/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}