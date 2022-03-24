package com.haberturm.tutuinternship.data.repositories.listScreen

import android.util.Log
import com.haberturm.tutuinternship.data.db.DataSource
import com.haberturm.tutuinternship.data.network.RetrofitClient
import com.haberturm.tutuinternship.data.network.pojo.SuperHero
import hero.herodb.HeroEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.IOException
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket

class ListScreenRepositoryImpl(
    private val db: DataSource,
) : ListScreenRepository {

    private suspend fun getDataFromApiToBd() {
        db.insertData(RetrofitClient.retrofit.getDataList())
    }

    override fun isInternetAvailable(): Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockaddr = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockaddr, timeoutMs)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun getSuperHeroList(refresh: Boolean): Flow<List<HeroEntity>> {
        if (refresh) {
            getDataFromApiToBd()
            return db.getAllHeroes()
        } else {
            return db.getAllHeroes()
        }
    }
}