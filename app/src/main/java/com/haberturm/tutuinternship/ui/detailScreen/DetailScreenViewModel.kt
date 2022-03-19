package com.haberturm.tutuinternship.ui.detailScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haberturm.tutuinternship.ui.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ContentPageViewState(
    val title: String,
    val counterValue: Int,
)

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    private val index = DetailScreenRoute.getIndexFrom(savedStateHandle)

    var viewState by mutableStateOf(
        ContentPageViewState(title = "Page $index", counterValue = 0)
    )

    fun onNextClicked() {
        navigateToNextPage()
    }

    fun onUpClicked() {
        navigateUp()
    }

    fun onNextWithDelayClicked() {
        viewModelScope.launch {
            delay(4000)
            navigateToNextPage()
        }
    }

    private fun navigateToNextPage() {
        navigateToRoute(DetailScreenRoute.get(index + 1))
    }

    fun onIncreaseCounterClicked() {
        viewState = viewState.copy(counterValue = viewState.counterValue + 1)
    }
}