package br.com.fiap.locaweb.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locaweb.service.EmailManager
import br.com.fiap.locaweb.service.RetrofitClient
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


@Composable
fun PreferenceScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
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
                coroutineScope.launch {
                    // Chama a função sendPreferences dentro da coroutine
                    sendPreferences(
                        isDarkTheme = isDarkTheme,
                        fontSize = fontSize
                    )

                    // Salva as preferências localmente
                    saveThemePreference(context, isDarkTheme)
                    saveFontSizePreference(context, fontSize)

                    // Navega para a tela de email
                    navController.navigate("email") {
                        popUpTo("settings") { inclusive = true }
                    }
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


suspend fun sendPreferences(isDarkTheme: Boolean, fontSize: TextUnit) {
    val url = "http://10.0.2.2:8080/preference"
    val headers = mapOf("Content-Type" to "application/json")
    val theme = if (isDarkTheme) "dark" else "light"
    val fontSizeValue = fontSize.value.toInt()

    val payload = """
        {
            "email": "${EmailManager.getEmail()}",
            "theme": "$theme",
            "font_size": $fontSizeValue
        }
    """.trimIndent()

    val body: RequestBody = payload.toRequestBody("application/json".toMediaTypeOrNull())

    RetrofitClient.post(url, headers, body)
}