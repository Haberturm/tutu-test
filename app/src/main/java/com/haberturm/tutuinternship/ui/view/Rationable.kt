package com.haberturm.tutuinternship.ui.view

import android.util.Rational
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.haberturm.tutuinternship.ui.listScreen.ListScreenEvent

@Composable
fun Rationable(action: () -> Unit) {
    Text(text = "Чтобы приложение работало, нужно вклчють интренет. Вклчюите интернет и обновите страницу")
    Button(
        onClick = {
            action()
        }
    ) {
        Text(text = "Обновить")
    }
}

