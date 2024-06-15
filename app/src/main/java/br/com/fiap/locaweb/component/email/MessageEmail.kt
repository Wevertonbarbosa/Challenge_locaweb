package br.com.fiap.locaweb.component.email

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MessageEmail(
    email: Email,
    isSelected: Boolean,
    onClick: () -> Unit,
    onImportantClick: () -> Unit,
) {
    val marcadorIcone = getIcon(email)

    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(if (isSelected) Color.LightGray else Color.White)
            .clickable { onClick() }
    ) {
        Icon(imageVector = Icons.Default.Person, contentDescription = "Icone do usuário", Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(text = email.horario, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = email.conteudo, style = MaterialTheme.typography.bodyMedium)
        }
            Icon(
                imageVector = marcadorIcone,
                contentDescription = "Marcador Icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
                    .align(Alignment.CenterVertically)
            )
        IconButton(onClick = onImportantClick) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Important Icon",
                tint = if (email.importante) Color.Blue else Color.Gray,
                modifier = Modifier.size(29.dp)
            )
        }
    }
}
@Composable
private fun getIcon(email: Email): ImageVector {
    val marcadorIcone = when {
        email.marcadores.contains("Trabalho") -> Icons.Default.Build
        email.marcadores.contains("Pessoal") -> Icons.Default.Person
        email.marcadores.contains("Urgente") -> Icons.Default.Warning
        email.marcadores.contains("Spam") -> Icons.Default.Clear
        else -> Icons.Default.AddCircle
    }
    return marcadorIcone
}