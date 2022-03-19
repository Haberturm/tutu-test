package com.haberturm.tutuinternship.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.haberturm.tutuinternship.ui.listScreen.ListScreenRoute
import com.haberturm.tutuinternship.ui.detailScreen.DetailScreenRoute

@Composable
fun ScreenHolder(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = ListScreenRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        ListScreenRoute.composable(this, navHostController)
        DetailScreenRoute.composable(
            this, navHostController
        )
    }
}