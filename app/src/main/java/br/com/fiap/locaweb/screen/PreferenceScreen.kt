package br.com.fiap.locaweb.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit

@Composable
fun PreferenceScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var fontSize by remember { mutableStateOf(loadFontSizePreference(context)) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = "Preferências",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Tema Claro")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { newValue ->
                        onThemeChanged(newValue)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Tema Escuro")
            }

            Divider()
            Spacer(modifier = Modifier.height(16.dp))


            Text(text = "Tamanho da Fonte", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { fontSize = 14.sp }) {
                    Text("14sp")
                }
                Button(onClick = { fontSize = 16.sp }) {
                    Text("16sp")
                }
                Button(onClick = { fontSize = 18.sp }) {
                    Text("18sp")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }


        Button(
            onClick = {

                saveThemePreference(context, isDarkTheme)
                saveFontSizePreference(context, fontSize)
                navController.navigate("email") {
                    popUpTo("settings") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Salvar Preferências")
        }
    }
}

private fun loadFontSizePreference(context: Context): TextUnit {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val fontSize = prefs.getFloat("font_size", 16f) // Default to 16sp
    return fontSize.sp
}

private fun saveThemePreference(context: Context, isDark: Boolean) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit().putBoolean("theme", isDark).apply()
}

private fun saveFontSizePreference(context: Context, fontSize: TextUnit) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit().putFloat("font_size", fontSize.value).apply()
}
