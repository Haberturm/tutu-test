package com.haberturm.tutuinternship.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorHeader(
    text: String = "Для тестирования. Нужно обновить"
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.error)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Composable
@Preview
fun ErrCardPrev() {
    ErrorHeader()
}