package com.example.psyconnect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.psyconnect.Interface.WelcomeScreen
import com.example.psyconnect.Interface.Paciente.*
import com.example.psyconnect.Interface.Psicologo.LoginPsicologoScreen
import com.example.psyconnect.Interface.Administrador.LoginAdminScreen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.psyconnect.Interface.Administrador.GestionUsuariosScreen

@Composable
fun AppNavigation(startDestination: String = "welcome") {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        // Pantalla de bienvenida
        composable("welcome") { WelcomeScreen(navController) }

        // Logins por rol
        composable("login_paciente") { LoginPacienteScreen(navController) }
        composable("login_psicologo") { LoginPsicologoScreen(navController) }
        composable("login_admin") { LoginAdminScreen(navController) }

        // Dashboard por rol
        composable("dashboard_paciente") { DashboardPacienteScreen(navController) }
        composable("dashboard_psicologo") {
            DashboardPacienteScreen(navController)
        }
        composable("dashboard_admin") {
            GestionUsuariosScreen(navController)

        }

        // Otras rutas del paciente
        composable("formulario") {
            FormularioScreen(navController) { nuevoFormulario ->
                // Guardar el formulario en el backStack anterior para recuperarlo
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("nuevoFormulario", nuevoFormulario)
                navController.popBackStack()
            }
        }
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
