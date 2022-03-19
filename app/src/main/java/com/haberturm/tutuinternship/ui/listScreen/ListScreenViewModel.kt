package com.haberturm.tutuinternship.ui.listScreen

import androidx.lifecycle.ViewModel
import com.haberturm.tutuinternship.ui.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import com.haberturm.tutuinternship.ui.detailScreen.DetailScreenRoute
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    fun onStartClicked() {
        // here we initiate navigation:
        navigateToRoute(DetailScreenRoute.get(0))
    }
}