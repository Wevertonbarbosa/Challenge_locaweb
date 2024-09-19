package br.com.fiap.locaweb.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.service.Auth
import br.com.fiap.locaweb.service.EmailManager
import br.com.fiap.locaweb.service.TokenManager
import br.com.fiap.locaweb.ui.theme.LocaWebTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(navController: NavController) {
    var email = remember { mutableStateOf("") }
    var senha = remember { mutableStateOf("") }
    var iconEmail = Icons.Default.Email
    var iconSenha = Icons.Default.Info

    LocaWebTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_email),
                contentDescription = "Logo do App",
                Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            InputsField(
                Modifier
                    .fillMaxWidth(),
                label = "Email:",
                placeholder = "Digite seu email",
                icon = iconEmail,
                value = email.value,
                onValueChange = { email.value = it }
            )
            InputsField(
                Modifier
                    .fillMaxWidth(),
                label = "Senha:",
                placeholder = "Digite sua senha",
                icon = iconSenha,
                value = senha.value,
                onValueChange = { senha.value = it }
            )
            ButtonApp(navController, email.value, senha.value)
        }
    }
}


@Composable
fun ButtonApp(navController: NavController, email: String, senha: String) {
    EmailManager.saveEmail(email)
    Button(onClick = {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = Auth.RetrofitClient.authService.login(
                    Auth.LoginRequest(email, senha)
                )
                TokenManager.saveToken(response.token)
                withContext(Dispatchers.Main) {
                    navController.navigate("email")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println("Erro: ${e.message}")
                }
            }
        }
    }) {
        Text(text = "Entrar")
    }
}


@Composable
fun InputsField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    icon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            leadingIcon = { Icon(icon, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}