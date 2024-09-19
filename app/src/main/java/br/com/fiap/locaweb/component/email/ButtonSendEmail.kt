package br.com.fiap.locaweb.component.email

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.locaweb.ui.theme.LocaWebTheme

@Composable
fun ButtonSendEmail(onClick: () -> Unit, modifier: Modifier = Modifier) {
    // Box to align the FloatingActionButton
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send Email"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonSendEmail() {
    LocaWebTheme {
        ButtonSendEmail(onClick = {})
    }
}
