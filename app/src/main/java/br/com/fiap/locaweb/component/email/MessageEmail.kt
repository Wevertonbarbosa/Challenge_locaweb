package br.com.fiap.locaweb.component.email

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.locaweb.ui.theme.LocaWebTheme

@Composable
fun MessageEmail(iconeEmail: ImageVector, time: String, contentEmail: String, category:String = "") {
    LocaWebTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .border(2.dp, Color.Gray)
                .background(Color.White),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Icon(
                    imageVector = iconeEmail,
                    contentDescription = "Icone Start",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = time,
                    modifier = Modifier.padding(start = 30.dp)
                )
                Row {

                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        contentPadding = PaddingValues(start = 24.dp),
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
                        contentPadding = PaddingValues(start = 15.dp),
                        modifier = Modifier.width(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Icone Important",
                            tint = Color.Blue,
                            modifier = Modifier.size(29.dp)
                        )
                    }
                }
            }

            Text(
                contentEmail,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MessageEmailPreview() {

    var icone = Icons.Default.Person
    var contentMsgEMail =
        "Body text for whatever you’d like to suggest. Add main takeaway points, quotes."
    LocaWebTheme {
        MessageEmail(icone, "17:50", contentMsgEMail)
    }
}