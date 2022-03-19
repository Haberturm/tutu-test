package com.haberturm.tutuinternship.data.network

import com.haberturm.tutuinternship.data.network.pojo.SuperHero
import retrofit2.http.GET

interface RetrofitInterface {
    @GET(SuperHeroApi.DATA_LIST)
    suspend fun getDataList(): List<SuperHero>
}