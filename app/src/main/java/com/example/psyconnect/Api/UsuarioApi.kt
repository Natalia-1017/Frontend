package com.example.psyconnect.Api


import com.example.psyconnect.Model.Usuario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuarioApi {
    @GET("/api/Usuario/listar")
    suspend fun listar(): List<Usuario>

    @GET("/api/Usuario/buscar/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Usuario

    @POST("/api/Usuario/guardar")
    suspend fun guardar(@Body usuario: Usuario): Usuario

    @PUT("/api/Usuario/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Body usuario: Usuario): Usuario

    @DELETE("/api/Usuario/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}