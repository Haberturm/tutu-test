package com.haberturm.tutuinternship.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haberturm.tutuinternship.R
import java.lang.Error

@Composable
fun ErrorView(
    action: () -> Unit,
    text: String
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                action()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.secondaryVariant
            )
        ) {
            Text(text = "Обновить")
        }
    }
}

@Composable
@Preview
fun ErrorViewPrev(){
    ErrorView({}, stringResource(id = R.string.rationable))
}