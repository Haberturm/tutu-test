package com.haberturm.tutuinternship.data.repositories.listScreen

import com.haberturm.tutuinternship.data.network.pojo.SuperHero
import hero.herodb.HeroEntity
import kotlinx.coroutines.flow.Flow

interface ListScreenRepository {
    suspend fun getSuperHeroList(refresh: Boolean): Flow<List<HeroEntity>>
    fun isInternetAvailable(): Boolean
}