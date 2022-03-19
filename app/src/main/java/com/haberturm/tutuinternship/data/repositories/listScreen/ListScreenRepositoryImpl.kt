package com.haberturm.tutuinternship.data.repositories.listScreen

import com.haberturm.tutuinternship.data.network.RetrofitClient
import com.haberturm.tutuinternship.data.network.pojo.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ListScreenRepositoryImpl : ListScreenRepository {

    override fun getSuperHeroList(): Flow<List<SuperHero>> = flow {
        val r = RetrofitClient.retrofit.getDataList()
        emit(r)
    }.flowOn(Dispatchers.IO)
}