package com.haberturm.tutuinternship.ui.listScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.haberturm.tutuinternship.R
import com.haberturm.tutuinternship.data.DataState
import com.haberturm.tutuinternship.ui.nav.NavRoute
import com.haberturm.tutuinternship.ui.view.ErrorView
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
        Button(onClick = {viewModel.onEvent(ListScreenEvent.RefreshData)}) {
            Text(text = "pleeeease")
        }
//        val data = viewModel.data.collectAsState(initial = emptyList())
//        LazyColumn {
//
//            items(data.value) { hero ->
//                Item(
//                    name = hero.name,
//                    fullName = hero.fullName,
//                    image = hero.image
//                )
//            }
//        }
        val dataState = viewModel.heroDataState
        Log.i("state", dataState.toString())
        when (dataState) {
            is DataState.Success -> {
                val heroList = dataState.data as List<*>
                Content(
                    heroList = heroList,
                    content = {},
                    viewModel = viewModel
                )
            }
            is DataState.Loading -> {
                LoadingScreen()
            }
            is DataState.Failure -> {
                if (dataState.e.message == ListException.FIRST_ENTER) { //mean there is no internet connection
                    Rationable { viewModel.onEvent(ListScreenEvent.RefreshData) }
                }else{
                    //TODO unkonwn exception
                }
            }
            is DataState.Offline -> {
                val heroList = dataState.data as List<*>
                Content(
                    heroList = heroList,
                    content = {
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

@Composable
fun Content(
    heroList: List<*>,
    content: @Composable() () -> Unit,
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
            item {
                content()
            }
            items(heroList) { hero ->
                Item(
                    name = (hero as HeroEntity).name,
                    fullName = hero.fullName,
                    image = hero.image
                )
            }
        }
    }
}
