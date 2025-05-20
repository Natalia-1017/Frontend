package com.example.psyconnect.Api

import com.example.psyconnect.Model.Formulario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FormularioApi {
    @GET("/api/Formulario/listar")
    suspend fun listar(): List<Formulario>

    @GET("/api/Formulario/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Formulario

    @POST("/api/Formulario/guardar")
    suspend fun guardar(@Body formulario: Formulario): Formulario

    @POST("/api/Formulario/guardar-multiples")
    suspend fun guardarMultiples(@Body formularios: List<Formulario>): List<Formulario>

    @PUT("/api/Formulario/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body formulario: Formulario): Formulario

    @DELETE("/api/Formulario/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}