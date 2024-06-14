package br.com.fiap.locaweb.component.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locaweb.ui.theme.LocaWebTheme

@Composable
fun ButtonApp(navController: NavController) {
    LocaWebTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(20.dp),
        ) {
            Button(
                onClick = {
                    navController.navigate("email")
                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Confirmar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Modifier.padding(90.dp)
            }
        }
    }

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun ButtonAppPreview() {
//    LocaWebTheme {
//        ButtonApp()
//    }
//}