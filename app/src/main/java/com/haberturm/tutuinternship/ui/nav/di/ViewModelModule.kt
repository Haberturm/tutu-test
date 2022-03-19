package com.haberturm.tutuinternship.ui.nav.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import com.haberturm.tutuinternship.ui.nav.MyRouteNavigator
import com.haberturm.tutuinternship.ui.nav.RouteNavigator

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = MyRouteNavigator()
}