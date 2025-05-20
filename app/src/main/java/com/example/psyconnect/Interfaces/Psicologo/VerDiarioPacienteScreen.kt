package com.example.psyconnect.Interfaces.Psicologo

/*import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.psyconnect.Model.Diario
import com.example.psyconnect.ViewModel.DiarioViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerDiarioPacienteScreen(
    pacienteId: Long,
    diarioViewModel: DiarioViewModel = viewModel()
) {
    // Carga los diarios cuando la pantalla aparece
    LaunchedEffect(Unit) {
        diarioViewModel.listar()
    }

    // Observar la lista de diarios del LiveData
    val diarios by diarioViewModel.diarios.observeAsState(emptyList())

    Scaffold(
        topBar = {
            SmallTopAppBar(title = { Text("Diarios del Paciente") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (diarios.isEmpty()) {
                Text(
                    text = "No hay diarios para mostrar",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(diarios) { diario ->
                        DiarioCard(diario)
                    }
                }
            }
        }
    }
}

@Composable
fun DiarioCard(diario: Diario) {
    val formatoFecha = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = formatoFecha.format(diario.fecha),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = diario.titulo,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = diario.contenido,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}*/
