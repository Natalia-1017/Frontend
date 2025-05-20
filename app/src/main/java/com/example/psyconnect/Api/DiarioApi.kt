package com.example.psyconnect.Api

import com.example.psyconnect.Model.Diario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DiarioApi {
    @GET("/api/Diario/listar")
    suspend fun listar(): List<Diario>

    @GET("/api/Diario/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Diario

    @POST("/api/Diario/guardar")
    suspend fun guardar(@Body diario: Diario): Diario

    @PUT("/api/Diario/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body diario: Diario): Diario

    @DELETE("/api/Diario/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}