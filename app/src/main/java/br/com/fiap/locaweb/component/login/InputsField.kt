package br.com.fiap.locaweb.component.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locaweb.ui.theme.LocaWebTheme

@Composable
fun InputsField(
    modifier: Modifier = Modifier,
    label: String,
    placeHolder: String,
    icon: ImageVector
) {

    var texto by remember { mutableStateOf("") }
    LocaWebTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = texto,
                onValueChange = { it ->
                    texto = it
                },
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = {
                    Text(label)
                },
                placeholder = {
                    Text(
                        text = placeHolder,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                }


            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InputsFieldPreview() {
    var procurar = Icons.Default.Search

    LocaWebTheme {
        Column {
            InputsField(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                "Email:",
                "Digite seu email",
                procurar
            )
            InputsField(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                "Senha:",
                "Digite sua senha",
                procurar
            )
        }
    }
}
