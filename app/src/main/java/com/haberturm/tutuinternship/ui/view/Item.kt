package com.haberturm.tutuinternship.ui.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.haberturm.tutuinternship.ui.listScreen.ListScreenEvent
import androidx.compose.foundation.layout.size as size

@Composable
fun Item(
    name: String,
    fullName: String,
    image: String,
    action: () -> Unit,
) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                action()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "hero-icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = name)
                Text(text = fullName)
            }
        }

    }
}

@Composable
@Preview
fun ItemPrev() {
    Item(
        "A-Bomb",
        "Richard Milhouse Jones",
        "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/1-a-bomb.jpg",
        {}
    )
}