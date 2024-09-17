package br.com.fiap.locaweb.component.email

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    hintText: String = "Pesquisar",
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val borderColor = MaterialTheme.colorScheme.outline
    val textColor = MaterialTheme.colorScheme.onBackground
    val hintColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Ícone de Pesquisa",
            modifier = Modifier.size(20.dp)
                .align(Alignment.CenterVertically),
            tint = textColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = textColor),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = hintText,
                            color = hintColor,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
