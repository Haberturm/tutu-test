package com.haberturm.tutuinternship.ui.listScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haberturm.tutuinternship.data.network.ApiState
import com.haberturm.tutuinternship.data.repositories.listScreen.ListScreenRepository
import com.haberturm.tutuinternship.ui.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import com.haberturm.tutuinternship.ui.detailScreen.DetailScreenRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val repository: ListScreenRepository
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    val myDataList: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    fun getSuperHeroList() = viewModelScope.launch {
        Log.i("SPDATA", "get")
        repository.getSuperHeroList()
            .catch { e ->
                Log.i("SPDATA", "$e")
                myDataList.value = ApiState.Failure(e)
            }
            .collect { data ->
                Log.i("SPDATA", "$data")
            }
    }

    fun onStartClicked() {
        getSuperHeroList()
        // here we initiate navigation:
        //navigateToRoute(DetailScreenRoute.get(0))
    }
}