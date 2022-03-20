package com.haberturm.tutuinternship.data.repositories.listScreen

import android.util.Log
import com.haberturm.tutuinternship.data.db.DataSource
import com.haberturm.tutuinternship.data.network.RetrofitClient
import com.haberturm.tutuinternship.data.network.pojo.SuperHero
import hero.herodb.HeroEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ListScreenRepositoryImpl(
    private val db: DataSource,
) : ListScreenRepository {

    private suspend fun getDataFromApi(){
            RetrofitClient.retrofit.getDataList().forEach { hero ->
                db.insertHero(
                    id = hero.id,
                    name = hero.name,
                    fullName = hero.biography.fullName,
                    image = hero.images.sm
                )
                db.insertStats(
                    id = hero.id,
                    intelligence = hero.powerstats.intelligence,
                    strength = hero.powerstats.strength,
                    speed = hero.powerstats.speed,
                    durability = hero.powerstats.durability,
                    power = hero.powerstats.power,
                    combat = hero.powerstats.combat
                )
                db.insertAppearance(
                    id = hero.id,
                    gender = hero.appearance.gender,
                    race = check4null(hero.appearance.race),
                    height = hero.appearance.height[1], //lets trust our backend api:)
                    weight = hero.appearance.weight[1],
                    eyeColor = hero.appearance.eyeColor,
                    hairColor = hero.appearance.hairColor
                )
            }
    }

    private fun check4null(nullable: String?): String{
        return nullable ?: "unknown"
    }

    override suspend fun getSuperHeroList(refresh: Boolean): Flow<List<HeroEntity>> {
        if(refresh){
            getDataFromApi()
        }
        return db.getAllHeroes()
    }
}