package br.com.fiap.locaweb.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.component.email.ButtonDropdown
import br.com.fiap.locaweb.component.email.MessageEmail
import br.com.fiap.locaweb.component.login.InputsField
import br.com.fiap.locaweb.ui.theme.LocaWebTheme

@Composable
fun EmailScreen() {
    val marcadores = listOf("Marcadores", "Tarefas", "Eventos")
    val categorias = listOf("Categorias", "Social", "Promoções", "Atualizações")
    val todos = listOf("Todos", "Não lidas", "Lidas")


    var icone = Icons.Default.Person
    var contentMsgEMail =
        "Body text for whatever you’d like to suggest. Add main takeaway points, quotes."


    var procurar = Icons.Default.Search
    LocaWebTheme {
        Column(Modifier.background(Color.White)) {
            Row {
                ButtonDropdown(marcadores)
                ButtonDropdown(categorias)
            }
            InputsField(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                "Pesquisar",
                "",
                procurar
            )
            Row {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = CircleShape,
                    contentPadding = PaddingValues(start = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "calendario",
                        Modifier.size(36.dp)
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    contentPadding = PaddingValues(all = 2.dp),
                    modifier = Modifier.width(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Icone Start",
                        tint = Color.Blue,
                        modifier = Modifier.size(29.dp)
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    contentPadding = PaddingValues(all = 2.dp),
                    modifier = Modifier.width(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Icone Important",
                        tint = Color.Blue,
                        modifier = Modifier.size(29.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                ButtonDropdown(todos)
            }

            for (i in 1..4) {
                MessageEmail(icone, "18:30", contentMsgEMail)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EmailScreenPreview() {
    LocaWebTheme {
        EmailScreen()
    }
}
