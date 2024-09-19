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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.com.fiap.locaweb.model.EmailDto

@Composable
fun MessageEmail(
    email: EmailDto,
    fontSize: TextUnit,
    isSelected: Boolean,
    onClick: () -> Unit,
    onImportantClick: () -> Unit,
) {
    val marcadorIcone = getIcon(email)
    val backgroundColor =
        if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onBackground
    val importantIconColor = if (email.importante) MaterialTheme.colorScheme.primary else Color.Gray
    val regex = """(\d{2}:\d{2})""".toRegex()
    var data = email.date
    val matchResult = regex.find(data)
    val dataFormatada: String = matchResult?.value ?: "Formato inválido"


    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Ícone do usuário",
            Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = dataFormatada,
                style = MaterialTheme.typography.bodySmall,
                color = textColor,
                fontSize = fontSize
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = email.subject,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                fontSize = fontSize
            )
        }
        Icon(
            imageVector = marcadorIcone,
            contentDescription = "Ícone do marcador",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = onImportantClick) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Ícone de importância",
                tint = importantIconColor,
                modifier = Modifier.size(29.dp)
            )
        }
    }
}

@Composable
private fun getIcon(email: EmailDto): ImageVector {
    return when {
        email.category.contains("Trabalho") -> Icons.Default.Build
        email.category.contains("Pessoal") -> Icons.Default.Person
        email.category.contains("Urgente") -> Icons.Default.Warning
        email.category.contains("Spam") -> Icons.Default.Clear
        else -> Icons.Default.AddCircle
    }
}