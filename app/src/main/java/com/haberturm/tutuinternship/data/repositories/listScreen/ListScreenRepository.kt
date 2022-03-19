package com.haberturm.tutuinternship.data.repositories.listScreen

import com.haberturm.tutuinternship.data.network.pojo.SuperHero
import kotlinx.coroutines.flow.Flow

interface ListScreenRepository {
    fun getSuperHeroList(): Flow<List<SuperHero>>
}