package com.haberturm.tutuinternship.di

import android.app.Application
import com.haberturm.tutuinternship.data.repositories.listScreen.ListScreenRepository
import com.haberturm.tutuinternship.data.repositories.listScreen.ListScreenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideListScreenRepository(): ListScreenRepository {
        return ListScreenRepositoryImpl()
    }
}