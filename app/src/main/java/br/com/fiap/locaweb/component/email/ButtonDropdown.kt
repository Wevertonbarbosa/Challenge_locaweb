package br.com.fiap.locaweb.component.email

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.locaweb.ui.theme.LocaWebTheme


@Composable
fun ButtonDropdown(labelOptions: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(labelOptions[0]) }

    LocaWebTheme {

        Column {
            Box {
                TextButton(
                    onClick = {
                        expanded = true
                        Log.d("FIAP", "Primeiro TextButton: $selectedOption")
                    }
                    ) {
                    Row {
                        Text(text = selectedOption, color = Color.Black)
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Dropdown Icon",
                            tint = Color.Blue
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    labelOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                expanded = false
                                selectedOption = option
                                Log.d("FIAP", "Segundo TextButton: $selectedOption")
                            }
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ButtonDropdownPreview() {
    val options = listOf("Marcadores", "Tarefas", "Eventos")
    val options2 = listOf("Categorias", "Social", "Promoções","Atualizações")

    LocaWebTheme {
        Row {
            ButtonDropdown(options)
            ButtonDropdown(options2)
        }

    }
}