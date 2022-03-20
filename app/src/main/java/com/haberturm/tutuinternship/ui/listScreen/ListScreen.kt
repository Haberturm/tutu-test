package com.haberturm.tutuinternship.ui.listScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haberturm.tutuinternship.data.network.ApiState
import com.haberturm.tutuinternship.ui.nav.NavRoute
import com.haberturm.tutuinternship.ui.view.Item
import com.haberturm.tutuinternship.ui.view.LoadingScreen
import com.haberturm.tutuinternship.ui.view.Rationable
import hero.herodb.HeroEntity
import java.net.UnknownHostException

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
    override fun Content(viewModel: ListScreenViewModel) = ListScreen(viewModel)
}

@Composable
private fun ListScreen(
    viewModel: ListScreenViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        when (val dataState = viewModel.heroDataState) {
            is ApiState.Success -> {
                val lst = dataState.data as List<*>
                LazyColumn {
                    items(lst) { hero ->
                        Item(
                            name = (hero as HeroEntity).name,
                            fullName = hero.fullName,
                            image = hero.image
                        )
                    }
                }
            }
            is ApiState.Loading -> {
                LoadingScreen()
            }
            is ApiState.Failure -> {
                if (dataState.e is UnknownHostException) { //mean there is no internet connection
                    Rationable { viewModel.onEvent(ListScreenEvent.RefreshData) }
                }
            }
            else -> { }
        }
    }
}
