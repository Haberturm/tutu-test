package com.haberturm.tutuinternship.ui.listScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haberturm.tutuinternship.ui.nav.NavRoute

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object ListScreenRoute : NavRoute<ListScreenViewModel> {

    override val route = "list/"

    @Composable
    override fun viewModel(): ListScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ListScreenViewModel) = ListScreen(viewModel::onStartClicked)
}

/**
 * Just your average Composable, nothing special here.
 */
@Composable
private fun ListScreen(
    onStartClicked: () -> Unit
) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Text",
            style = MaterialTheme.typography.h6,
        )
        Button(onClick = onStartClicked) {

        }
    }
}
