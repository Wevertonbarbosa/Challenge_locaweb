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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.model.DatailEmailDto
import br.com.fiap.locaweb.model.EmailDto
import br.com.fiap.locaweb.service.RetrofitClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
    emailId: String?,
    onBackClick: () -> Unit = { navController.popBackStack() }
) {
    val emailId = navController.currentBackStackEntry?.arguments?.getString("id")
    var details by remember { mutableStateOf<DatailEmailDto?>(null) }

    LaunchedEffect(emailId) {
        if (emailId != null) {
            details = getEmailById(emailId.toInt())
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detalhes do Email", style = MaterialTheme.typography.titleMedium)
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
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
                text = details?.subject ?: "",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "De: ${details?.sender}>",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            details?.let { Text(text = it.date, style = MaterialTheme.typography.bodySmall) }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            details?.let { Text(text = it.body, style = MaterialTheme.typography.bodyMedium) }
        }
    }
}


suspend fun getEmailById(id: Int): DatailEmailDto {
    val url = "http://10.0.2.2:8080/email/$id"
    val headers = mapOf("Content-Type" to "application/json")

    try {
        println("Fazendo a requisição GET para $url")  // Log para verificar se a requisição é feita
        val responseBody = RetrofitClient.get(url, headers)
        val json = responseBody.string()

        println("Resposta recebida: $json")  // Log para a resposta
        val emailListType = object : TypeToken<List<EmailDto>>() {}.type
        return Gson().fromJson(json, DatailEmailDto::class.java)
    } catch (e: Exception) {
        println("Erro ao fazer a requisição: ${e.message}")
        return DatailEmailDto("", "", "", "")
    }
}