package br.com.fiap.locaweb.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.component.login.ButtonApp
import br.com.fiap.locaweb.component.login.InputsField
import br.com.fiap.locaweb.ui.theme.LocaWebTheme


@Composable
fun LoginScreen(navController: NavController) {
    var iconEmail = Icons.Default.Email
    var iconSenha = Icons.Default.Info

    LocaWebTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(color = Color.White), verticalArrangement = Arrangement.Top,
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
                    .fillMaxWidth()
                    .background(Color.White),
                "Email:",
                "Digite seu email",
                iconEmail
            )
            InputsField(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                "Senha:",
                "Digite sua senha",
                iconSenha
            )
            ButtonApp(navController)
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun LogoImagemPreview() {
//    LocaWebTheme {
//        LoginScreen()
//    }
//}
