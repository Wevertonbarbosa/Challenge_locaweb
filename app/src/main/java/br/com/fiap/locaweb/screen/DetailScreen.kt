package br.com.fiap.locaweb.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Email(
    val senderName: String,
    val senderEmail: String,
    val subject: String,
    val timestamp: String,
    val body: String
)

val mockEmail = Email(
    senderName = "John Doe",
    senderEmail = "john.doe@gmail.com",
    subject = "Reunião de Projeto",
    timestamp = "15:30 - 20 de Setembro de 2024",
    body = "Olá, \nGostaria de confirmar a nossa reunião sobre o projeto X para amanhã. Aguardo sua confirmação.\n\nAtenciosamente,\nJohn Doe"
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    email: Email = mockEmail,
    onBackClick: () -> Unit = { navController.popBackStack()}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detalhes do Email", style = MaterialTheme.typography.titleMedium)
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick()}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = email.subject,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "De: ${email.senderName} <${email.senderEmail}>",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = email.timestamp, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = email.body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun PreviewDetailScreenWrapper() {
    val navController = rememberNavController() // Cria um NavController para preview
    DetailScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    PreviewDetailScreenWrapper()
}