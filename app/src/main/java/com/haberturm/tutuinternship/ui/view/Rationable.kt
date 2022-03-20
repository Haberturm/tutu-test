package com.haberturm.tutuinternship.ui.view

import android.util.Rational
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haberturm.tutuinternship.R
import com.haberturm.tutuinternship.ui.listScreen.ListScreenEvent

@Composable
fun Rationable(action: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(R.string.rationable),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                action()
            }
        ) {
            Text(text = "Обновить")
        }
    }

}

@Composable
@Preview
fun RationablePrev(){
    Rationable({})
}