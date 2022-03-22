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

    private suspend fun getDataFromApiToBd(){
        db.insertData(RetrofitClient.retrofit.getDataList())
//            RetrofitClient.retrofit.getDataList().forEach { hero ->
//                db.insertHero(
//                    id = hero.id,
//                    name = hero.name,
//                    fullName = hero.biography.fullName,
//                    image = hero.images.sm
//                )
//                db.insertStats(
//                    id = hero.id,
//                    intelligence = hero.powerstats.intelligence,
//                    strength = hero.powerstats.strength,
//                    speed = hero.powerstats.speed,
//                    durability = hero.powerstats.durability,
//                    power = hero.powerstats.power,
//                    combat = hero.powerstats.combat
//                )
//                db.insertAppearance(
//                    id = hero.id,
//                    gender = hero.appearance.gender,
//                    race = check4null(hero.appearance.race),
//                    height = hero.appearance.height[1], //lets trust our api:)
//                    weight = hero.appearance.weight[1],
//                    eyeColor = hero.appearance.eyeColor,
//                    hairColor = hero.appearance.hairColor
//                )
//            }
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
        Log.i("SPDATA1", "in rep")
         return if(refresh){
             getDataFromApiToBd()
             db.getAllHeroes()
         }else{
             db.getAllHeroes()
         }
    }
}