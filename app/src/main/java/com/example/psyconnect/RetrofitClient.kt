package com.example.psyconnect


import com.example.psyconnect.Api.AlertaEmergenciaApi
import com.example.psyconnect.Api.DiarioApi
import com.example.psyconnect.Api.FormularioApi
import com.example.psyconnect.Api.PacienteApi
import com.example.psyconnect.Api.PreguntaFormularioApi
import com.example.psyconnect.Api.PsicologoApi
import com.example.psyconnect.Api.RespuestaFormularioApi
import com.example.psyconnect.Api.SesionApi
import com.example.psyconnect.Api.UsuarioApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "jdbc:postgresql://aws-0-us-east-2.pooler.supabase.com:5432/postgres" // Cambia por tu IP si es físico o remoto
    private const val API_KEY = "Vanameal2035\$"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val diarioApi: DiarioApi by lazy {
        retrofit.create(DiarioApi::class.java)
    }

    val psicologoApi: PsicologoApi by lazy {
        retrofit.create(PsicologoApi::class.java)
    }

    val pacienteApi: PacienteApi by lazy {
        retrofit.create(PacienteApi::class.java)
    }

    val usuarioApi: UsuarioApi by lazy {
        retrofit.create(UsuarioApi::class.java)
    }

    val sesionApi: SesionApi by lazy {
        retrofit.create(SesionApi::class.java)
    }

    val alertaEmergenciaApi: AlertaEmergenciaApi by lazy {
        retrofit.create(AlertaEmergenciaApi::class.java)
    }

    val formularioApi: FormularioApi by lazy {
        retrofit.create(FormularioApi::class.java)
    }

    val preguntaFormularioApi: PreguntaFormularioApi by lazy {
        retrofit.create(PreguntaFormularioApi::class.java)
    }

    val respuestaFormularioApi: RespuestaFormularioApi by lazy {
        retrofit.create(RespuestaFormularioApi::class.java)
    }
}