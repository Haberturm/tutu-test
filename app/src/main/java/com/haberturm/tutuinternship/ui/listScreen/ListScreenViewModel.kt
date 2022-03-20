package com.haberturm.tutuinternship.ui.listScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haberturm.tutuinternship.data.network.ApiState
import com.haberturm.tutuinternship.data.repositories.listScreen.ListScreenRepository
import com.haberturm.tutuinternship.ui.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import hero.herodb.HeroEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val repository: ListScreenRepository
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    var heroDataState by mutableStateOf<ApiState>(ApiState.Empty)
        private set

    init {
        getSuperHeroList(true)
    }

    fun getSuperHeroList(refresh: Boolean) = viewModelScope.launch {
        heroDataState = ApiState.Loading
        try {
            getData(refresh)
        } catch (e: Exception) {
            Log.i("SPDATA", "$e")
            heroDataState = ApiState.Failure(e)
        }
    }

    private suspend fun getData(refresh: Boolean) {
        repository.getSuperHeroList(refresh)
            .catch { e ->
                Log.i("SPDATA", "$e")
                heroDataState = ApiState.Failure(e)
            }
            .collect { data ->
                if (data == emptyList<HeroEntity>()) {    //if db return empty list, this mean it first enter to the app
                    throw Exception(UnknownHostException())
                }
                else {
                    heroDataState = ApiState.Success(data)
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
        }
    }


    fun onStartClicked() {
        try {
            getSuperHeroList(true)
        } catch (e: Exception) {
            Log.i("SPDATA", "$e")
        }

        // here we initiate navigation:
        //navigateToRoute(DetailScreenRoute.get(0))
    }
}