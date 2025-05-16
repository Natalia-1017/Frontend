package com.example.psyconnect.Interface

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.psyconnect.R

// Colores personalizados
val AzulSuaveClaro = Color(0xFFE1F5FE)
val AzulProfundoClaro = Color(0xFF81D4FA)
val VerdeCalmaClaro = Color(0xFFE8F5E9)
val GrisMedio = Color(0xFFE0E0E0)

@Composable
fun WelcomeScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopSection(modifier = Modifier.weight(1f))
            BottomSection(navController, modifier = Modifier.weight(1f))
        }
    } else {
        Row(modifier = Modifier.fillMaxSize()) {
            TopSection(modifier = Modifier.weight(1f))
            BottomSection(navController, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TopSection(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(GrisMedio),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircleDecor(AzulProfundoClaro, 180.dp, Modifier.offset(x = (-60).dp, y = 40.dp))
            CircleDecor(VerdeCalmaClaro, 120.dp, Modifier.offset(x = 60.dp, y = (-40).dp))
            CircleDecor(AzulSuaveClaro, 100.dp, Modifier.offset(x = 80.dp, y = 300.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo PsyConnect",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}

@Composable
fun BottomSection(navController: NavController, modifier: Modifier) {
    var selectedRole by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Bienvenido a PsyConnect",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = AzulProfundoClaro
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Selecciona tu rol",
                fontSize = 22.sp,
                color = AzulProfundoClaro.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(40.dp))

            RoleCard("Paciente", selectedRole == "Paciente") {
                selectedRole = "Paciente"
                navController.navigate("login_paciente")
            }
            RoleCard("Psicólogo", selectedRole == "Psicólogo") {
                selectedRole = "Psicólogo"
                navController.navigate("login_psicologo")
            }
            RoleCard("Administrador", selectedRole == "Administrador") {
                selectedRole = "Administrador"
                navController.navigate("login_admin")
            }
        }
    }
}

@Composable
fun RoleCard(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) AzulProfundoClaro else Color.White
    val textColor = if (isSelected) Color.White else AzulProfundoClaro

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        color = backgroundColor,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, AzulProfundoClaro)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Composable
fun CircleDecor(color: Color, size: Dp, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}
