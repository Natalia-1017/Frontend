package com.example.psyconnect.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.psyconnect.Interfaces.WelcomeScreen
import com.example.psyconnect.Interfaces.Paciente.*
import com.example.psyconnect.Interfaces.Psicologo.LoginPsicologoScreen
import com.example.psyconnect.Interfaces.Administrador.LoginAdminScreen
import com.example.psyconnect.Interfaces.Administrador.GestionUsuariosScreen
import com.example.psyconnect.Interfaces.Psicologo.DashboardPsicologoScreen
import com.example.psyconnect.ViewModel.PsicologoFormulariosViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.psyconnect.Interfaces.Psicologo.AlertaEmergencia
import com.example.psyconnect.Interfaces.Psicologo.AsignarFormularioScreen
import com.example.psyconnect.Interfaces.Psicologo.CalendarioPsicologoScreen
import com.example.psyconnect.Interfaces.Psicologo.EmergenciaAlertasScreen
import com.example.psyconnect.Model.Psicologo
import com.example.psyconnect.Model.Paciente

@Composable
fun AppNavigation(startDestination: String = "welcome") {
    val navController: NavHostController = rememberNavController()

    // Instancia del ViewModel compartido
    val psicologoFormulariosViewModel: PsicologoFormulariosViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {

        // Pantalla de bienvenida
        composable("welcome") { WelcomeScreen(navController) }

        // Logins por rol
        composable("login_paciente") { LoginPacienteScreen(navController) }
        composable("login_psicologo") { LoginPsicologoScreen(navController) }
        composable("login_admin") { LoginAdminScreen(navController) }

        // Dashboard por rol
        composable("dashboard_paciente") { DashboardPacienteScreen(navController) }
        composable("dashboard_psicologo") { DashboardPsicologoScreen(navController) }
        composable("dashboard_admin") { GestionUsuariosScreen(navController) }

        // Rutas del paciente

        composable("calendario") {
            CalendarioScreen(navController)
        }
        composable("emergencia") {
            EmergenciaScreen(navController)
        }
        composable("recursos") {
            PlaceholderScreen("Recursos de Bienestar")
        }
        composable("diario") {
            DiarioScreen(navController)
        }
        composable("historial") {
            HistorialSesionesScreen(navController)
        }

        composable("formulario") {
            FormularioScreen(navController, psicologoFormulariosViewModel)
        }

        composable("lista_formularios_paciente") {
            val viewModel: PsicologoFormulariosViewModel = viewModel()
            ListaFormulariosPacienteScreen(navController = navController, viewModel = viewModel)
        }
        //Rutas del psicologo
        composable("calendario_psicologo") {
            CalendarioPsicologoScreen(navController)
        }
        composable("asignar_formulario") {
            val viewModel: PsicologoFormulariosViewModel = viewModel()
            val pacientes = listOf("Paciente A", "Paciente B", "Paciente C")

            AsignarFormularioScreen(
                navController = navController,
                viewModel = viewModel,
                pacientes = pacientes
            )
        }

        composable("emergencia_alertas") {
            EmergenciaAlertasScreen(
                alertas = listOf( // Puedes conectar esto con ViewModel o Firestore
                    AlertaEmergencia("Juan Pérez", "2025-05-19 14:30", "Se siente en crisis."),
                    AlertaEmergencia("Ana López", "2025-05-19 15:12", "Ha indicado pensamientos negativos.")
                )
            )
        }
    }


}



    @Composable
    fun PlaceholderScreen(title: String) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title, fontSize = 24.sp)
        }
    }
