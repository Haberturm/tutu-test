package com.haberturm.tutuinternship.ui.listScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.haberturm.tutuinternship.R
import com.haberturm.tutuinternship.data.DataState
import com.haberturm.tutuinternship.ui.nav.NavRoute
import com.haberturm.tutuinternship.ui.view.*
import hero.herodb.HeroEntity

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
        val dataState = viewModel.heroDataState

        when (dataState) {
            is DataState.Success -> {
                val heroList = dataState.data as List<*>
                Content(
                    heroList = heroList,
                    errContent = {},
                    viewModel = viewModel
                )
            }
            is DataState.Loading -> {
                LoadingScreen()
            }
            is DataState.Failure -> {
                if (dataState.e.message == ListException.FIRST_ENTER) {
                    ErrorView(
                        action = { viewModel.onEvent(ListScreenEvent.RefreshData) }, 
                        text = stringResource(id = R.string.rationable)
                    )
                }else{
                    ErrorView(
                        action = { viewModel.onEvent(ListScreenEvent.RefreshData) },
                        text = stringResource(id = R.string.something_went_wrong)
                    )
                }
            }
            is DataState.Offline -> {
                val heroList = dataState.data as List<*>
                Content(
                    heroList = heroList,
                    errContent = {
                        ErrorView(
                            text = stringResource(R.string.internet_err)
                        )
                    },
                    viewModel = viewModel
                )

            }
            else -> {
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(
    heroList: List<*>,
    errContent: @Composable() () -> Unit,
    viewModel: ListScreenViewModel,
) {

    val isRefreshing = viewModel.swipeIndicatorVisibility
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { Log.i("spdata-refresh", "refresh swiped")
            viewModel.onEvent(ListScreenEvent.RefreshData)
        })
    {

        LazyColumn {
            stickyHeader{
                errContent()
            }
            items(heroList) { hero ->
                Item(
                    name = (hero as HeroEntity).name,
                    fullName = hero.fullName,
                    image = hero.image,
                    { viewModel.onEvent(ListScreenEvent.NavigateToDetailScreen(hero.id)) }
                )
            }
        }

    }
}
