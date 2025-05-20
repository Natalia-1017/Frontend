package com.example.psyconnect.Api

import com.example.psyconnect.Model.Sesion
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SesionApi {
    @GET("/api/Sesion/listar")
    suspend fun listar(): List<Sesion>

    @GET("/api/Sesion/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Sesion

    @POST("/api/Sesion/guardar")
    suspend fun guardar(@Body sesion: Sesion): Sesion

    @PUT("/api/Sesion/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body sesion: Sesion): Sesion

    @DELETE("/api/Sesion/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}