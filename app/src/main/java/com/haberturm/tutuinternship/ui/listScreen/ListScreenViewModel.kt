package com.haberturm.tutuinternship.ui.listScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.haberturm.tutuinternship.data.DataState
import com.haberturm.tutuinternship.data.repositories.listScreen.ListScreenRepository
import com.haberturm.tutuinternship.ui.detailScreen.DetailScreenRoute
import com.haberturm.tutuinternship.ui.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import hero.herodb.HeroEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

object ListException {
    const val FIRST_ENTER = "FIRST_ENTER_EXCEPTION"
}


@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val repository: ListScreenRepository
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    var heroDataState by mutableStateOf<DataState>(DataState.Empty)
        private set

    var swipeIndicatorVisibility by mutableStateOf(false)
        private set

    init {
        getSuperHeroList(true)
    }

    private fun getSuperHeroList(refresh: Boolean) = viewModelScope.launch {
        heroDataState = DataState.Loading
        swipeIndicatorVisibility = true
        delay(500) //for smooth loading screen
        try {
            getData(refresh)
        } catch (e: Exception) {
            Log.i("EXCEPTION-LISTVM1", "$e")
            if (e is UnknownHostException) { //mean no internet connection in this case
                onEvent(ListScreenEvent.TryOfflineMode)
            } else {
                heroDataState = DataState.Failure(e)
            }

        }
    }

    private suspend fun getData(refresh: Boolean) {
        withContext(Dispatchers.IO) {
            repository.getSuperHeroList(refresh)
                .catch { e ->
                    Log.i("EXCEPTION-LISTVM2", "$e")
                    heroDataState = DataState.Failure(e)
                }
                .collect { data ->
                    if (refresh) {
                        swipeIndicatorVisibility = false
                        heroDataState = DataState.Success(data)
                    } else if(!repository.isInternetAvailable()){
                        //if db return not empty list, this mean we should try offline mode.
                        if (data != emptyList<HeroEntity>()) {
                            swipeIndicatorVisibility = false
                            heroDataState = DataState.Offline(data)
                        } else {
                            throw Exception(ListException.FIRST_ENTER)
                        }
                    }
                }
        }
    }

    fun onEvent(event: ListScreenEvent) {
        when (event) {
            is ListScreenEvent.RefreshData -> {
                getSuperHeroList(true)
            }
            is ListScreenEvent.TryOfflineMode -> {
                getSuperHeroList(false)
            }
            is ListScreenEvent.NavigateToDetailScreen -> {
                navigateToRoute(DetailScreenRoute.get(event.itemId))
            }
        }
    }
}