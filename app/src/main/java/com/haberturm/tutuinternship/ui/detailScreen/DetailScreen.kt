package com.haberturm.tutuinternship.ui.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.haberturm.tutuinternship.ui.nav.NavRoute
import com.haberturm.tutuinternship.ui.nav.getOrThrow

const val KEY_CONTENT_PAGE_INDEX = "CONTENT_PAGE_INDEX"

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object DetailScreenRoute : NavRoute<DetailScreenViewModel> {

    override val route = "detail/{$KEY_CONTENT_PAGE_INDEX}/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: Int): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    fun getIndexFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<Int>(KEY_CONTENT_PAGE_INDEX)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.IntType })

    @Composable
    override fun viewModel(): DetailScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: DetailScreenViewModel) = DetailScreen(viewModel)
}

/**
 * Just your average Composable, nothing special here.
 */
@Composable
private fun DetailScreen(
    viewModel: DetailScreenViewModel
) {
    val viewState = viewModel.viewState

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = viewState.title,
            style = MaterialTheme.typography.h6,
        )
    }
}
