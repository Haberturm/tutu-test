package com.haberturm.tutuinternship.data.repositories.listScreen

import hero.herodb.HeroEntity
import kotlinx.coroutines.flow.Flow

interface ListScreenRepository {
    suspend fun getSuperHeroList(refresh: Boolean): Flow<List<HeroEntity>>
    fun isInternetAvailable(): Boolean
}