package com.example.psyconnect.Api

import com.example.psyconnect.Model.PreguntaFormulario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PreguntaFormularioApi {
    @GET("/api/Pregunta/listar")
    suspend fun listar(): List<PreguntaFormulario>

    @GET("/api/Pregunta/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): PreguntaFormulario

    @POST("/api/Pregunta/guardar")
    suspend fun guardar(@Body pregunta: PreguntaFormulario): PreguntaFormulario

    @PUT("/api/Pregunta/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body pregunta: PreguntaFormulario): PreguntaFormulario

    @DELETE("/api/Pregunta/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}