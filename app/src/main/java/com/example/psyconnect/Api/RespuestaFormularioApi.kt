package com.example.psyconnect.Api

import com.example.psyconnect.Model.RespuestaFormulario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RespuestaFormularioApi {
    @GET("/api/Respuesta/listar")
    suspend fun listar(): List<RespuestaFormulario>

    @GET("/api/Respuesta/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): RespuestaFormulario

    @POST("/api/Respuesta/guardar")
    suspend fun guardar(@Body respuesta: RespuestaFormulario): RespuestaFormulario

    @PUT("/api/Respuesta/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body respuesta: RespuestaFormulario): RespuestaFormulario

    @DELETE("/api/Respuesta/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}