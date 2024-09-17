package br.com.fiap.locaweb.screen

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.component.email.ButtonDropdown
import br.com.fiap.locaweb.component.email.ButtonSendEmail
import br.com.fiap.locaweb.component.email.EmailsOnline
import br.com.fiap.locaweb.component.email.MessageEmail
import br.com.fiap.locaweb.component.email.SearchField
import br.com.fiap.locaweb.ui.theme.LocaWebTheme
import java.util.Calendar

val marcadores = listOf("Marcadores", "Trabalho", "Pessoal", "Urgente", "Spam")
val categorias = listOf("Categorias", "Social", "Promoções", "Atualizações")
val todos = listOf("Todos", "Não lidas", "Lidas")

fun showDatePickerDialog(context: Context, onDateSet: (year: Int, month: Int, day: Int) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
        onDateSet(selectedYear, selectedMonth, selectedDay)
    }, year, month, day).show()
}

@Composable
fun EmailScreen(isConnected: Boolean, navController: NavController) {
    val context = LocalContext.current
    val isDarkTheme = remember { mutableStateOf(loadThemePreference(context)) }

    LocaWebTheme(darkTheme = isDarkTheme.value) {
        var emails by remember { mutableStateOf(EmailsOnline.emailsSincronizado) }
        var procurar by remember { mutableStateOf("") }
        var categoriaSelecionada by remember { mutableStateOf(categorias[0]) }
        var marcadorSelecionado by remember { mutableStateOf(marcadores[0]) }
        var selecaoEmailsLidosOuNao by remember { mutableStateOf(todos[0]) }
        val scrollState = rememberLazyListState()
        var emailsSelecionadosIds by remember { mutableStateOf(listOf<Int>()) }
        var startButtonSelected by remember { mutableStateOf(false) }

        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonDropdown(categorias, onItemSelected = { categoriaSelecionada = it })
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    navController.navigate("settings")
                }) {
                    Icon(Icons.Filled.Settings, contentDescription = "Configurações")
                }
            }
            SearchField(
                modifier = Modifier.fillMaxWidth(),
                hintText = "Pesquisar",
                value = procurar,
                onValueChange = { procurar = it },
                icon = Icons.Default.Search
            )
            Row {
                Button(
                    onClick = {
                        showDatePickerDialog(context) { year, month, day ->
                            // Lidar com a data selecionada
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                    shape = CircleShape,
                    contentPadding = PaddingValues(start = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "calendário",
                        Modifier.size(36.dp)
                    )
                }
                Button(
                    onClick = {
                        startButtonSelected = !startButtonSelected
                        emails = if (startButtonSelected) {
                            emails.filter { it.importante }
                        } else {
                            EmailsOnline.emailsSincronizado
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    contentPadding = PaddingValues(all = 2.dp),
                    modifier = Modifier.width(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Icone Star",
                        tint = if (startButtonSelected) Color.Blue else Color.Gray,
                        modifier = Modifier.size(29.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                ButtonDropdown(todos, onItemSelected = { selecaoEmailsLidosOuNao = it })
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val filteredEmails = emails.filter {
                    (categoriaSelecionada == "Categorias" || it.categoria == categoriaSelecionada) &&
                            (marcadorSelecionado == "Marcadores" || it.marcadores.contains(
                                marcadorSelecionado
                            )) &&
                            (selecaoEmailsLidosOuNao == "Todos" ||
                                    (selecaoEmailsLidosOuNao == "Lidas" && it.lido) ||
                                    (selecaoEmailsLidosOuNao == "Não lidas" && !it.lido)) &&
                            (procurar.isEmpty() || it.conteudo.contains(
                                procurar,
                                ignoreCase = true
                            ))
                }.sortedByDescending { it.importante }

                LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState) {
                    items(filteredEmails) { email ->
                        MessageEmail(
                            email = email,
                            isSelected = emailsSelecionadosIds.contains(email.id),
                            onClick = {
                                emailsSelecionadosIds = listOf(email.id)
                                navController.navigate("detail")
                            },
                            onImportantClick = {
                                emails = emails.map {
                                    if (it.id == email.id) it.copy(importante = !email.importante) else it
                                }
                            }
                        )
                    }
                }
                LaunchedEffect(emailsSelecionadosIds) {
                    if (emailsSelecionadosIds.isNotEmpty()) {
                        scrollState.scrollToItem(filteredEmails.indexOfFirst { it.id == emailsSelecionadosIds.first() })
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ButtonSendEmail(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                onClick = {
                    navController.navigate("sendEmail")
                }
            )
        }
    }
}


private fun loadThemePreference(context: Context): Boolean {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return prefs.getBoolean("theme", false)
}

